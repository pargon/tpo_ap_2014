package model;

import javax.persistence.*;

@Entity
@Table(name="itemOC")
public class ItemOC {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private int cantidad;
	@OneToOne
	private ItemRodamiento itemRodamiento;
	
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public ItemRodamiento getItemRodamiento() {
		return itemRodamiento;
	}
	public void setItemRodamiento(ItemRodamiento itemRodamiento) {
		this.itemRodamiento = itemRodamiento;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
}
