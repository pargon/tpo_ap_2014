package rmi.controller;

import hbt.dao.HibernateCotizacionRodamientoDAO;
import hbt.dao.HibernateFacturaDAO;
import hbt.dao.HibernateItemRodamientoDAO;
import hbt.dao.HibernateSolicitudCotizacionDAO;

import java.io.File;
import java.io.FileInputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import model.Cliente;
import model.ClienteSRV;
import model.CotizacionRodamiento;
import model.CotizacionRodamientoSRV;
import model.Factura;
import model.FacturaSRV;
import model.ItemCotizacion;
import model.ItemRodamiento;
import model.ItemSolicitudCotizacion;
import model.ListaPrecios;
import model.ListaPreciosSRV;
import model.Marca;
import model.MarcaSRV;
import model.Proveedor;
import model.ProveedorSRV;
import model.Rodamiento;
import model.RodamientoSRV;
import model.SolicitudCotizacion;
import model.SolicitudCotizacionSRV;
import model.Usuario;
import model.UsuarioSRV;
import beans.BeansCliente;
import beans.BeanCotizacionRodamiento;
import beans.BeanFactura;
import beans.BeanItemCotizacionRodamiento;
import beans.BeanItemRodamiento;
import beans.BeanListaPrecios;
import beans.BeanMarca;
import beans.BeanOficinaVenta;
import beans.BeanPerfil;
import beans.BeanProveedor;
import beans.BeanRodamiento;
import beans.BeanSolicitudCotizacion;
import beans.BeanUsuario;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class RMIController extends UnicastRemoteObject implements TDAManejoDatos {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RMIController() throws RemoteException {
		super();
		
	}

	@Override
	public boolean setCliente(BeansCliente BeansCliente) throws RemoteException {
		System.out.println("Alta Cliente en Servidor!");
		Cliente c = ClienteSRV.getinstancia().fromBean(BeansCliente);
		System.out.println(c.getCuit());
		ClienteSRV.getinstancia().guardar(c);
		return true;
	}
	
	@Override
	public boolean updateCliente(BeansCliente BeansCliente)
			throws RemoteException {
		System.out.println("Modif. Cliente en Servidor!");
		Cliente c = ClienteSRV.getinstancia().fromBean(BeansCliente);
		return ClienteSRV.getinstancia().actualizarCliente(c);
	}

	@Override
	public boolean deleteCliente(BeansCliente BeansCliente)
			throws RemoteException {
		System.out.println("Baja Cliente en Servidor!");
		Cliente cliente = ClienteSRV.getinstancia().fromBean(BeansCliente);
		return ClienteSRV.getinstancia().borrarCliente(cliente);
	}
	
	@Override
	public boolean setProveedor(BeanProveedor beanProveedor)
			throws RemoteException {
		System.out.println("Alta Proveedor en Servidor!");
		Proveedor p = ProveedorSRV.getinstancia().fromBean(beanProveedor);
		return ProveedorSRV.getinstancia().guardar(p);
	}

	@Override
	public boolean deleteProveedor(BeanProveedor beanProveedor)
			throws RemoteException {
		System.out.println("Baja Proveedor en Servidor!");
		Proveedor p = ProveedorSRV.getinstancia().fromBean(beanProveedor);
		return ProveedorSRV.getinstancia().borrarProveedor(p);
	}

	@Override
	public boolean updateProveedor(BeanProveedor beanProveedor)
			throws RemoteException {
		System.out.println("Modificacion Proveedor en Servidor!");
		Proveedor p = ProveedorSRV.getinstancia().fromBean(beanProveedor);
		return ProveedorSRV.getinstancia().actualizarProveedor(p);
	}

	public List<BeanMarca> getListaMarcas()throws RemoteException{
		System.out.println("Levantar marcas desde Servidor!");
		List<BeanMarca> beanMarcas = new ArrayList<BeanMarca>(); 
		List<Marca> marcas = MarcaSRV.getinstancia().levantarMarcas();
		for(Marca marca: marcas ){
			BeanMarca beanMarca = new BeanMarca();
			beanMarca.setDescripcion(marca.getMarcaId().getDescripcion());
			beanMarca.setPais(marca.getMarcaId().getPais());
			beanMarcas.add(beanMarca);
		}
		return beanMarcas;
	}

	@Override
	public boolean setRodamiento(BeanRodamiento beanRodamiento) throws RemoteException {
		System.out.println("Alta Rodamiento en Servidor!");
		Rodamiento rodamiento = RodamientoSRV.getinstancia().fromBean(beanRodamiento);
		RodamientoSRV.getinstancia().guardar(rodamiento);
		return true;
	}
	
	@Override
	public Map<Integer,List<String>> getRodamientosByCriterio(BeanRodamiento beanRodamiento)
			throws RemoteException {
		System.out.println("Busqueda de Rodamientos en Servidor!");
		Rodamiento rodamiento = RodamientoSRV.getinstancia().fromBean(beanRodamiento);
		Map<Integer,List<String>> mapRodamientos = new HashMap<Integer, List<String>>();
		List<Rodamiento> rodamientos = RodamientoSRV.getinstancia().getRodamientosByCriterio(rodamiento);
		for(int i=0;i<rodamientos.size();i++){
				List<String> rodString = new ArrayList<String>();
//				rodString.add(rodamientos.get(i).getCodigo());
				rodString.add(rodamientos.get(i).getRodamientoId().getCodigo());
				rodString.add(rodamientos.get(i).getTipo());
				rodString.add(Float.toString(rodamientos.get(i).getMedida()));
//				rodString.add(rodamientos.get(i).getMarca().toString());
				rodString.add(rodamientos.get(i).getRodamientoId().getMarca().toString());
				rodString.add(rodamientos.get(i).getCaracteristicas());
				mapRodamientos.put(i, rodString);
		}
		return mapRodamientos;
	}
	
	@Override
	public boolean deleteRodamiento(BeanRodamiento beanRodamiento)
			throws RemoteException {
		System.out.println("Baja Rodamiento en Servidor!");
		Rodamiento rodamiento = RodamientoSRV.getinstancia().fromBean(beanRodamiento);
		RodamientoSRV.getinstancia().borrarRodamiento(rodamiento);
		return true;
	}

	@Override
	public boolean updateRodamiento(BeanRodamiento beanRodamientoNuevo)
			throws RemoteException {
		System.out.println("Modif. Rodamiento en Servidor!");
		Rodamiento rNuevo = RodamientoSRV.getinstancia().fromBean(beanRodamientoNuevo);
		RodamientoSRV.getinstancia().actualizarRodamiento(rNuevo);
		return true;
	}

	@Override
	public BeanUsuario loguearUsuario(BeanUsuario beanUsuario)
			throws RemoteException {
		System.out.println("Logueando al usuario: " + beanUsuario.getUsername());
		Usuario user = UsuarioSRV.getinstancia().loguearUsuario(beanUsuario);
		
		if(user!=null){
			BeanPerfil beanPerfil = new BeanPerfil();
			beanPerfil.setCliente(user.getPerfil().getCliente());
			beanPerfil.setNombre(user.getPerfil().getNombre());
			beanPerfil.setProveedor(user.getPerfil().getProveedor());
			beanPerfil.setRodamiento(user.getPerfil().getRodamiento());
			BeanUsuario beanUser = new BeanUsuario();
			beanUser.setPerfil(beanPerfil);
			beanUser.setId(user.getId());
			beanUser.setNombre(user.getNombre());
			beanUser.setPassword(user.getPassword());
			beanUser.setUsername(user.getUsername());
			
			if(user.getOficinaVenta() != null){
				BeanOficinaVenta beanOficinaVenta = new BeanOficinaVenta();
				beanOficinaVenta.setCiudad(user.getOficinaVenta().getCiudad());
				beanOficinaVenta.setId(user.getOficinaVenta().getId());
				beanOficinaVenta.setNombre(user.getOficinaVenta().getNombre());
				beanOficinaVenta.setTelefono(user.getOficinaVenta().getTelefono());
				beanUser.setOficinaVenta(beanOficinaVenta);
			}
			return beanUser;
		}
		else
			return null;
	}
	
	
//	public ListPrecio obtenerListaComparativa(){
//		
//	}
	
	
	
	@Override
	public BeanCotizacionRodamiento mostrarComparativaPrecios(
			BeanSolicitudCotizacion beanSolicitudCotizacion) throws RemoteException {

		List<ListaPrecios> listasPreciosProveedores = ListaPreciosSRV.getinstancia().levantarListasPrecios();
		SolicitudCotizacion solicitudCotizacion = SolicitudCotizacionSRV.getinstancia().fromBean(beanSolicitudCotizacion);
		BeanCotizacionRodamiento beanCotizacionRodamiento = new BeanCotizacionRodamiento();

		List<BeanItemCotizacionRodamiento> listaBeanItemsCotizacionRodamiento = new ArrayList<BeanItemCotizacionRodamiento>();
		
		
		for(ItemSolicitudCotizacion itemSolicitudCotizacion : solicitudCotizacion.getItemsSolicitudCotizacion()){
			BeanItemCotizacionRodamiento beanItemCotizacionRodamiento = new BeanItemCotizacionRodamiento();
			beanItemCotizacionRodamiento.setCantidad(itemSolicitudCotizacion.getCantidad());
			ItemRodamiento itemRodamientoSolicitud = itemSolicitudCotizacion.getItemRodamiento();
			boolean primeraVez = true;
			for (ListaPrecios listaPrecio : listasPreciosProveedores) {
				BeanProveedor beanProveedor= new BeanProveedor();
				beanProveedor.setCuit(listaPrecio.getProveedor().getCuit());
				beanProveedor.setDireccion(listaPrecio.getProveedor().getDireccion());
				beanProveedor.setRazonSocial(listaPrecio.getProveedor().getRazonSocial());
				beanProveedor.setTelefono(listaPrecio.getProveedor().getTelefono());
				
				BeanListaPrecios beanListaPrecios = new BeanListaPrecios();
				beanListaPrecios.setBeanProveedor(beanProveedor);
				beanListaPrecios.setId(listaPrecio.getId());
				
				
				for (ItemRodamiento itemRodamientoListaPrecio : listaPrecio.getItemsRodamiento()) {
					BeanMarca beanMarca = new BeanMarca();
					beanMarca.setDescripcion(itemRodamientoListaPrecio.getRodamiento().getRodamientoId().getMarca().getMarcaId().getDescripcion());
					beanMarca.setPais(itemRodamientoListaPrecio.getRodamiento().getRodamientoId().getMarca().getMarcaId().getPais());
					BeanRodamiento beanRodamiento = new BeanRodamiento();
					beanRodamiento.setBeanMarca(beanMarca);
					beanRodamiento.setCodigo(itemRodamientoListaPrecio.getRodamiento().getRodamientoId().getCodigo());
					beanRodamiento.setMedida(String.valueOf(itemRodamientoListaPrecio.getRodamiento().getMedida()));
					beanRodamiento.setCaracteristicas(itemRodamientoListaPrecio.getRodamiento().getCaracteristicas());
					beanRodamiento.setTipo(itemRodamientoListaPrecio.getRodamiento().getTipo());
					
					BeanItemRodamiento beanItemRodamiento = new BeanItemRodamiento();
					beanItemRodamiento.setBeanRodamiento(beanRodamiento);
					beanItemRodamiento.setPrecio(itemRodamientoListaPrecio.getPrecio());
					
					beanListaPrecios.agregarBeanItemRodamiento(beanItemRodamiento);
				}
				/** La primera vez lo hice porque necesitaba cargar en la lista de precios todos sus articulos por las dudas , ahora en esta iteracion hago la mierda del precio**/
				for (ItemRodamiento itemRodamientoListaPrecio : listaPrecio.getItemsRodamiento()) {
					
					BeanMarca beanMarca = new BeanMarca();
					beanMarca.setDescripcion(itemRodamientoListaPrecio.getRodamiento().getRodamientoId().getMarca().getMarcaId().getDescripcion());
					beanMarca.setPais(itemRodamientoListaPrecio.getRodamiento().getRodamientoId().getMarca().getMarcaId().getPais());
					BeanRodamiento beanRodamiento = new BeanRodamiento();
					beanRodamiento.setBeanMarca(beanMarca);
					beanRodamiento.setCodigo(itemRodamientoListaPrecio.getRodamiento().getRodamientoId().getCodigo());
					beanRodamiento.setMedida(String.valueOf(itemRodamientoListaPrecio.getRodamiento().getMedida()));
					beanRodamiento.setCaracteristicas(itemRodamientoListaPrecio.getRodamiento().getCaracteristicas());
					beanRodamiento.setTipo(itemRodamientoListaPrecio.getRodamiento().getTipo());
					
					BeanItemRodamiento beanItemsRodamientoFinal = new BeanItemRodamiento();
					
					beanItemsRodamientoFinal.setBeanRodamiento(beanRodamiento);
					
					
					if(itemRodamientoListaPrecio.getRodamiento().getRodamientoId().getMarca().getMarcaId().getDescripcion().equalsIgnoreCase(itemRodamientoSolicitud.getRodamiento().getRodamientoId().getMarca().getMarcaId().getDescripcion())
							&& itemRodamientoListaPrecio.getRodamiento().getRodamientoId().getMarca().getMarcaId().getPais().equalsIgnoreCase(itemRodamientoSolicitud.getRodamiento().getRodamientoId().getMarca().getMarcaId().getPais()) 
							&& itemRodamientoListaPrecio.getRodamiento().getRodamientoId().getCodigo().equalsIgnoreCase(itemRodamientoSolicitud.getRodamiento().getRodamientoId().getCodigo())){
						
						if(primeraVez){
							itemRodamientoSolicitud.setPrecio(itemRodamientoListaPrecio.getPrecio());
							
							beanItemsRodamientoFinal.setPrecio(itemRodamientoSolicitud.getPrecio());
							beanItemCotizacionRodamiento.setBeanitemsRodamiento(beanItemsRodamientoFinal);
							beanItemCotizacionRodamiento.setBeanListaPrecios(beanListaPrecios);
						}else{
							if(itemRodamientoSolicitud.getPrecio()>itemRodamientoListaPrecio.getPrecio()){
							
								itemRodamientoSolicitud.setPrecio(itemRodamientoListaPrecio.getPrecio());

								beanItemsRodamientoFinal.setPrecio(itemRodamientoSolicitud.getPrecio());
								beanItemCotizacionRodamiento.setBeanitemsRodamiento(beanItemsRodamientoFinal);
								beanItemCotizacionRodamiento.setBeanListaPrecios(beanListaPrecios);
							}
						}
						
						
					}
					
				}
				
				primeraVez = false;
			}
			listaBeanItemsCotizacionRodamiento.add(beanItemCotizacionRodamiento);
		}
		
		beanCotizacionRodamiento.setBeanitemsCotizacion(listaBeanItemsCotizacionRodamiento);
		beanCotizacionRodamiento.setFechaCotizacion(beanSolicitudCotizacion.getFecha());
		beanCotizacionRodamiento.setBeansCliente(beanSolicitudCotizacion.getBeansCliente());
		beanCotizacionRodamiento.setActiva(1);
		Date fechaVencimiento = beanSolicitudCotizacion.getFecha();    
		Calendar cal = Calendar.getInstance();   
		cal.setTime(fechaVencimiento);   
		cal.add(Calendar.DATE, 20); // agregue 20 dias
		fechaVencimiento = cal.getTime();
		beanCotizacionRodamiento.setFechaVencimiento(fechaVencimiento);
		
		return beanCotizacionRodamiento;

	}

	@Override
	public boolean cargarListaPrecos(BeanProveedor beanProveedor, File file) throws RemoteException {
		try {
			Proveedor proveeedor = ProveedorSRV.getinstancia().levantarProveedor(beanProveedor.getCuit());
			XStream xstream = new XStream(new DomDriver());
			xstream.alias("ListaPrecios", ListaPrecios.class);
			xstream.alias("ItemRodamiento", ItemRodamiento.class);
			xstream.alias("Rodamiento", Rodamiento.class);
			xstream.alias("Proveedor", Proveedor.class);
			ListaPrecios listaPrecios = (ListaPrecios) xstream.fromXML(new FileInputStream(file));
			listaPrecios.setProveedor(proveeedor);
			guardarItemsRodamientoListaPrecio(listaPrecios);
			guardarLista(listaPrecios);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
		
	}

	private void guardarLista(ListaPrecios listaPrecios) throws Exception{

		ListaPreciosSRV.getinstancia().guardarLista(listaPrecios);
		
	}	
	
	public List<BeansCliente> getListaClientes()throws RemoteException{
		System.out.println("Levantar clientes desde Servidor!");
		List<BeansCliente> BeansClientes = new ArrayList<BeansCliente>(); 
		List<Cliente> clientes = ClienteSRV.getinstancia().levantarClientes();
		for(Cliente cliente: clientes ){
			BeansCliente BeansCliente = new BeansCliente();
			BeansCliente.setContacto(cliente.getContacto());
			BeansCliente.setCuit(cliente.getCuit());
			BeansCliente.setPorcentajeDesc(cliente.getPorcentajeDesc());
			BeansCliente.setRazonSocial(cliente.getRazonSocial());
			BeansCliente.setTelefono(cliente.getTelefono());
			BeansClientes.add(BeansCliente);
		}
		return BeansClientes;
	}
	
	
	
	public void guardarItemsRodamientoListaPrecio(ListaPrecios lp) throws Exception{
		for (Iterator<ItemRodamiento> it = lp.getItemsRodamiento().iterator(); it.hasNext();){
			ItemRodamiento itr = it.next();
			guardarItemRodamiento(itr);
		}
			
	}
	
	public void guardarItemRodamiento (ItemRodamiento itr){
		HibernateItemRodamientoDAO.getInstancia().guardar(itr);
	}

	@Override
	public List<BeansCliente> buscarClientesCriterios(String nombreCliente,
			String cuitCliente) throws RemoteException {
		
		List<BeansCliente> BeansClientes = new ArrayList<BeansCliente>(); 
		List<Cliente> clientes = ClienteSRV.getinstancia().buscarClientes(nombreCliente,cuitCliente);
		for(Cliente cliente: clientes ){
			BeansCliente BeansCliente = new BeansCliente();
			BeansCliente.setContacto(cliente.getContacto());
			BeansCliente.setCuit(cliente.getCuit());
			BeansCliente.setPorcentajeDesc(cliente.getPorcentajeDesc());
			BeansCliente.setRazonSocial(cliente.getRazonSocial());
			BeansCliente.setTelefono(cliente.getTelefono());
			BeansClientes.add(BeansCliente);
		}
		return BeansClientes;
	}

	@Override
	public boolean guardarCotizacion(
			BeanCotizacionRodamiento beanCotizacionRodamiento) throws RemoteException {
		CotizacionRodamiento cotizacionRodamiento = CotizacionRodamientoSRV.getinstancia().fromBean(beanCotizacionRodamiento);
		return HibernateCotizacionRodamientoDAO.getInstancia().guardarCotizacion(cotizacionRodamiento);
		
	}

	@Override
	public List<BeanCotizacionRodamiento> levantarCotizaciones(String numeroCuit)
			throws RemoteException {
		List<BeanCotizacionRodamiento> beanCotizaciones = new ArrayList<BeanCotizacionRodamiento>(); 
		List<CotizacionRodamiento> cotizaciones = CotizacionRodamientoSRV.getinstancia().levantarCotizaciones(numeroCuit);
		for(CotizacionRodamiento cotizacion: cotizaciones ){
			BeanCotizacionRodamiento beanCotizacionRodamiento = new BeanCotizacionRodamiento();
			beanCotizacionRodamiento.setActiva(cotizacion.getActiva());
			beanCotizacionRodamiento.setFechaCotizacion(cotizacion.getFechaCotizacion());
			beanCotizacionRodamiento.setFechaVencimiento(cotizacion.getFechaVencimiento());
			beanCotizacionRodamiento.setId(cotizacion.getId());
			
			Cliente cliente = cotizacion.getCliente();
			BeansCliente BeansCliente = new BeansCliente();
			BeansCliente.setContacto(cliente.getContacto());
			BeansCliente.setCuit(cliente.getCuit());
			BeansCliente.setPorcentajeDesc(cliente.getPorcentajeDesc());
			BeansCliente.setRazonSocial(cliente.getRazonSocial());
			BeansCliente.setTelefono(cliente.getTelefono());
			beanCotizacionRodamiento.setBeansCliente(BeansCliente);
			
			List<BeanItemCotizacionRodamiento> beanItemsCotizacion = new ArrayList<BeanItemCotizacionRodamiento>();
			for (ItemCotizacion itemCotizacionRodamiento : cotizacion.getItemsCotizacion()) {
				BeanItemCotizacionRodamiento beanItemCotizacionRodamiento = new BeanItemCotizacionRodamiento();
				beanItemCotizacionRodamiento.setCantidad(itemCotizacionRodamiento.getCantidad());
				
				BeanMarca beanMarca = new BeanMarca();
				beanMarca.setDescripcion(itemCotizacionRodamiento.getItemRodamiento().getRodamiento().getRodamientoId().getMarca().getMarcaId().getDescripcion());
				beanMarca.setPais(itemCotizacionRodamiento.getItemRodamiento().getRodamiento().getRodamientoId().getMarca().getMarcaId().getPais());
				BeanRodamiento beanRodamiento = new BeanRodamiento();
				beanRodamiento.setBeanMarca(beanMarca);
				beanRodamiento.setCaracteristicas(itemCotizacionRodamiento.getItemRodamiento().getRodamiento().getCaracteristicas());
				beanRodamiento.setCodigo(itemCotizacionRodamiento.getItemRodamiento().getRodamiento().getRodamientoId().getCodigo());
				beanRodamiento.setMedida(String.valueOf(itemCotizacionRodamiento.getItemRodamiento().getRodamiento().getMedida()));
				beanRodamiento.setTipo(itemCotizacionRodamiento.getItemRodamiento().getRodamiento().getTipo());
				
				BeanItemRodamiento beanItemRodamiento = new BeanItemRodamiento();
				beanItemRodamiento.setBeanRodamiento(beanRodamiento);
				beanItemRodamiento.setId(itemCotizacionRodamiento.getItemRodamiento().getId());
				beanItemRodamiento.setPrecio(itemCotizacionRodamiento.getItemRodamiento().getPrecio());
				
				beanItemCotizacionRodamiento.setBeanitemsRodamiento(beanItemRodamiento);
				beanItemCotizacionRodamiento.setCantidad(itemCotizacionRodamiento.getCantidad());

				BeanListaPrecios beanListaPrecios = new BeanListaPrecios();
				
				BeanProveedor beanProveedor = new BeanProveedor();
				beanProveedor.setCuit(itemCotizacionRodamiento.getListaPrecios().getProveedor().getCuit());
				beanProveedor.setDireccion(itemCotizacionRodamiento.getListaPrecios().getProveedor().getDireccion());
				beanProveedor.setRazonSocial(itemCotizacionRodamiento.getListaPrecios().getProveedor().getRazonSocial());
				beanProveedor.setTelefono(itemCotizacionRodamiento.getListaPrecios().getProveedor().getTelefono());
				beanListaPrecios.setBeanProveedor(beanProveedor);
				beanListaPrecios.setId(itemCotizacionRodamiento.getListaPrecios().getId());
				for(ItemRodamiento itemRodamientoListaPrecio : itemCotizacionRodamiento.getListaPrecios().getItemsRodamiento()){
					BeanItemRodamiento beanItemRodamiento2 = new BeanItemRodamiento();
					
					BeanMarca beanMarca2 = new BeanMarca();
					beanMarca2.setDescripcion(itemRodamientoListaPrecio.getRodamiento().getRodamientoId().getMarca().getMarcaId().getDescripcion());
					beanMarca2.setPais(itemRodamientoListaPrecio.getRodamiento().getRodamientoId().getMarca().getMarcaId().getPais());
					BeanRodamiento beanRodamiento2 = new BeanRodamiento();
					beanRodamiento2.setBeanMarca(beanMarca);
					beanRodamiento2.setCaracteristicas(itemRodamientoListaPrecio.getRodamiento().getCaracteristicas());
					beanRodamiento2.setCodigo(itemRodamientoListaPrecio.getRodamiento().getRodamientoId().getCodigo());
					beanRodamiento2.setMedida(String.valueOf(itemRodamientoListaPrecio.getRodamiento().getMedida()));
					beanRodamiento2.setTipo(itemRodamientoListaPrecio.getRodamiento().getTipo());
					
					beanItemRodamiento2.setBeanRodamiento(beanRodamiento2);
					beanItemRodamiento2.setId(itemRodamientoListaPrecio.getId());
					beanItemRodamiento2.setPrecio(itemRodamientoListaPrecio.getPrecio());
					beanListaPrecios.agregarBeanItemRodamiento(beanItemRodamiento2);
				}
				beanItemCotizacionRodamiento.setBeanListaPrecios(beanListaPrecios);
				beanItemsCotizacion.add(beanItemCotizacionRodamiento);
			}
			beanCotizacionRodamiento.setBeanitemsCotizacion(beanItemsCotizacion);
			beanCotizaciones.add(beanCotizacionRodamiento);
		}
		
		return beanCotizaciones;
	}

	@Override
	public boolean generarFactura(BeanCotizacionRodamiento beanCotizacionRodamiento) throws RemoteException {
		BeanFactura beanFactura = new BeanFactura();
		beanFactura.setFecha(new Date());
		beanCotizacionRodamiento.setActiva(0);
		Float total = new Float("0");
		for(BeanItemCotizacionRodamiento beanItemCotizacionRodamiento : beanCotizacionRodamiento.getBeanitemsCotizacion()){
			Integer cant = beanItemCotizacionRodamiento.getCantidad();
			Float precio = beanItemCotizacionRodamiento.getBeanitemsRodamiento().getPrecio();
			total += precio * cant;
		}
		beanFactura.setTotal(total*(1-(beanCotizacionRodamiento.getBeansCliente().getPorcentajeDesc()/100)));
		beanFactura.setBeanCotizacion(beanCotizacionRodamiento);
		
		CotizacionRodamiento cotizacionRodamiento = CotizacionRodamientoSRV.getinstancia().fromBean(beanCotizacionRodamiento);
		Factura factura = FacturaSRV.getinstancia().fromBean(beanFactura);
		
		boolean modificacionExitosa = HibernateCotizacionRodamientoDAO.getInstancia().actualizarEstadoCotizacion(cotizacionRodamiento);
		boolean generacionExitosa = HibernateFacturaDAO.getInstancia().guardarFactura(factura);
		return (modificacionExitosa && generacionExitosa);
	}

	@Override
	public BeanProveedor buscarProveedor(String cuit) throws RemoteException {
		BeanProveedor beanProveedor = new BeanProveedor();
		try {
			Proveedor proveedor = ProveedorSRV.getinstancia().levantarProveedor(cuit);
			beanProveedor.setCuit(proveedor.getCuit());
			beanProveedor.setDireccion(proveedor.getDireccion());
			beanProveedor.setRazonSocial(proveedor.getRazonSocial());
			beanProveedor.setTelefono(proveedor.getTelefono());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return beanProveedor; 
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
	
}
	

