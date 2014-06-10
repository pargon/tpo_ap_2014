package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name="listaOfertas")
public class ListaOfertas implements Serializable {
	private static final long serialVersionUID = -2669455326865896092L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@OneToMany
	@PrimaryKeyJoinColumn
	private List<ItemRodamiento> itemsRodamiento = new ArrayList<ItemRodamiento>();
	private String descripcion;
	@ManyToOne
	private Proveedor proveedor;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<ItemRodamiento> getItemsRodamiento() {
		return itemsRodamiento;
	}
	public void setItemsRodamiento(List<ItemRodamiento> itemsRodamiento) {
		this.itemsRodamiento = itemsRodamiento;
	}
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
}
