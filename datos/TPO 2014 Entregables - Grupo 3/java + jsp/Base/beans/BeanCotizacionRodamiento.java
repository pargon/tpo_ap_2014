package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class BeanCotizacionRodamiento implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private int id;
 	private List<BeansItemRodamiento> beanItemsRodamiento = new ArrayList<BeansItemRodamiento>();
 	private BeanSolicitudCotizacion beanSolicitudCotizacion = new BeanSolicitudCotizacion();
	private Date fechaCotizacion;
	private int termino;
	private Integer activa;
	private int descuento;
	private int financiacionDias;
	private int recargo;
	private float precioFinal;
	
	
	

	public List<BeansItemRodamiento> getBeanItemsRodamiento() {
		return beanItemsRodamiento;
	}
	public void setBeanItemsRodamiento(List<BeansItemRodamiento> beanItemsRodamiento) {
		this.beanItemsRodamiento = beanItemsRodamiento;
	}
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
	public float getPrecioFinal() {
		return precioFinal;
	}
	public void setPrecioFinal(float precioFinal) {
		this.precioFinal = precioFinal;
	}
	public BeanSolicitudCotizacion getBeanSolicitudCotizacion() {
		return beanSolicitudCotizacion;
	}
	public void setBeanSolicitudCotizacion(
			BeanSolicitudCotizacion beanSolicitudCotizacion) {
		this.beanSolicitudCotizacion = beanSolicitudCotizacion;
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

	public Integer getActiva() {
		return activa;
	}
	public void setActiva(Integer activa) {
		this.activa = activa;
	}
	
	
}
