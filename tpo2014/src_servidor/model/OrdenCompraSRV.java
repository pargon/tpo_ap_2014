package model;

import hbt.dao.HibernateDAO;
import hbt.dao.HibernateOPedidoDAO;

import java.util.List;

public class OrdenCompraSRV {

	private static OrdenCompraSRV instancia;
	
	public static OrdenCompraSRV getinstancia(){
		if (instancia == null){
			instancia = new OrdenCompraSRV();
		}
		return instancia;
	}
	
	public OrdenCompraSRV() {
		// TODO Auto-generated constructor stub
	}

	public OrdenCompra confimarRec(Integer idOC) {
		OrdenCompra oc1 = new OrdenCompra();
		oc1.setId(idOC);
		
		OrdenCompra oc = (OrdenCompra) HibernateDAO.getInstancia().get(OrdenCompra.class, oc1.getId());
		
		oc.setEstado("REC");
		HibernateDAO.getInstancia().persistir(oc);
		
		return oc;
	}

}
