package model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import util.PersistentObject;

@Entity
@Table(name="remitos")
public class Remito extends PersistentObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Date fecha;
	@OneToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Cliente cliente;
	@OneToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private CotizacionRodamiento cotizacion;
	
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
	public CotizacionRodamiento getCotizacion() {
		return cotizacion;
	}
	public void setCotizacion(CotizacionRodamiento cotizacion) {
		this.cotizacion = cotizacion;
	}
	
}
