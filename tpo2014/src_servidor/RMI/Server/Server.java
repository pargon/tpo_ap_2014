package RMI.Server;

import Interfaz.InterfazRMI;

import java.io.FileWriter;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.ClienteSRV;
import model.CotizacionRodamiento;
import model.CotizacionRodamientoSRV;
import model.FacturaSRV;
import model.Remito;
import model.RemitosSRV;
import RMI.controller.*;
import beans.*;
import model.Cliente;
import model.Factura;

import org.jdom2.Attribute;
import org.jdom2.Document;            
import org.jdom2.Element;             
import org.jdom2.output.Format;       
import org.jdom2.output.XMLOutputter; 

//import com.thoughtworks.xstream.XStream;
//import com.thoughtworks.xstream.io.xml.DomDriver;


public class Server {
    
	protected InterfazRMI objetoRemoto;
	
	public Server() {
		iniciar();
	}

	public static void main(String[] args)
	{
		System.out.println("Ejecuta main");	
		new Server();	
		
		System.out.println("Ejecuta Carga de clientes");	
		BeansCliente nuevoClienteBeans = new BeansCliente();
		nuevoClienteBeans.setCuit("20-30734253-222");
		nuevoClienteBeans.setContacto("254847");
		nuevoClienteBeans.setPorcentajeDesc((float)1.51);
		nuevoClienteBeans.setRazonSocial("Jorge y Cia 222");
		nuevoClienteBeans.setTelefono("15654898897");
		Cliente cliente = ClienteSRV.getinstancia().fromBean(nuevoClienteBeans);
		ClienteSRV.getinstancia().guardar(cliente);
		
		System.out.println("Ejecuta Modificacion de clientes");
		Cliente modificacionCliente = new Cliente();
		modificacionCliente = ClienteSRV.getinstancia().buscarClientes("Jorge y Cia 222", "20-30734253-222").get(0);
		modificacionCliente.setPorcentajeDesc((float)2.2);
		ClienteSRV.getinstancia().actualizarCliente(modificacionCliente);
		
		//System.out.println("Ejecuta Eliminacion de clientes");
		//Cliente eliminaCliente = new Cliente();
		//eliminaCliente = ClienteSRV.getinstancia().buscarClientes("Jorge y Cia 222", "20-30734253-222").get(0);
		//ClienteSRV.getinstancia().borrarCliente(eliminaCliente);
		
		System.out.println("Ejecuta Creacion de cotizacion");
		BeansCotizacionRodamiento beansCotizacion = new BeansCotizacionRodamiento();
		beansCotizacion.setActiva(1);		
		beansCotizacion.setBeanCliente(nuevoClienteBeans);
		beansCotizacion.setFechaCotizacion(Calendar.getInstance().getTime());
		BeansItemCotizacionRodamiento it = new BeansItemCotizacionRodamiento();
		
		Calendar c1 = Calendar.getInstance(); 
		c1.add(Calendar.DATE,25); //le suma 25 días
		beansCotizacion.setFechaVencimiento(c1.getTime());

		CotizacionRodamiento cotizacion = CotizacionRodamientoSRV.getinstancia().fromBean(beansCotizacion);
		CotizacionRodamientoSRV.getinstancia().guardar(cotizacion);
		
		//creo un remito para el cliente existente
		System.out.println("Crea remito para un cliente existente");
		BeansRemito beansremito = new BeansRemito();
		beansremito.setCliente(nuevoClienteBeans);
		beansremito.setCotizacion(beansCotizacion);
		beansremito.setFecha(Calendar.getInstance().getTime());
		Remito remito = RemitosSRV.getinstancia().fromBean(beansremito);
		RemitosSRV.getinstancia().guardar(remito);
		
		
		System.out.println("Ejecuta Venta de rodamiento");
		List<BeansRemito> remitos = new ArrayList<BeansRemito>();
		remitos = RemitosSRV.getinstancia().BuscarRemitosPendientesFacturacion(cliente.getId());
		BeansFactura facturas = new BeansFactura();
		facturas = FacturaSRV.getinstancia().Facturar(remitos);
		escribirXML(facturas);
	}

	
	private static void escribirXML(BeansFactura facturas) {
		Element factura = new Element("Factura");
		Document doc = new Document(factura);
	    factura.setAttribute(new Attribute("Numero", facturas.getNroFactura().toString()));

	    Element Fechas = new Element("Fechas");
	    Fechas.addContent(new Element("Emision").setText(facturas.getFecha().toString()));
	    Fechas.addContent(new Element("Vencimiento").setText(facturas.getFecha().toString()));
	    doc.getRootElement().addContent(Fechas);
	    
	    Element Cliente = new Element("Cliente");
	    Cliente.addContent(new Element("Cuil").setText(facturas.getRemitos().get(0).getCliente().getCuit()));
	    Cliente.addContent(new Element("RazonSocial").setText(facturas.getRemitos().get(0).getCliente().getRazonSocial()));
	    Cliente.addContent(new Element("CondicionIva").setText(facturas.getRemitos().get(0).getCliente().getCuit()));
	    doc.getRootElement().addContent(Cliente);
	    
	    Element CondicionesDeVenta = new Element("CondicionesDeVenta");
	    doc.getRootElement().addContent(CondicionesDeVenta);
	    
	    Element PagoContado = new Element("PagoContado");
	    PagoContado.addContent(new Element("Descuento").setText(facturas.getRemitos().get(0).getCliente().getCuit()));
	    
	    Element Financiamiento = new Element("Financiación");
	    Financiamiento.addContent(new Element("CantidadDias").setText("30"));
	    Financiamiento.addContent(new Element("Recargo").setText("30"));
	    
	    CondicionesDeVenta.addContent(PagoContado);
	    CondicionesDeVenta.addContent(Financiamiento);

	    Element Referencia = new Element("Referencia");
	    doc.getRootElement().addContent(Referencia);

	    Element Remito = new Element("Remito");
	    Remito.addContent(new Element("Numero").setText("23123"));
	    Remito.addContent(new Element("Fecha").setText("fecha"));
	    Referencia.addContent(Remito);
	    

	    Element Rodamientos = new Element("Rodamientos");
	    Element Item = new Element("Item");
	    Item.setAttribute(new Attribute("Serie", "Numero de serie"));
	    Item.addContent(new Element("Sufijo").setText("23123"));
	    Item.addContent(new Element("Marca").setText("Marca fabricante"));
	    Item.addContent(new Element("Origen").setText("Pais de fabricación"));
	    Item.addContent(new Element("Precio").setText("Precio sin iva"));
	    Item.addContent(new Element("Cantidad").setText("Cantidad de unidades"));
	    Rodamientos.addContent(Item);
	    Referencia.addContent(Rodamientos);
	   
        XMLOutputter xmlOutput = new XMLOutputter();
 
        // display nice nice
        xmlOutput.setFormat(Format.getPrettyFormat());
        try {
			xmlOutput.output(doc, new FileWriter("e:\\file.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
        System.out.println("File Saved!");
       
	}

	public void iniciar() {
    	try {
    		LocateRegistry.createRegistry(1099);	
    	    objetoRemoto = new RMIController();
            Naming.rebind ("//localhost/rmiController", objetoRemoto);
            System.out.println("Fijado en //localhost/rmiController");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	

	
	
}
