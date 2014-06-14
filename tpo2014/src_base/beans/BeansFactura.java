package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class BeansFactura {
	
	private static final long serialVersionUID = 1L;
	private Integer nroFactura;
	private Date fecha;
	private Float total;
	private BeansCotizacionRodamiento beanCotizacion;
	private List<BeansRemito> remitos = new ArrayList<BeansRemito>();
	
	public BeansFactura() {
	}
	public Integer getNroFactura() {
		return nroFactura;
	}
	public void setNroFactura(Integer nroFactura) {
		this.nroFactura = nroFactura;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Float getTotal() {
		return total;
	}
	public void setTotal(Float total) {
		this.total = total;
	}
	public BeansCotizacionRodamiento getBeanCotizacion() {
		return beanCotizacion;
	}
	public void setBeanCotizacion(BeansCotizacionRodamiento beanCotizacion) {
		this.beanCotizacion = beanCotizacion;
	}
	public List<BeansRemito> getRemitos() {
		return remitos;
	}
	public void setRemitos(List<BeansRemito> remitos) {
		this.remitos = remitos;
	}
	
}
