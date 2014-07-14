package model;

import java.io.Serializable;

import javax.persistence.*;

@Embeddable
public class Marca implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	@Column(columnDefinition="char(30)",nullable=false)
	private String descripcion;
	@Column(columnDefinition="char(30)",nullable=false)
	private String pais;
		
	public Marca (){}
	
			
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
	
}
