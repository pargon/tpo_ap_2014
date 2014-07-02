package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="itemSolicitudCotizacion")
public class ItemSolicitudCotizacion {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@OneToOne(cascade=CascadeType.ALL)
/*	@JoinColumns({
		@JoinColumn(name="codigo", referencedColumnName="codigo", updatable = false),
		@JoinColumn(name="descripcion", referencedColumnName="descripcion",  updatable = false),
		@JoinColumn(name="pais", referencedColumnName="pais", updatable = false)
		})
*/	private Rodamiento rodamiento;
	
	private Integer cantidad;
	
	public Rodamiento getRodamiento() {
		return rodamiento;
	}
	public void setRodamiento(Rodamiento Rodamiento) {
		this.rodamiento = Rodamiento;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	
	
}
