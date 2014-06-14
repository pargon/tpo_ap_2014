
package model;

import java.util.Date;

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

	// precondicion: el archivo xml debe tener los cambios, así como los valores en negativo para descontar o dar de baja
	public void AdministraRodamiento(){
		
		// obtiene xml y llena objeto para alta
		MovimientoStock mov = LlenarObjetoDesdeXML();
		// obtiene el rod. del mov.
		Rodamiento rod = mov.getRodamiento();
		
		// persiste en DB
		RodamientoSRV.getinstancia().guardar(rod);
		MovimientoStockSRV.getInstancia().persistir(mov);
	}

	private MovimientoStock LlenarObjetoDesdeXML() {
		// TODO Auto-generated method stub
		System.out.println("Hacer lectura xml LlenarObjetoDesdeXML");
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
		// lee archivo xml
				
		// por cada item del arhivo, es un rodamiento con cantidad
		// cantidad a sumar o descontar, si 0 se elimina
		int cant = 0;
		// fecha de hoy
		java.util.Date fecha = new Date();
		Rodamiento rod = new Rodamiento();
		String ori = "Proveedor";
		String des = "CPR";
		
		//
		//	
		MovimientoStock mov = new MovimientoStock();
		mov.setCantidad(cant);
		mov.setFecha(fecha);
		mov.setRodamiento(rod);
//		mov.setOrigen(ori);
//		mov.setDestino(des);
		
		return mov;
	}
}
