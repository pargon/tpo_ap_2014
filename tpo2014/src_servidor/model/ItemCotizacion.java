package model;

import java.io.Serializable;

import javax.persistence.*;
@Entity
@Table(name="ItemsCotizacion")
public class ItemCotizacion implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(referencedColumnName="id")
	private ItemRodamiento itemRodamiento;
	@OneToOne
	private ListaPrecios listaPrecios;

	public ItemRodamiento getItemRodamiento() {
		return itemRodamiento;
	}
	public void setItemRodamiento(ItemRodamiento itemRodamiento) {
		this.itemRodamiento = itemRodamiento;
	}
	public Integer getCantidad() {
		return itemRodamiento.getCantidad();
	}
	public void setCantidad(Integer cantidad) {
		itemRodamiento.setCantidad(cantidad);
	}
	public ListaPrecios getListaPrecios() {
		return listaPrecios;
	}
	public void setListaPrecios(ListaPrecios listaPrecios) {
		this.listaPrecios = listaPrecios;
	}
	
	
}
