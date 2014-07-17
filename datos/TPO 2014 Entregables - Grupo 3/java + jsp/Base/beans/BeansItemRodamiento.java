package beans;
import java.io.Serializable;

public class BeansItemRodamiento implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	private BeanRodamiento beanRodamiento;
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
	public void setBeanRodamiento(BeanRodamiento rodamiento) {
		this.beanRodamiento = rodamiento;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
}
