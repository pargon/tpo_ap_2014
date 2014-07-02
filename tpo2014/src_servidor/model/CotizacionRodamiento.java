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
	private List<ItemCotizacion> itemsCot = new ArrayList<ItemCotizacion>();
	
	@OneToOne
	@PrimaryKeyJoinColumn
 	private SolicitudCotizacion solicitudCotizacion = new SolicitudCotizacion();
	private Date fechaCotizacion;
	private int termino;
	private Integer activa;
	private float precioFinal;

	
	
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
	public List<ItemCotizacion> getItemsRodamiento() {
		return itemsCot;
	}
	public void setItemsRodamiento(List<ItemCotizacion> itemsCot) {
		this.itemsCot = itemsCot;
	}
	public float getPrecioFinal() {
		return precioFinal;
	}
	public void setPrecioFinal(float precioFinal) {
		this.precioFinal = precioFinal;
	}
}
