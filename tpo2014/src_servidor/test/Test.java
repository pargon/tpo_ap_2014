package test;

import hbt.dao.HibernateDAO;
import hbt.dao.HibernetCasaDAO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import model.Cliente;
import model.Factura;
import model.ItemCotizacion;
import model.ItemRodamiento;
import model.OrdenCompra;
import model.OrdenPedido;
import model.OrdenPedidoSRV;
import model.Proveedor;
import model.Remito;
import model.Rodamiento;


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
			List<ItemCotizacion> lro = op.getListaRod();
			
			// recorre los rodamientos
			for(ItemCotizacion itrod: lro){
				Proveedor pr = itrod.getListaPrecios().getProveedor();
				boolean entro = false;
							
				// busca si esta el proveedor cargado en alguna OC
				for(OrdenCompra oc: loc){
					if(oc.getProveedor() == itrod.getListaPrecios().getProveedor()){
						oc.agregaItems(itrod.getItemRodamiento() );
						entro = true;
					}							
				}
				//si no existia la crea con el rodamiento como primero
				if(!entro){
					OrdenCompra oc2 = new OrdenCompra();
					oc2.setFecha(fecha);
					oc2.setProveedor(pr);
					oc2.agregaItems(itrod.getItemRodamiento() );
					loc.add(oc2);
				}
			}		
		}
		
		// persiste las OC
		for(OrdenCompra oc: loc){
			HibernateDAO.getInstancia().persistir(oc);
			
			/*
			 * persistir en xml de proveedor y lista de precios
			 * 
			 * 			
			 */
		}
	}
}


