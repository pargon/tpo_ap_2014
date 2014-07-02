package model;


import java.util.ArrayList;
import java.util.List;

import model.Marca.MarcaId;
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
		List<ItemSolicitudCotizacion> itemSolicitudCotizacions = new ArrayList<ItemSolicitudCotizacion>();
		for(BeanItemSolicitudCotizacion beanItemSolicitudCotizacion:beanSolicitudCotizacion.getBeanItemsSolicitudCotizacion()){
			ItemSolicitudCotizacion itemSolicitudCotizacion = new ItemSolicitudCotizacion();
			itemSolicitudCotizacion.setCantidad(beanItemSolicitudCotizacion.getCantidad());
			
			Rodamiento rodamiento = new Rodamiento();
			rodamiento.setCaracteristicas(beanItemSolicitudCotizacion.getBeanRodamiento().getCaracteristicas());
			rodamiento.setMedida(beanItemSolicitudCotizacion.getBeanRodamiento().getMedida());
			Marca marca = new Marca();
			MarcaId marcaId = new MarcaId();
			marcaId.setDescripcion(beanItemSolicitudCotizacion.getBeanRodamiento().getBeanMarca().getDescripcion());
			marcaId.setPais(beanItemSolicitudCotizacion.getBeanRodamiento().getBeanMarca().getPais());
			marca.setMarcaId(marcaId);
			RodamientoId rodamientoId = new RodamientoId();
			rodamientoId.setCodigo(beanItemSolicitudCotizacion.getBeanRodamiento().getCodigo());
			rodamientoId.setMarca(marca);
			rodamiento.setRodamientoId(rodamientoId);
			itemSolicitudCotizacion.setRodamiento(rodamiento);
			itemSolicitudCotizacions.add(itemSolicitudCotizacion);
			
			
		}
		
		Cliente cliente = new Cliente();
		cliente.setContacto(beanSolicitudCotizacion.getBeanCliente().getContacto());
		cliente.setCuit(beanSolicitudCotizacion.getBeanCliente().getCuit());
		cliente.setPorcentajeDesc(beanSolicitudCotizacion.getBeanCliente().getPorcentajeDesc());
		cliente.setRazonSocial(beanSolicitudCotizacion.getBeanCliente().getRazonSocial());
		cliente.setTelefono(beanSolicitudCotizacion.getBeanCliente().getTelefono());
		solicitudCotizacion.setCliente(cliente);
		solicitudCotizacion.setFecha(beanSolicitudCotizacion.getFecha());
		solicitudCotizacion.setId(beanSolicitudCotizacion.getId());
		solicitudCotizacion.setItemsSolicitudCotizacion(itemSolicitudCotizacions);
		return solicitudCotizacion;
	}
}
