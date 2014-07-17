package beans;

import java.io.Serializable;

public class BeansItemCotizacionRodamiento implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private int id;
	private BeansItemRodamiento beanItemRodamiento;
	private Integer cantidad;
	private BeansListaPrecios beanListaPrecios;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BeansItemRodamiento getBeanitemsRodamiento() {
		return beanItemRodamiento;
	}
	public void setBeanitemsRodamiento(BeansItemRodamiento beanitemsRodamiento) {
		this.beanItemRodamiento = beanitemsRodamiento;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public BeansListaPrecios getBeanListaPrecios() {
		return beanListaPrecios;
	}
	public void setBeanListaPrecios(BeansListaPrecios beanListaPrecios) {
		this.beanListaPrecios = beanListaPrecios;
	}

	
}
