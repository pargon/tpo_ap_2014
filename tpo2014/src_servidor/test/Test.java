package test;

import hbt.dao.HibernateDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import beans.BeanItemSolicitudCotizacion;
import beans.BeanMarca;
import beans.BeanRodamiento;
import beans.BeanSolicitudCotizacion;
import beans.BeansCliente;
import RMI.controller.RMIController;
import model.Cliente;
import model.CotizacionRodamiento;
import model.ItemRodamiento;
import model.ItemSolicitudCotizacion;
import model.ListaPreciosSRV;
import model.Marca;
import model.Marca.MarcaId;
import model.OrdenCompra;
import model.OrdenPedido;
import model.Proveedor;
import model.Rodamiento;
import model.Rodamiento.RodamientoId;
import model.SolicitudCotizacion;


public class Test {
	
	private static HibernateDAO hdao;
	
	public Test() {
	}
	
	public static void persistirObj(Object c){
		hdao.getInstancia().persistir(c);
	}
	
	
	public static void main(String[] args) {

		//altaListaPrecios();
		
		
		//testXML();
		
		//System.exit(1);
		
		AltaDatos();
		
		//BeanSolicitudCotizacion bsc = CrearBeanSolCot();
		/*
		try {
			int cot = new RMIController().guardarSolicitudCotizacion(bsc);
			
			new RMIController().crearOrdenPedido(cot);
			
			new RMIController().crearOrdenCompra();

			int ocnro = get1erOC();
			
			new RMIController().recepcionMercaderia(ocnro);
			
			new RMIController().facturar();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		System.exit(0);
	}

	private static void altaListaPrecios() {
		List<String> archivos = new ArrayList<String>();
		archivos.add("c:\\ListaPrecio1.xml");
		archivos.add("C:\\ListaPrecio2.xml");
		
		new ListaPreciosSRV().getinstancia().getListas(archivos);

		
	}

	private static void testXML() {
		new ListaPreciosSRV().actualizarListas("c:\\ListaPrecio1.xml", "1", 1542);
		
	}

	private static int get1erOC() {
		OrdenCompra oc = (OrdenCompra)HibernateDAO.getInstancia().get(OrdenCompra.class, 1) ;
		return oc.getId();
	}

	private static BeanSolicitudCotizacion CrearBeanSolCot() {
		Marca mar = new Marca();
		MarcaId marid= new MarcaId();
		marid.setDescripcion("SDK");
		marid.setPais("Suecia");
		mar.setMarcaId(marid);
		HibernateDAO.getInstancia().persistir(mar);
		System.out.println("marca");
		
		
		BeanSolicitudCotizacion bsc = new BeanSolicitudCotizacion();
		BeanItemSolicitudCotizacion bisc = new BeanItemSolicitudCotizacion();
		BeansCliente bc = new BeansCliente();
		bc.setContacto("Contacto PEPE");
		bc.setCuit("123");
		bc.setPorcentajeDesc((float) 10.00);
		bc.setRazonSocial("Razon social SRL");
		bc.setTelefono("42064885");
		bsc.setBeansCliente(bc);
		bisc.setCantidad(5);
		
		BeanRodamiento br = new BeanRodamiento();
		br.setTipo("Bolilla");
		br.setCodigo("1");
		br.setCaracteristicas("456");
		
		BeanMarca bm = new BeanMarca();
		bm.setDescripcion("SDK");
		bm.setPais("Suecia");
		
		
		br.setBeanMarca(bm);
		bisc.setBeanRodamiento(br);
		List<BeanItemSolicitudCotizacion> blisc = new ArrayList<BeanItemSolicitudCotizacion>();
		blisc.add(bisc);
		bsc.setBeanItemsSolicitudCotizacion(blisc);
		bsc.setFecha(new Date());
		return bsc;

				
	}

	private static void AltaDatos() {
		
		Cliente c = new Cliente();
		c.setCuit("2132131");
		c.setRazonSocial("Razon SRL");
		persistirObj(c);
		
		
		// alta cotizacion
		CotizacionRodamiento cot = new CotizacionRodamiento();
		cot.setFechaCotizacion(new Date());
		List<ItemRodamiento> lrod = new ArrayList<ItemRodamiento>();
		Rodamiento rod = new Rodamiento();
		ItemRodamiento irod = new ItemRodamiento();
		Proveedor prv = new Proveedor();
		RodamientoId rodid = new RodamientoId();
		Marca marc = new Marca();
		MarcaId mId = new MarcaId();
		mId.setDescripcion("marcadesc");
		mId.setPais("argen");
		
		marc.setMarcaId(mId);
		//HibernateDAO.getInstancia().persistir(marc);
		rodid.setCodigo("codigo1");
		rodid.setMarca(marc);
		rod.setRodamientoId(rodid);
		prv.setCuit("1234455");
		prv.setRazonSocial("el mas");
		
		irod.setCantidad(1);
		irod.setPrecio(12);
		irod.setRodamiento(rod);
		irod.setProveedor(prv);
		
		lrod.add(irod);
		
		Cliente cli = new Cliente();
		cli.setCuit("33444555");
		cli.setRazonSocial("el cli");
		
		cot.setItemsRodamiento(lrod);
		
		SolicitudCotizacion sol = new SolicitudCotizacion();
		sol.setCliente(cli);
		sol.setFecha(new Date());
		
		List<ItemSolicitudCotizacion> lsol = new ArrayList<ItemSolicitudCotizacion>();
		ItemSolicitudCotizacion itsol = new ItemSolicitudCotizacion();
		itsol.setCantidad(12);
		itsol.setRodamiento(rod);
		
		sol.setItemsSolicitudCotizacion(lsol);
		
		cot.setSolicitudCotizacion(sol);
		
		HibernateDAO.getInstancia().persistir(cot);
		
		// alta orden de pedido
		OrdenPedido op =  new OrdenPedido();
		
		op.setCliente(cli);
		op.setCot(cot);
		op.setEstado("PEN");
		op.setFecha(new Date());
		
		persistirObj(op);
		
	}
}


