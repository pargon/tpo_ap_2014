package beans;

import java.io.Serializable;


public class BeansProveedor implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String razonSocial;
	private String cuit;
	private String direccion;
	private String telefono;
	
	public BeansProveedor() {
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
		
}
