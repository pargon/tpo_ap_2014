package RMI.controller;

import hbt.dao.HibernateCotizacionRodamientoDAO;
import hbt.dao.HibernateDAO;
import hbt.dao.HibernateOPedidoDAO;
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
import model.Factura;
import model.FacturaSRV;
import model.ItemCotizacion;
import model.ItemRodamiento;
import model.ItemSolicitudCotizacion;
import model.ListaPrecios;
import model.ListaPreciosSRV;
import model.OrdenCompra;
import model.OrdenCompraSRV;
import model.OrdenPedido;
import model.OrdenPedidoSRV;
import model.Proveedor;
import model.Remito;
import model.RemitoSRV;
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
	public int guardarSolicitudCotizacion(BeanSolicitudCotizacion beanSolicitudCotizacion)
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
		
		for(int i=0;i<liscaux.size();i++){
			
			//el id de itemRoda se tiene q generar automaticamente
			ItemRodamiento itemRoda = new ItemRodamiento();
			itemRoda.setRodamiento(liscaux.get(i).getRodamiento());
			itemRoda.setCantidad(liscaux.get(i).getCantidad());
			itemRoda.setPrecio(0);
			
			lraux.add(itemRoda);
			
			for(ListaPrecios lp : lprecios){
				List<ItemRodamiento> irs = lp.getItemsRodamiento();
					

				for(int j=0;j<irs.size();j++){
					if(liscaux.get(i).getRodamiento().getRodamientoId().getCodigo().equals( irs.get(j).getRodamiento().getRodamientoId().getCodigo() )){
						
						
						if(itemRoda.getPrecio() > irs.get(j).getPrecio() || itemRoda.getPrecio() == 0){
							lraux.get(i).setPrecio(irs.get(j).getPrecio());
							lraux.get(i).setProveedor(lp.getProveedor());
						}
						j=irs.size();
						
						
					}
				}	
			}
		}
		cotizacionRodamiento.setItemsRodamiento(lraux);
		//Falta calcular el precio total...aunque en el xml no lo indica. Creo que lo sacamos a la mierda mejor...
		
		new HibernateCotizacionRodamientoDAO().guardarCotizacionRodamiento(cotizacionRodamiento);
		
		System.out.println("Crea Cotización: "+ cotizacionRodamiento.getId());
		
		return cotizacionRodamiento.getId();	
	}
	
	public void crearOrdenCompra() throws RemoteException {
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
			
			op.setEstado("OC");
		}
		
		// persiste las OC
		for(OrdenCompra oc: loc){
			
			// quedan pendientes de recepción de mercadería
			oc.setEstado("PEN");
			HibernateDAO.getInstancia().persistir(oc);
			
			System.out.println("Crea O.Compra: " +oc.getId()+ " Proveedor: "+ oc.getProveedor().getRazonSocial());
			
			// persistir en xml x cada proveedor
			OrdenCompraSRV.getinstancia().newDomXML(oc);
			OrdenCompraSRV.getinstancia().saveDomXML("d:\\ordencompra.xml");
			
		}
	}
	
	public void recepcionMercaderia(int idOrdenCompra) throws RemoteException {
		// confirma la recepcion de la OC
		OrdenCompra oc = OrdenCompraSRV.getinstancia().confimarRec(idOrdenCompra);

		// crea remitos para ODV
		
		Date fecha = new Date();
		
		// recorre los pedidos de la OC
		List<OrdenPedido> peds = oc.getPedidos();
		for(OrdenPedido op: peds){
			
			// crea remitos en funcion de la OC para ser enviados a la ODV
			Remito rem = new Remito();
			rem.setCliente(op.getCliente());
			rem.setFecha(fecha); 
			rem.setEstado("PAR"); //Deja parcial, si es q el pedido aun no se completa
			
			// asocia remito a la OP
			rem.setOp(op);
			
			// obtiene los itemRodamientos que estan en la OC y pertenecen a la OP
			List<ItemRodamiento> lro = OrdenCompraSRV.getinstancia().PedidoVSCompra(oc, op);
						
			// copia detalle al remito el detalle que coincide entre pedido y compra
			List<ItemRodamiento> lro2 = new ArrayList<ItemRodamiento>(); 
			for(ItemRodamiento itr: lro){
				ItemRodamiento nitr = new ItemRodamiento();
				nitr.setCantidad(itr.getCantidad());
				nitr.setPrecio(itr.getPrecio());
				nitr.setProveedor(itr.getProveedor());
				nitr.setRodamiento(itr.getRodamiento());
				
				lro2.add(nitr);
			}
			rem.setItems(lro2);			
			
			// guarda el remito
			HibernateDAO.getInstancia().persistir(rem);

		}
		
		// chequea OP de la OC, y cambia estado si se completo. Si se completó exporta remitos asociados
		IntentaEmitirRemitos(oc);			
	}

	private void IntentaEmitirRemitos(OrdenCompra oc) {
		
		// actualiza pedidos completos
		OrdenCompraSRV.getinstancia().cumplimientoOPedido(oc);
		
		// obtiene remitos de las OP de la OC, que aún no se hayan emitido
		List<Remito> lrem = OrdenCompraSRV.getinstancia().remitosParaEmitir(oc);
		
		for(Remito rem: lrem){
						
			// cambia estado remito para poder facturarse
			rem.setEstado("PEN");
			HibernateDAO.getInstancia().persistir(rem);
				
			List<OrdenPedido> lop = new ArrayList<OrdenPedido>();
			lop.add(rem.getOp());
			
			RemitoSRV.getinstancia().newDomXML(rem, lop);
			RemitoSRV.getinstancia().saveDomXML("d:\\remito"+rem.getId() +".xml"  );
			
			// emite remitos
			System.out.println("Crea remito: " + rem.getId() + " Cliente: " + rem.getCliente().getRazonSocial());
		}		
	}

	@Override
	public void crearOrdenPedido(int idCotizacion) throws RemoteException {
		CotizacionRodamiento cr = (CotizacionRodamiento) HibernateDAO.getInstancia().get(CotizacionRodamiento.class, idCotizacion);
		OrdenPedido op = new OrdenPedido();
		op.setCliente(cr.getSolicitudCotizacion().getCliente());
		op.setCot(cr);
		op.setEstado("PEN");
		op.setFecha(new Date());
		HibernateDAO.getInstancia().persistir(op);
		
		System.out.println("Crea O.Pedido: " + op.getId() + " Cliente: "+ op.getCliente().getRazonSocial());
		
	}

	@Override
	public void facturar() throws RemoteException {
		
		List<Remito> lr;
		boolean entro = false;
		
		// recorre remitos pendientes
		String query = "Select r from Remito r where r.estado = 'PEN'";
		lr = (List<Remito>) new HibernateDAO().getInstancia().getlista(query);	

		List<Factura> lfacturas = new ArrayList<Factura>();
		// recorre los remitos pendientes
		for(Remito remito: lr){
			//primera vez
			if(lfacturas.size() == 0){
				Factura f = new Factura();
				f.setCliente(remito.getCliente());
				f.setFecha(new Date());
				f.setRemitoSolo(remito);
				lfacturas.add(f);
			}
			else
			{
				for(int i=0; i<lfacturas.size();i++){
					if(lfacturas.get(i).getCliente().equals(remito.getCliente())){
						lfacturas.get(i).setRemitoSolo(remito);
						entro = true;
					}
				}
				if(entro == false){
					Factura f2 = new Factura();
					f2.setCliente(remito.getCliente());
					f2.setFecha(new Date());
					f2.setRemitoSolo(remito);
					lfacturas.add(f2);
				}
				entro = false;
			}

		}	
		for(Factura f3 : lfacturas){
			new HibernateDAO().getInstancia().persistir(f3);
		}
	}

}
	

