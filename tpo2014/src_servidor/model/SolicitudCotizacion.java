package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SolicitudCotizacion {
	
	private List<ItemSolicitudCotizacion> itemsSolicitudCotizacion = new ArrayList<ItemSolicitudCotizacion>();
	private Date fecha;
	private Cliente cliente;
	
	public List<ItemSolicitudCotizacion> getItemsSolicitudCotizacion() {
		return itemsSolicitudCotizacion;
	}
	public void setItemsSolicitudCotizacion(
			List<ItemSolicitudCotizacion> itemsSolicitudCotizacion) {
		this.itemsSolicitudCotizacion = itemsSolicitudCotizacion;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	
	
}
