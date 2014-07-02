package model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name="ordenCompra")
public class OrdenCompra {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<ItemRodamiento> itemsOC;
	
	private Date fecha;
	
	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn 
	private Proveedor proveedor;
	
	private float total;
	
	private String estado;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<OrdenPedido> pedidos;
	
	
	public OrdenCompra(){
		itemsOC = new ArrayList<ItemRodamiento>();
		pedidos = new ArrayList<OrdenPedido>();
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
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
	public void agregaItems(ItemRodamiento irod) {

		boolean entro = false;
		
		// busca en los items el rodamiento
		for(ItemRodamiento ioc: itemsOC){
			// si encuentra
			if (ioc.getRodamiento() == irod.getRodamiento()){					
				// suma cantidad del item del parametro
				ioc.setCantidad(ioc.getCantidad() + irod.getCantidad());
				entro = true;
			}			
		}
		
		// si no entro, agrega el item
		if(!entro){
			itemsOC.add(irod);
		}
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public void agregarOPedido(OrdenPedido op) {
		pedidos.add(op);
	}
	public List<OrdenPedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<OrdenPedido> pedidos) {
		this.pedidos = pedidos;
	}
	public List<ItemRodamiento> getItemsOC() {
		return itemsOC;
	}
	public void setItemsOC(List<ItemRodamiento> itemsOC) {
		this.itemsOC = itemsOC;
	}
	
	
}
