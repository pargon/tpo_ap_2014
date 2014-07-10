package model;

import java.util.List;

import hbt.dao.HibernateDAO;

public class RemitoSRV {
	
	private static RemitoSRV instancia;
	
	public static RemitoSRV getinstancia(){
		if (instancia == null){
			instancia = new RemitoSRV();
		}
		return instancia;
	}
	
	public RemitoSRV() {
		
	}

	public boolean cumplenOPedido(Remito r){
		
		String sql = "select p from OrdenPedido p "
				+ " join p.cot c"
				+ " join c.itemsRodamiento it"
				+ " where CAST(p.cliente.id as string) =:cli"
				+ " and it.cantidad < ( select sum(itr.cantidad) from Remito r"
				+ "					join r.items itr"
				+ "					where itr.rodamiento.rodamientoId.codigo = it.rodamiento.rodamientoId.codigo)";
		
		List<ItemRodamiento> lrod = (List<ItemRodamiento>) HibernateDAO.getInstancia().parametros(sql, "cli", String.valueOf( r.getCliente().getId() ));
		
		return (lrod.size() == 0);
	}
}

