package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class BeansCotizacionRodamiento implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private int id;
 	private List<BeansItemCotizacionRodamiento> beanItemsCotizacion = new ArrayList<BeansItemCotizacionRodamiento>();
	private Date fechaCotizacion;
	private Date fechaVencimiento;
	private BeansCliente beanCliente;
	private Integer activa;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<BeansItemCotizacionRodamiento> getBeanitemsCotizacion() {
		return beanItemsCotizacion;
	}
	public void setBeanitemsCotizacion(
			List<BeansItemCotizacionRodamiento> beanitemsCotizacion) {
		this.beanItemsCotizacion = beanitemsCotizacion;
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
	public BeansCliente getBeanCliente() {
		return beanCliente;
	}
	public void setBeanCliente(BeansCliente beanCliente) {
		this.beanCliente = beanCliente;
	}
	public Integer getActiva() {
		return activa;
	}
	public void setActiva(Integer activa) {
		this.activa = activa;
	}
	
	
}
