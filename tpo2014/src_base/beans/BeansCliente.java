package beans;

import java.io.Serializable;

public class BeansCliente implements Serializable {

	private static final long serialVersionUID = 1L;
	private String cuit;
	private String contacto;
	private String razonSocial;
	private Float porcentajeDesc;
	private String telefono;
	
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	public String getContacto() {
		return contacto;
	}
	public void setContacto(String contacto) {
		this.contacto = contacto;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public Float getPorcentajeDesc() {
		return porcentajeDesc;
	}
	public void setPorcentajeDesc(Float porcentajeDesc) {
		this.porcentajeDesc = porcentajeDesc;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
}
