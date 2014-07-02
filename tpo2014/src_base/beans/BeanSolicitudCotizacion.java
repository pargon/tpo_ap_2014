package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BeanSolicitudCotizacion implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;

	private List<BeanItemSolicitudCotizacion> beanItemsSolicitudCotizacion = new ArrayList<BeanItemSolicitudCotizacion>();
	private Date fecha;
	private BeansCliente BeansCliente;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<BeanItemSolicitudCotizacion> getBeanItemsSolicitudCotizacion() {
		return beanItemsSolicitudCotizacion;
	}
	public void setBeanItemsSolicitudCotizacion(
			List<BeanItemSolicitudCotizacion> beanItemsSolicitudCotizacion) {
		this.beanItemsSolicitudCotizacion = beanItemsSolicitudCotizacion;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public BeansCliente getBeansCliente() {
		return BeansCliente;
	}
	public void setBeansCliente(BeansCliente BeansCliente) {
		this.BeansCliente = BeansCliente;
	}
	
	
	
}
