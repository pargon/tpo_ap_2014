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
		for(ListaPrecios lp : lprecios){
			List<ItemRodamiento> irs = lp.getItemsRodamiento();
			for(int i=0;i<liscaux.size();i++){
				for(int j=0;j<irs.size();j++){
					if(liscaux.get(i).getRodamiento().getRodamientoId().getCodigo() == irs.get(j).getRodamiento().getRodamientoId().getCodigo()){
						
						//el id de itemRoda se tiene q generar automaticamente
						ItemRodamiento itemRoda = new ItemRodamiento();
						itemRoda.setRodamiento(liscaux.get(i).getRodamiento());
						itemRoda.setPrecio(irs.get(j).getPrecio());
						itemRoda.setCantidad(liscaux.get(i).getCantidad());
						itemRoda.setProveedor(irs.get(j).getProveedor());
						//ItemCotizacion itcot = new ItemCotizacion();
						//itcot.setItemRodamiento(itemRoda);
						
						//si es la primera vez que colecto los items no los puedo comparar, los agrego directamente
						if(lraux.get(j) == null)
							lraux.add(itemRoda);
						else
							if(itemRoda.getPrecio() < lraux.get(i).getPrecio()){
								lraux.get(i).setPrecio(itemRoda.getPrecio());
								lraux.get(i).setProveedor(itemRoda.getProveedor());
							}
						j=irs.size();
						
						
					}
				}	
			}
		}
		cotizacionRodamiento.setItemsRodamiento(lraux);
		//Falta calcular el precio total...aunque en el xml no lo indica. Creo que lo sacamos a la mierda mejor...
		
		new HibernateCotizacionRodamientoDAO().guardarCotizacionRodamiento(cotizacionRodamiento);		
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
			List<ItemCotizacion> lro = op.getListaRod();
			
			// recorre los rodamientos
			for(ItemCotizacion itrod: lro){
				Proveedor pr = itrod.getListaPrecios().getProveedor();
				boolean entro = false;
							
				// busca si esta el proveedor cargado en alguna OC
				for(OrdenCompra oc: loc){
					if(oc.getProveedor() == itrod.getListaPrecios().getProveedor()){
						oc.agregarOPedido(op);
						oc.agregaItems(itrod.getItemRodamiento() );
						entro = true;
					}							
				}
				//si no existia la crea con el rodamiento como primero
				if(!entro){
					OrdenCompra oc = new OrdenCompra();
					oc.setFecha(fecha);
					oc.setProveedor(pr);
					oc.agregarOPedido(op);
					oc.agregaItems(itrod.getItemRodamiento() );
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
	
	public void recepcionMercaderia(int idOrdenCompra) throws RemoteException {
		// confirma la recepcion de la OC
		OrdenCompra oc = OrdenCompraSRV.getinstancia().confimarRec(idOrdenCompra);

		// crea remitos para ODV
		Remito rem;
		Date fecha = new Date();
		
		// recorre los pedidos de la OC
		List<OrdenPedido> peds = oc.getPedidos();
		for(OrdenPedido op: peds){
			
			// crea remitos en funcion de la OC para ser enviados a la ODV
			rem = new Remito();
			rem.setCliente(op.getCliente());
			rem.setFecha(fecha);
			rem.setItems(oc.getItemsOC());
			
			HibernateDAO.getInstancia().persistir(rem);
			
			/*
			 * persistir en xml los remitos
			 * 
			 * 			
			 */
			
		}
	}
}
	

