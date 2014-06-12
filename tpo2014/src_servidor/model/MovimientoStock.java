package model;


import java.util.Date;

import util.PersistentObject;

public class MovimientoStock extends PersistentObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date fecha;
	private Rodamiento rodamiento;
	private int cantidad;
	private String tipoMov;
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Rodamiento getRodamiento() {
		return rodamiento;
	}
	public void setRodamiento(Rodamiento rodamiento) {
		this.rodamiento = rodamiento;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getTipoMov() {
		return tipoMov;
	}
	public void setTipoMov(String tipoMov) {
		this.tipoMov = tipoMov;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
