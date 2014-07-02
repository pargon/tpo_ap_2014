package RMI.controller;

import hbt.dao.HibernateCotizacionRodamientoDAO;
import hbt.dao.HibernateSolicitudCotizacionDAO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import beans.BeanCotizacionRodamiento;
import beans.BeanSolicitudCotizacion;
import beans.BeansCliente;
import beans.BeansFactura;
import beans.BeansRemito;
import Interfaz.InterfazRMI;
import model.Cliente;
import model.ClienteSRV;
import model.CotizacionRodamiento;
import model.CotizacionRodamientoSRV;
import model.FacturaSRV;
import model.ItemRodamiento;
import model.ItemSolicitudCotizacion;
import model.ListaPrecios;
import model.ListaPreciosSRV;
import model.Remito;
import model.SolicitudCotizacion;
import model.SolicitudCotizacionSRV;

public class RMIController extends UnicastRemoteObject implements InterfazRMI {

	public RMIController() throws RemoteException {
		super();
	}

	private static final long serialVersionUID = 1L;

	@Override
	public boolean agregarCliente(BeansCliente dtoCliente) throws RemoteException {
		System.out.println("Alta Cliente en Servidor!");
		Cliente c = ClienteSRV.getinstancia().fromBean(dtoCliente);
		System.out.println(c.getCuit());
		ClienteSRV.getinstancia().guardar(c);
		return true;
	}

	@Override
	public boolean borrarCliente(BeansCliente beanCliente) throws RemoteException {
		System.out.println("Ejecuta Modificacion de clientes");
		Cliente modificacionCliente = new Cliente();
		modificacionCliente = ClienteSRV.getinstancia().buscarClientes("Jorge y Cia 222", "20-30734253-222").get(0);
		modificacionCliente.setPorcentajeDesc((float)2.2);
		ClienteSRV.getinstancia().actualizarCliente(modificacionCliente);
		return true;
	}

	@Override
	public boolean actualizarCliente(BeansCliente beanCliente) throws RemoteException {
		System.out.println("Ejecuta Eliminacion de clientes");
		Cliente eliminaCliente = new Cliente();
		eliminaCliente = ClienteSRV.getinstancia().buscarClientes("Jorge y Cia 222", "20-30734253-222").get(0);
		ClienteSRV.getinstancia().borrarCliente(eliminaCliente);
		return true;
	}

	@Override
	public BeansFactura ventaRodamiento(List<BeansRemito> beansremitos) throws RemoteException {
		System.out.println("Ejecuta Venta de Rodamientos");
		BeansFactura facturas = FacturaSRV.getinstancia().Facturar(beansremitos);
		
		return facturas;
	}

	@Override
	public BeanCotizacionRodamiento guardarSolicitudCotizacion(BeanSolicitudCotizacion beanSolicitudCotizacion)
	throws RemoteException {
		SolicitudCotizacion solicitudCotizacion = SolicitudCotizacionSRV.getinstancia().fromBean(beanSolicitudCotizacion);
		new HibernateSolicitudCotizacionDAO().guardarSolicitudCotizacion(solicitudCotizacion);
		CotizacionRodamiento cotizacionRodamiento = new CotizacionRodamiento();
		cotizacionRodamiento.setActiva(1);
		cotizacionRodamiento.setFechaCotizacion(new Date());
		cotizacionRodamiento.setTermino(30);
		cotizacionRodamiento.setSolicitudCotizacion(solicitudCotizacion);
		//Tengo que levantar las listas de precios, asi puedo buscar el mejor precio para cada rodamiento
		List<String> archivos = new ArrayList<String>();
		archivos.add("C:\\ListaPrecio1.xml");
		archivos.add("C:\\ListaPrecio2.xml");
		List<ListaPrecios> lprecios = new ListaPreciosSRV().getinstancia().getListas(archivos);
		List<ItemRodamiento> lraux = new ArrayList<ItemRodamiento>();
		List<ItemSolicitudCotizacion> liscaux = solicitudCotizacion.getItemsSolicitudCotizacion();
		for(ListaPrecios lp : lprecios){
			List<ItemRodamiento> irs = lp.getItemsRodamiento();
			for(int i=0;i<liscaux.size();i++){
				for(int j=0;j<irs.size();j++){
					if(liscaux.get(i).getRodamiento().getRodamientoId().getCodigo() == irs.get(j).getRodamiento().getRodamientoId().getCodigo()){
						//el id de itemRoda se tiene q generar automaticamente
						ItemRodamiento itemRoda = new ItemRodamiento();
						itemRoda.setRodamiento(liscaux.get(i).getRodamiento());
						itemRoda.setPrecio(irs.get(j).getPrecio());
						//si es la primera vez que colecto los items no los puedo comparar, los agrego directamente
						if(lraux.get(j) == null)
							lraux.add(itemRoda);
						else
							if(itemRoda.getPrecio() < lraux.get(i).getPrecio())
								lraux.get(i).setPrecio(itemRoda.getPrecio());
						j=irs.size();
						
						
					}
				}	
			}
		}
		cotizacionRodamiento.setItemsRodamiento(lraux);
		//Falta calcular el precio total...aunque en el xml no lo indica. Creo que lo sacamos a la mierda mejor...
		
		new HibernateCotizacionRodamientoDAO().guardarCotizacionRodamiento(cotizacionRodamiento);
		//Falta tmb hacer el toBean
		BeanCotizacionRodamiento beanCotizacionRodamiento = CotizacionRodamientoSRV.getinstancia().toBean(cotizacionRodamiento);
		return beanCotizacionRodamiento;
		
		
	}
	
	public void crearOrdenCompra() throws RemoteException {
		
	}


}
	

