package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.*;


@Entity
@Table(name="listaPrecios")
public class ListaPrecios implements Serializable {
	private static final long serialVersionUID = 1581747666183346205L;

	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<ItemRodamiento> itemsRodamiento = new ArrayList<ItemRodamiento>();
	
	@OneToOne(cascade=CascadeType.ALL)
	private Proveedor proveedor;
	
	@Column
	private int descuento;
	@Column
	private int financiacion_dias;
	@Column
	private int financiacion_dto;
	

	
	public int getDescuento() {
		return descuento;
	}
	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}
	public ListaPrecios() {
		this.itemsRodamiento = new ArrayList<ItemRodamiento>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	
	public void agregarItemRodamiento(ItemRodamiento itr){
		this.itemsRodamiento.add(itr);
	}
	
	public void setFinanciacion(List<Map<Integer,Float>> financiacion) {
	}
	
	
}
