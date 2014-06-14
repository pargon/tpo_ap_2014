package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Cotizaciones")
public class CotizacionRodamiento implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@OneToMany(cascade=CascadeType.ALL)
 	private List<ItemCotizacion> itemsCotizacion = new ArrayList<ItemCotizacion>();
	private Date fechaCotizacion;
	private Date fechaVencimiento;
	@ManyToOne(cascade=CascadeType.ALL)
	private Cliente cliente;
	private Integer activa;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<ItemCotizacion> getItemsCotizacion() {
		return itemsCotizacion;
	}
	public void setItemsCotizacion(List<ItemCotizacion> itemsCotizacion) {
		this.itemsCotizacion = itemsCotizacion;
	}

	public Date getFechaCotizacion() {
		return fechaCotizacion;
	}
	public void setFechaCotizacion(Date fechaCotizacion) {
		this.fechaCotizacion = fechaCotizacion;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Integer getActiva() {
		return activa;
	}
	public void setActiva(Integer activa) {
		this.activa = activa;
	}
	
	
}
