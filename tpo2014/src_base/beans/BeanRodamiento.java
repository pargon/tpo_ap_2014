package beans;

import java.io.Serializable;

public class BeanRodamiento implements Serializable 
{
	
	private static final long serialVersionUID = 1L;
	
//	private int id;

	private String codigo;
	private String tipo;
	private float medida;
	private String caracteristicas;
	private BeanMarca beanMarca;
	
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public float getMedida() {
		return medida;
	}
	public void setMedida(String medida) {
		try {
			Float f = Float.parseFloat(medida);
			this.medida = f;
		} catch (Exception e) {
			this.medida=0;
		}
	}
	public String getCaracteristicas() {
		return caracteristicas;
	}
	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	public BeanMarca getBeanMarca() {
		return beanMarca;
	}
	public void setBeanMarca(BeanMarca beanMarca) {
		this.beanMarca = beanMarca;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}