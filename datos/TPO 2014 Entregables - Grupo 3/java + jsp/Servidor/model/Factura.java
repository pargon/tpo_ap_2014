package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
@Entity
@Table(name="facturas")
public class Factura {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer nroFactura;
	private Date fecha;
	private Float total;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Cliente cliente;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="fac_rem",joinColumns=@JoinColumn(name="idFactura"),inverseJoinColumns=@JoinColumn(name="idRemito") )
	//@JoinColumn(name="idRemi")
	private List<Remito> remitos = new ArrayList<Remito>();
	private String estado;
	
	public Factura() {
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Integer getNroFactura() {
		return nroFactura;
	}
	public void setNroFactura(Integer nroFactura) {
		this.nroFactura = nroFactura;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Float getTotal() {
		return total;
	}
	public void setTotal(Float total) {
		this.total = total;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<Remito> getRemitos() {
		return remitos;
	}

	public void setRemitos(List<Remito> remitos) {
		this.remitos = remitos;
	}
	
	public void setRemitoSolo(Remito remito){
		this.remitos.add(remito);
	}
	
		
}
