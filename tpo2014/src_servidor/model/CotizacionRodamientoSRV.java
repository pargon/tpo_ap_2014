package model;

import hbt.dao.HibernateCotizacionRodamientoDAO;

import java.util.ArrayList;
import java.util.List;

import beans.BeanCotizacionRodamiento;
import beans.BeanItemCotizacion;
import beans.BeanItemCotizacionRodamiento;
import beans.BeanItemRodamiento;
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
		List<BeanItemCotizacionRodamiento>beanItemCotizacionRodamientos = beanCotizacionRodamiento.getBeanitemsCotizacion();
		List<ItemCotizacion> itemsCotizacion = new ArrayList<ItemCotizacion>();
		for (BeanItemCotizacionRodamiento beanItemCotizacionRodamiento : beanItemCotizacionRodamientos) {
			ItemCotizacion itemCotizacion = new ItemCotizacion();
			itemCotizacion.setCantidad(beanItemCotizacionRodamiento.getCantidad());
			Rodamiento r = RodamientoSRV.getinstancia().fromBean(beanItemCotizacionRodamiento.getBeanitemsRodamiento().getBeanRodamiento());
			ItemRodamiento it = new ItemRodamiento();
			it.setPrecio(beanItemCotizacionRodamiento.getBeanitemsRodamiento().getPrecio());
			it.setRodamiento(r);
			itemCotizacion.setItemRodamiento(it);
			ListaPrecios listaPrecios = ListaPreciosSRV.getinstancia().fromBean(beanItemCotizacionRodamiento.getBeanListaPrecios());
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
		List<BeanItemCotizacionRodamiento> beanItemsCotizacion = new ArrayList<BeanItemCotizacionRodamiento>();
		for (ItemCotizacion itemCotizacionRodamiento : itemCotizacionRodamientos) {
			BeanItemCotizacionRodamiento beanitemCotizacion = new BeanItemCotizacionRodamiento();
			beanitemCotizacion.setCantidad(itemCotizacionRodamiento.getCantidad());
			//Hacer RodamientoSRV.toBean
			BeanRodamiento br = RodamientoSRV.getinstancia().toBean(itemCotizacionRodamiento.getItemRodamiento().getRodamiento());
			BeanItemRodamiento bit = new BeanItemRodamiento();
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
