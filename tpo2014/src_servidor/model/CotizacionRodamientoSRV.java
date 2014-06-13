package model;

import hbt.dao.HibernateCotizacionRodamientoDAO;

import java.util.ArrayList;
import java.util.List;

import beans.BeansCotizacionRodamiento;
import beans.BeansItemCotizacionRodamiento;

public class CotizacionRodamientoSRV {
	private static CotizacionRodamientoSRV instancia;

	public static CotizacionRodamientoSRV getinstancia(){
		if (instancia == null){
			instancia = new CotizacionRodamientoSRV();
		}
		return instancia;
	}

	public CotizacionRodamiento fromBean(BeansCotizacionRodamiento beanCotizacionRodamiento) {
		CotizacionRodamiento cotizacionRodamiento = new CotizacionRodamiento();
		Cliente cliente=new Cliente();
		cliente = ClienteSRV.getinstancia().fromBean(beanCotizacionRodamiento.getBeanCliente());
		cotizacionRodamiento.setCliente(cliente);
		cotizacionRodamiento.setFechaCotizacion(beanCotizacionRodamiento.getFechaCotizacion());
		cotizacionRodamiento.setFechaVencimiento(beanCotizacionRodamiento.getFechaVencimiento());
		List<BeansItemCotizacionRodamiento>beanItemCotizacionRodamientos = beanCotizacionRodamiento.getBeanitemsCotizacion();
		List<ItemCotizacion> itemsCotizacion = new ArrayList<ItemCotizacion>();
		for (BeansItemCotizacionRodamiento beanItemCotizacionRodamiento : beanItemCotizacionRodamientos) {
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
		cotizacionRodamiento.setItemsCotizacion(itemsCotizacion); 
		cotizacionRodamiento.setActiva(beanCotizacionRodamiento.getActiva());
		cotizacionRodamiento.setId(beanCotizacionRodamiento.getId());
		return cotizacionRodamiento;
	}

	public List<CotizacionRodamiento> levantarCotizaciones(String numeroCuit) {
		try{
		return HibernateCotizacionRodamientoDAO.getInstancia().levantarCotizaciones(numeroCuit);
		}catch(Exception e){
			e.toString();
			return null;
		}
	}
}
