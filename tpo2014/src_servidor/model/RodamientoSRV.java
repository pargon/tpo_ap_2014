package model;

import hbt.dao.HibernateRodamientoDAO;

public class RodamientoSRV {

	private static RodamientoSRV instancia;
	
	public static RodamientoSRV getInstancia(){
		if (instancia==null)
			instancia = new RodamientoSRV();
		
		return instancia;
	}
	
	public void persistir(Rodamiento r){
		HibernateRodamientoDAO.getInstancia().persistir(r);
	}
}
