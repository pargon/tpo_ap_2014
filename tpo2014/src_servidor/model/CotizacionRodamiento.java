package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.*;

@Entity
@Table(name="Cotizaciones")
public class CotizacionRodamiento implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@OneToMany(cascade=CascadeType.ALL)
 	//private List<ItemCotizacion> itemsCotizacion = new ArrayList<ItemCotizacion>();
	private List<ItemRodamiento> itemsRodamiento = new ArrayList<ItemRodamiento>();
	@OneToOne
	@PrimaryKeyJoinColumn
 	private SolicitudCotizacion solicitudCotizacion = new SolicitudCotizacion();
	private Date fechaCotizacion;
	private int termino;
	private Integer activa;
	private float precioFinal;
	//private int descuento;
	//private int financiacionDias;
	//private int recargo;

	
	
	public SolicitudCotizacion getSolicitudCotizacion() {
		return solicitudCotizacion;
	}
	public void setSolicitudCotizacion(
			SolicitudCotizacion solicitudCotizacion) {
		this.solicitudCotizacion = solicitudCotizacion;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Date getFechaCotizacion() {
		return fechaCotizacion;
	}
	public void setFechaCotizacion(Date fechaCotizacion) {
		this.fechaCotizacion = fechaCotizacion;
	}

	public int getTermino() {
		return termino;
	}
	public void setTermino(int termino) {
		this.termino = termino;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getActiva() {
		return activa;
	}
	public void setActiva(Integer activa) {
		this.activa = activa;
	}
	public List<ItemRodamiento> getItemsRodamiento() {
		return itemsRodamiento;
	}
	public void setItemsRodamiento(List<ItemRodamiento> itemsRodamiento) {
		this.itemsRodamiento = itemsRodamiento;
	}
	public float getPrecioFinal() {
		return precioFinal;
	}
	public void setPrecioFinal(float precioFinal) {
		this.precioFinal = precioFinal;
	}
	/*
	public int getDescuento() {
		return descuento;
	}
	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}
	public int getFinanciacionDias() {
		return financiacionDias;
	}
	public void setFinanciacionDias(int financiacionDias) {
		this.financiacionDias = financiacionDias;
	}
	public int getRecargo() {
		return recargo;
	}
	public void setRecargo(int recargo) {
		this.recargo = recargo;
	}
	*/

	
	
}
