package model;

import hbt.dao.HibernateCotizacionRodamientoDAO;

import java.util.ArrayList;
import java.util.List;

import beans.BeanCotizacionRodamiento;
import beans.BeanItemCotizacion;
import beans.BeansItemCotizacionRodamiento;
import beans.BeansItemRodamiento;
import beans.BeanItemSolicitudCotizacion;
import beans.BeanListaPrecios;
import beans.BeanRodamiento;
import beans.BeanSolicitudCotizacion;

public class CotizacionRodamientoSRV {
	private static CotizacionRodamientoSRV instancia;

	public static CotizacionRodamientoSRV getinstancia(){
		if (instancia == null){
			instancia = new CotizacionRodamientoSRV();
		}
		return instancia;
	}

	public CotizacionRodamiento fromBean(BeanCotizacionRodamiento beanCotizacionRodamiento) {
		CotizacionRodamiento cotizacionRodamiento = new CotizacionRodamiento();
		cotizacionRodamiento.setFechaCotizacion(beanCotizacionRodamiento.getFechaCotizacion());
		cotizacionRodamiento.setTermino(beanCotizacionRodamiento.getTermino());
		List<BeansItemCotizacionRodamiento>BeansItemCotizacionRodamientos = beanCotizacionRodamiento.getBeanitemsCotizacion();
		List<ItemCotizacion> itemsCotizacion = new ArrayList<ItemCotizacion>();
		for (BeansItemCotizacionRodamiento BeansItemCotizacionRodamiento : BeansItemCotizacionRodamientos) {
			ItemCotizacion itemCotizacion = new ItemCotizacion();
			itemCotizacion.setCantidad(BeansItemCotizacionRodamiento.getCantidad());
			Rodamiento r = RodamientoSRV.getinstancia().fromBean(BeansItemCotizacionRodamiento.getBeanitemsRodamiento().getBeanRodamiento());
			ItemRodamiento it = new ItemRodamiento();
			it.setPrecio(BeansItemCotizacionRodamiento.getBeanitemsRodamiento().getPrecio());
			it.setRodamiento(r);
			itemCotizacion.setItemRodamiento(it);
			ListaPrecios listaPrecios = ListaPreciosSRV.getinstancia().fromBean(BeansItemCotizacionRodamiento.getBeanListaPrecios());
			itemCotizacion.setListaPrecios(listaPrecios);
			itemsCotizacion.add(itemCotizacion);
		}
		
		//Solicitudes
		BeanSolicitudCotizacion beanSolicitudCotizacion = beanCotizacionRodamiento.getBeanSolicitudCotizacion();
		SolicitudCotizacion solicitudCotizacion = new SolicitudCotizacion();
		solicitudCotizacion.setFecha(beanSolicitudCotizacion.getFecha());
		solicitudCotizacion.setId(beanSolicitudCotizacion.getId());
		Cliente c = ClienteSRV.getinstancia().fromBean(beanSolicitudCotizacion.getBeanCliente());
		solicitudCotizacion.setCliente(c);
		List<ItemSolicitudCotizacion> itemSCotizaciones = new ArrayList<ItemSolicitudCotizacion>();
		List<BeanItemSolicitudCotizacion> beanItemSolicitudCotizaciones = beanSolicitudCotizacion.getBeanItemsSolicitudCotizacion();
		for (BeanItemSolicitudCotizacion beanItemSolicitudCotizacion : beanItemSolicitudCotizaciones) {
			ItemSolicitudCotizacion itemsc = new ItemSolicitudCotizacion();
			itemsc.setCantidad(beanItemSolicitudCotizacion.getCantidad());
			Rodamiento r = RodamientoSRV.getinstancia().fromBean(beanItemSolicitudCotizacion.getBeanRodamiento());
			itemsc.setRodamiento(r);
			itemSCotizaciones.add(itemsc);
		}
		solicitudCotizacion.setItemsSolicitudCotizacion(itemSCotizaciones);
		//************
		cotizacionRodamiento.setItemsCotizacion(itemsCotizacion); 
		cotizacionRodamiento.setActiva(beanCotizacionRodamiento.getActiva());
		cotizacionRodamiento.setId(beanCotizacionRodamiento.getId());
		cotizacionRodamiento.setSolicitudCotizacion(solicitudCotizacion);
		return cotizacionRodamiento;
	}
	
