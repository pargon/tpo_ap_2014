package model;

import java.sql.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="ordenCompra")
public class OrdenCompra {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@OneToMany
	@PrimaryKeyJoinColumn
	private List<ItemOC> itemsOC;
	private Date fecha;
	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn 
	private Proveedor proveedor;
	private float total;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public List<ItemOC> getItemsOC() {
		return itemsOC;
	}
	public void setItemsOC(List<ItemOC> itemsOC) {
		this.itemsOC = itemsOC;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
}
