package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BeanListaPrecios implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private List<BeansItemRodamiento> beanItemsRodamiento = new ArrayList<BeansItemRodamiento>();
	private BeansProveedor beanProveedor;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<BeansItemRodamiento> getItemsRodamiento() {
		return beanItemsRodamiento;
	}
	public void setItemsRodamiento(List<BeansItemRodamiento> itemsRodamiento) {
		this.beanItemsRodamiento = itemsRodamiento;
	}
	public BeansProveedor getBeanProveedor() {
		return beanProveedor;
	}
	public void setBeanProveedor(BeansProveedor beanProveedor) {
		this.beanProveedor = beanProveedor;
	}
	public void agregarBeanItemRodamiento(BeansItemRodamiento beanItemRodamiento){
		this.beanItemsRodamiento.add(beanItemRodamiento);
	}
	
}
