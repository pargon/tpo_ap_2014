package model;


import hbt.dao.PersistentObject;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="movimientostock")
public class MovimientoStock extends PersistentObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date fecha;
	
	@OneToOne
	private Rodamiento rodamiento;
	private String origen;
	private String destino;
	private int cantidad;
	
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
	
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public String getTipoMov() {
		if (cantidad == 0)
			return "Elimina";
		else
			if (cantidad > 0)
				return "Ingresa";
			else
				return "Egresa";
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
