package model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="usuarios")
public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private Integer id;
	private String nombre;
	private String password;
	private String username;
	@OneToOne(cascade=CascadeType.ALL)
	private Perfil perfil;
	@OneToOne(cascade=CascadeType.ALL)
	private OficinaVenta oficinaVenta;
	
	public Usuario() {
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
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public OficinaVenta getOficinaVenta() {
		return oficinaVenta;
	}

	public void setOficinaVenta(OficinaVenta oficinaVenta) {
		this.oficinaVenta = oficinaVenta;
	}
	
	
}
