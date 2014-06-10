package model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="perfiles")
public class Perfil implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String nombre;
	private Integer cliente;
	private Integer rodamiento;
	private Integer proveedor;
	private Integer oficinaVenta;
	
	public Perfil() {
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCliente() {
		return cliente;
	}
	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}
	public Integer getRodamiento() {
		return rodamiento;
	}
	public void setRodamiento(Integer rodamiento) {
		this.rodamiento = rodamiento;
	}
	public Integer getProveedor() {
		return proveedor;
	}
	public void setProveedor(Integer proveedor) {
		this.proveedor = proveedor;
	}
	public boolean canModifyProveedor(){
		return this.proveedor==1 ? true : false;
	}
	
	public boolean canModifyRodamientos(){
		return this.rodamiento==1 ? true : false;
	}
	
	public boolean canModifyClientes(){
		return this.cliente==1 ? true : false;
	}
	
	public boolean isAdministrator(){
		return (this.cliente==1 && this.proveedor==1 && this.rodamiento == 1) ? true : false;
	}
	public boolean isOficinaVenta(){
		return (this.oficinaVenta==1) ? true : false;
	}
	public Integer getOficinaVenta() {
		return oficinaVenta;
	}
	public void setOficinaVenta(Integer oficinaVenta) {
		this.oficinaVenta = oficinaVenta;
	}
	

}
