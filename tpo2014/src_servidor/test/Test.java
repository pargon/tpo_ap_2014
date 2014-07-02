package test;

import hbt.dao.HibernateDAO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		// retornar proveeedor-item
		
		
		
		
			// recorre ordenes de pedido pendientes
			List<OrdenPedido> lop;
			List<Proveedor> lpr = new ArrayList<Proveedor>();
			
			lop = OrdenPedidoSRV.getinstancia().getListaPendientes();	
			OrdenCompra oc;
			Date fecha = new java.util.Date();
			
			// recorre las ordenes de pedido pendientes
			for(OrdenPedido op: lop){
				List<ItemCotizacion> lro = op.getListaRod();
				
				// recorre los rodamientos y guarda rodamiento-proveedor
				for(ItemCotizacion itrod: lro){
					Proveedor pr = itrod.getListaPrecios().getProveedor();
			
					// guarda proveedor
					if (lpr.indexOf(pr) == 0){
						lpr.add(pr);
						oc = new OrdenCompra();
						oc.setFecha(fecha);
					}
				}
				
			}
			; 
	}

}


