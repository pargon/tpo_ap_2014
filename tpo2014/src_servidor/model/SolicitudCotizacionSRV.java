package model;


import hbt.dao.HibernateClientesDAO;

import java.util.ArrayList;
import java.util.List;

import model.Rodamiento.RodamientoId;
import beans.BeanItemSolicitudCotizacion;
import beans.BeanSolicitudCotizacion;

public class SolicitudCotizacionSRV {
	private static SolicitudCotizacionSRV instancia;

	public static SolicitudCotizacionSRV getinstancia(){
		if (instancia == null){
			instancia = new SolicitudCotizacionSRV();
		}
		return instancia;
	}

	
	public SolicitudCotizacion fromBean(BeanSolicitudCotizacion beanSolicitudCotizacion) {
		
		SolicitudCotizacion solicitudCotizacion = new SolicitudCotizacion();
		List<ItemSolicitudCotizacion>itemSolicitudCotizacions = new ArrayList<ItemSolicitudCotizacion>();
		for(BeanItemSolicitudCotizacion beanItemSolicitudCotizacion:beanSolicitudCotizacion.getBeanItemsSolicitudCotizacion()){
			
			ItemSolicitudCotizacion itemSolicitudCotizacion = new ItemSolicitudCotizacion();
			itemSolicitudCotizacion.setCantidad(beanItemSolicitudCotizacion.getCantidad());
			
			Rodamiento rodamiento = new Rodamiento();
			rodamiento.setCaracteristicas(beanItemSolicitudCotizacion.getBeanRodamiento().getCaracteristicas());
			rodamiento.setMedida(beanItemSolicitudCotizacion.getBeanRodamiento().getMedida());
			
			Marca marca = new Marca();
			marca.setDescripcion(beanItemSolicitudCotizacion.getBeanRodamiento().getBeanMarca().getDescripcion());
			marca.setPais(beanItemSolicitudCotizacion.getBeanRodamiento().getBeanMarca().getPais());
			
			
			RodamientoId rodamientoId = new RodamientoId();
			rodamientoId.setCodigo(beanItemSolicitudCotizacion.getBeanRodamiento().getCodigo());
			rodamientoId.setMarca(marca);
			rodamiento.setRodamientoId(rodamientoId);
			rodamiento.setTipo(beanItemSolicitudCotizacion.getBeanRodamiento().getTipo());
			
			itemSolicitudCotizacion.setRodamiento(rodamiento);
			itemSolicitudCotizacions.add(itemSolicitudCotizacion);
		
		}
		
		
		Cliente cliente = null;
		// busca cliente
		List<Cliente> lcli = buscarCliente(beanSolicitudCotizacion.getBeansCliente().getRazonSocial(),
											beanSolicitudCotizacion.getBeansCliente().getCuit());
				
		if (lcli.size()> 0)
			cliente = lcli.get(0);
		else
			cliente = new Cliente();
		
		cliente.setContacto(beanSolicitudCotizacion.getBeansCliente().getContacto());
		cliente.setCuit(beanSolicitudCotizacion.getBeansCliente().getCuit());
		cliente.setPorcentajeDesc(beanSolicitudCotizacion.getBeansCliente().getPorcentajeDesc());
		cliente.setRazonSocial(beanSolicitudCotizacion.getBeansCliente().getRazonSocial());
		cliente.setTelefono(beanSolicitudCotizacion.getBeansCliente().getTelefono());
		solicitudCotizacion.setId(beanSolicitudCotizacion.getId());
		solicitudCotizacion.setCliente(cliente);
		solicitudCotizacion.setFecha(beanSolicitudCotizacion.getFecha());
		solicitudCotizacion.setItemsSolicitudCotizacion(itemSolicitudCotizacions);
		return solicitudCotizacion;
	}


	private List<Cliente> buscarCliente(String nom, String cuit) {
		
		return HibernateClientesDAO.getInstancia().buscarClientes(nom, cuit);
	}
}
