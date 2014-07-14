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

import model.OrdenCompra;
import model.OrdenPedido;
import model.Proveedor;
import model.Rodamiento;
import model.Rodamiento.RodamientoId;
import model.SolicitudCotizacion;


public class Test {
	
	public Test() {
	}	
	
	public static void main(String[] args) {

		altaListaPrecios();
		
		System.exit(0);
	}

	private static void altaListaPrecios() {
		List<String> archivos = new ArrayList<String>();
		archivos.add("c:\\ListaPrecio1.xml");
		archivos.add("C:\\ListaPrecio2.xml");
		archivos.add("C:\\ListaPrecio3.xml");
		archivos.add("C:\\ListaPrecio4.xml");
		
		new ListaPreciosSRV().getinstancia().getListas(archivos);
		
	}

	
	
	
	public static void persistirObj(Object c){
		HibernateDAO.getInstancia().persistir(c);
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
		marc.setDescripcion("marcadesc");
		marc.setPais("argen");
		
		
		HibernateDAO.getInstancia().persistir(marc);
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
		
		
		//item2
		rod = new Rodamiento();
		irod = new ItemRodamiento();
		rodid = new RodamientoId();
		
		rodid.setCodigo("codigo2");
		rodid.setMarca(marc);
		rod.setRodamientoId(rodid);
		
		irod.setCantidad(1);
		irod.setPrecio(14);
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


