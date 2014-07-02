package test;

import hbt.dao.HibernateDAO;
import hbt.dao.HibernetCasaDAO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import model.Cliente;
import model.CotizacionRodamiento;
import model.Factura;
import model.ItemCotizacion;
import model.ItemRodamiento;
import model.Marca;
import model.Marca.MarcaId;
import model.OrdenCompra;
import model.OrdenPedido;
import model.OrdenPedidoSRV;
import model.Proveedor;
import model.Remito;
import model.Rodamiento;
import model.Rodamiento.RodamientoId;


public class Test {
	private static HibernateDAO hdao;
	
	public Test(){
	
	}
	
	public static void persistirCliente(Object c){
		hdao.getInstancia().persistir(c);
	}
	
	
	public static void main(String[] args) {
		
		Cliente c = new Cliente();
		c.setCuit("2132131");
		c.setRazonSocial("Razon SRL");
		persistirCliente(c);
		
		Remito r = new Remito();
		r.setCliente(c);
		
		Remito r2 = new Remito();
		r.setCliente(c);
		
		@SuppressWarnings("deprecation")
		Date d = new Date(14,01,01);
		
		r.setFecha( d);
		r2.setFecha(d);
		persistirCliente(r);
		persistirCliente(r2);
		
		List<Remito> lr = new ArrayList<Remito>();
		lr.add(r);
		lr.add(r2);
		
		Factura f = new Factura();
		f.setRemitos(lr);
		persistirCliente(f);
		
		Factura f2 = new Factura();
		f.setRemitos(lr);
		try{
		persistirCliente(f2);
		}catch(Exception e)
		{	e.printStackTrace();}
		
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
		
		cot.setItemsRodamiento(lrod);
		HibernateDAO.getInstancia().persistir(cot);
		
		ocompra();
		
		System.exit(0);
	}

	private static void ocompra() {
		
		List<OrdenPedido> lop;
		List<OrdenCompra> loc = new ArrayList<OrdenCompra>();
		
		// recorre ordenes de pedido pendientes
		
		lop = OrdenPedidoSRV.getinstancia().getListaPendientes();	
		Date fecha = new java.util.Date();
		
		// recorre las ordenes de pedido pendientes
		for(OrdenPedido op: lop){
			List<ItemRodamiento> lro = op.getListaRod();
			
			// recorre los rodamientos
			for(ItemRodamiento itrod: lro){
				Proveedor pr = itrod.getProveedor();
				boolean entro = false;
							
				// busca si esta el proveedor cargado en alguna OC
				for(OrdenCompra oc: loc){
					if(oc.getProveedor() == itrod.getProveedor()) {
						oc.agregarOPedido(op);
						oc.agregaItems(itrod );
						entro = true;
					}							
				}
				//si no existia la crea con el rodamiento como primero
				if(!entro){
					OrdenCompra oc = new OrdenCompra();
					oc.setFecha(fecha);
					oc.setProveedor(pr);
					oc.agregarOPedido(op);
					oc.agregaItems(itrod );
					loc.add(oc);
				}
			}		
		}
		
		// persiste las OC
		for(OrdenCompra oc: loc){
			
			// quedan pendientes de recepción de mercadería
			oc.setEstado("PEN");
			HibernateDAO.getInstancia().persistir(oc);
			
			/*
			 * persistir en xml de proveedor y lista de precios
			 * 
			 * 			
			 */
		}
	}
}


