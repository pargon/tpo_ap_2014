package model;

import hbt.dao.HibernateFacturaDAO;
import hbt.dao.HibernateOPedidoDAO;

import java.util.List;

public class OrdenPedidoSRV {


	private static OrdenPedidoSRV instancia;

	public static OrdenPedidoSRV getinstancia(){
		if (instancia == null){
			instancia = new OrdenPedidoSRV();
		}
		return instancia;
	}
	public OrdenPedidoSRV() {
		// TODO Auto-generated constructor stub
	}
	
	public List<OrdenPedido> getListaPendientes() {

		String sql = "from OrdenPedido where estado=:est ";
		return (List<OrdenPedido>) HibernateOPedidoDAO.getInstancia().parametros(sql, "est", "PEN");
	}

}
