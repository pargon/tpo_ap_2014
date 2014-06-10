package model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="oficina_ventas")
public class OficinaVenta implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	private Integer id;
	private String nombre;
	private String ciudad;
	private String telefono;
	
	
	public OficinaVenta() {
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
	

}
