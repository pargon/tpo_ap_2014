package model;

import hbt.dao.PersistentObject;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="remitos")
public class Remito extends PersistentObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Date fecha;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Cliente cliente;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<ItemRodamiento> items;
	
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<ItemRodamiento> getItems() {
		return items;
	}
	public void setItems(List<ItemRodamiento> items) {
		this.items = items;
	}
	
	
}
