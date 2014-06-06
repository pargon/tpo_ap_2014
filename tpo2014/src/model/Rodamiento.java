package model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="rodamientos")
public class Rodamiento implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private RodamientoId rodamientoId; 
	private String tipo;
	private float medida;
	private String caracteristicas;

	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public float getMedida() {
		return medida;
	}
	public void setMedida(float medida) {
		this.medida = medida;
	}
	public String getCaracteristicas() {
		return caracteristicas;
	}
	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	public RodamientoId getRodamientoId() {
		return rodamientoId;
	}
	public void setRodamientoId(RodamientoId rodamientoId) {
		this.rodamientoId = rodamientoId;
	}

	@Embeddable
	public static class RodamientoId implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 2939343529393414696L;
		@Column(name="codigo", nullable=false, updatable=false)
		private String codigo;
		@ManyToOne
		@JoinColumns({
			@JoinColumn(name="descripcion", referencedColumnName="descripcion", nullable=false, updatable=false),
			@JoinColumn(name="pais", referencedColumnName="pais", nullable=false, updatable=false)
			})
		private Marca marca;
		
		public String getCodigo() {
			return codigo;
		}
		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}
		public Marca getMarca() {
			return marca;
		}
		public void setMarca(Marca marca) {
			this.marca = marca;
		}
		
		
	}

}





