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

	
}
