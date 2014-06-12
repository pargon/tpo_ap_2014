package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import util.PersistentObject;

@Entity
@Table(name="casa_central")
public class CasaCentral extends PersistentObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String direccion;
	
	private String telefono;
	private float ganancia;
	private float cond_venta;

	public void AltaRodamiento(){
		
		// obtiene xml y llena objeto para alta
		MovimientoStock mov = LlenarObjetoDesdeXML();
		// obtiene el rod. del mov.
		Rodamiento rod = mov.getRodamiento();
		
		// persiste en DB
		RodamientoSRV.getInstancia().persistir(rod);
		MovimientoStockSRV.getInstancia().persistir(mov);
	}

	private MovimientoStock LlenarObjetoDesdeXML() {
		// TODO Auto-generated method stub
		
		/*
		 * 
		 * 
		 * 
		 * 
		 * HACERRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
		return null;
	}
}
