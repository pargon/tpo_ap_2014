package beans;

import java.io.Serializable;


public class BeanItemSolicitudCotizacion implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private BeanRodamiento beanRodamiento;
	private Integer cantidad;
	private float precio;
		
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public BeanRodamiento getBeanRodamiento() {
		return beanRodamiento;
	}
	public void setBeanRodamiento(BeanRodamiento beanRodamiento) {
		this.beanRodamiento = beanRodamiento;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	
}