	public BeanCotizacionRodamiento toBean(CotizacionRodamiento cotizacionRodamiento) {
		BeanCotizacionRodamiento beanCotizacionRodamiento = new BeanCotizacionRodamiento();
		beanCotizacionRodamiento.setFechaCotizacion(cotizacionRodamiento.getFechaCotizacion());
		beanCotizacionRodamiento.setTermino(cotizacionRodamiento.getTermino());
		List<ItemCotizacion> itemCotizacionRodamientos = cotizacionRodamiento.getItemsCotizacion();
		List<BeansItemCotizacionRodamiento> beanItemsCotizacion = new ArrayList<BeansItemCotizacionRodamiento>();
		for (ItemCotizacion itemCotizacionRodamiento : itemCotizacionRodamientos) {
			BeansItemCotizacionRodamiento beanitemCotizacion = new BeansItemCotizacionRodamiento();
			beanitemCotizacion.setCantidad(itemCotizacionRodamiento.getCantidad());
			//Hacer RodamientoSRV.toBean
			BeanRodamiento br = RodamientoSRV.getinstancia().toBean(itemCotizacionRodamiento.getItemRodamiento().getRodamiento());
			BeansItemRodamiento bit = new BeansItemRodamiento();
			bit.setPrecio(itemCotizacionRodamiento.getItemRodamiento().getPrecio());
			bit.setBeanRodamiento(br);
			beanitemCotizacion.setBeanitemsRodamiento(bit);
			//Hacer el ListaPreciosSRV.toBean
			BeanListaPrecios beanListaPrecios = ListaPreciosSRV.getinstancia().toBean(itemCotizacionRodamiento.getListaPrecios());
			beanitemCotizacion.setBeanListaPrecios(beanListaPrecios);
			beanItemsCotizacion.add(beanitemCotizacion);
		}
		
		//Solicitudes
		//Hasta aca llegue. falta toBean de toda esta parte.
		BeanSolicitudCotizacion beanSolicitudCotizacion = beanCotizacionRodamiento.getBeanSolicitudCotizacion();
		SolicitudCotizacion solicitudCotizacion = new SolicitudCotizacion();
		solicitudCotizacion.setFecha(beanSolicitudCotizacion.getFecha());
		solicitudCotizacion.setId(beanSolicitudCotizacion.getId());
		Cliente c = ClienteSRV.getinstancia().fromBean(beanSolicitudCotizacion.getBeanCliente());
		solicitudCotizacion.setCliente(c);
		List<ItemSolicitudCotizacion> itemSCotizaciones = new ArrayList<ItemSolicitudCotizacion>();
		List<BeanItemSolicitudCotizacion> beanItemSolicitudCotizaciones = beanSolicitudCotizacion.getBeanItemsSolicitudCotizacion();
		for (BeanItemSolicitudCotizacion beanItemSolicitudCotizacion : beanItemSolicitudCotizaciones) {
			ItemSolicitudCotizacion itemsc = new ItemSolicitudCotizacion();
			itemsc.setCantidad(beanItemSolicitudCotizacion.getCantidad());
			Rodamiento r = RodamientoSRV.getinstancia().fromBean(beanItemSolicitudCotizacion.getBeanRodamiento());
			itemsc.setRodamiento(r);
			itemSCotizaciones.add(itemsc);
		}
		solicitudCotizacion.setItemsSolicitudCotizacion(itemSCotizaciones);
		//************
		cotizacionRodamiento.setItemsCotizacion(itemsCotizacion); 
		cotizacionRodamiento.setActiva(beanCotizacionRodamiento.getActiva());
		cotizacionRodamiento.setId(beanCotizacionRodamiento.getId());
		cotizacionRodamiento.setSolicitudCotizacion(solicitudCotizacion);
		return cotizacionRodamiento;
	}
/*
	public List<CotizacionRodamiento> levantarCotizaciones(String numeroCuit) {
		try{
		return HibernateCotizacionRodamientoDAO.getInstancia().levantarCotizaciones(numeroCuit);
		}catch(Exception e){
			e.toString();
			return null;
		}
	}
	*/
}
