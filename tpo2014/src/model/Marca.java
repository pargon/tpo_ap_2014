package model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "marcas")
public class Marca implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 716011125681681386L;

	@EmbeddedId
	private MarcaId marcaId;
	
	public Marca (){}
	
	@Override
	public String toString() {
		return ""+this.marcaId.getDescripcion()+", "+ this.marcaId.getPais();
	}
	
	@Embeddable
	public static class MarcaId implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = -7438282727038230331L;
		@Column(name="descripcion")
		private String descripcion;
		@Column(name="pais")
		private String pais;
		
		public MarcaId (){}
		
		public String getDescripcion() {
			return descripcion;
		}
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}
		public String getPais() {
			return pais;
		}
		public void setPais(String pais) {
			this.pais = pais;
		}
	}

	public MarcaId getMarcaId() {
		return marcaId;
	}

	public void setMarcaId(MarcaId marcaId) {
		this.marcaId = marcaId;
	}
	
	
	
}
