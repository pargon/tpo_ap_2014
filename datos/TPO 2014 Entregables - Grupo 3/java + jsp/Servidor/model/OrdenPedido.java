package model;

import hbt.dao.PersistentObject;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="OrdenPedido")
public class OrdenPedido extends PersistentObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date fecha;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Cliente cliente;
	
	private String estado;
	
	@OneToOne(cascade=CascadeType.ALL)
	private CotizacionRodamiento cot;
	
	public OrdenPedido() {
		// TODO Auto-generated constructor stub
	}

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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public CotizacionRodamiento getCot() {
		return cot;
	}

	public void setCot(CotizacionRodamiento cot) {
		this.cot = cot;
	}

	public List<ItemRodamiento> getListaRod() {

		return cot.getItemsRodamiento();
	}

	
}
