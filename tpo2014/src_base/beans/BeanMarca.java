package beans;

import java.io.Serializable;

public class BeanMarca implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -311053319479625975L;
//	private Integer id;
	private String descripcion;
	private String pais;
	
//	public Integer getId() {
//		return id;
//	}
//	public void setId(Integer id) {
//		this.id = id;
//	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	@Override
	public String toString() {
		return ""+this.getDescripcion()+", "+ this.getPais();
	}
	
}
