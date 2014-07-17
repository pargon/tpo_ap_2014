package beans;

import java.util.Date;

public class BeansRemito {

	private Date fecha;
	private BeansCliente cliente;
	private BeansCotizacionRodamiento cotizacion;
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BeansCliente getCliente() {
		return cliente;
	}
	public void setCliente(BeansCliente cliente) {
		this.cliente = cliente;
	}
	public BeansCotizacionRodamiento getCotizacion() {
		return cotizacion;
	}
	public void setCotizacion(BeansCotizacionRodamiento cotizacion) {
		this.cotizacion = cotizacion;
	}
}
