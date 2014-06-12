package model;

import hbt.dao.HibernateMovStockDAO;


public class MovimientoStockSRV {

	private static MovimientoStockSRV instancia;
	
	public static MovimientoStockSRV getInstancia(){
		if (instancia==null)
			instancia = new MovimientoStockSRV();
		
		return instancia;
	}
	
	public void persistir(MovimientoStock mov){
		HibernateMovStockDAO.getInstancia().persistir(mov);
	}
}
