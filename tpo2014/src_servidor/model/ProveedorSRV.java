package model;

import hbt.dao.HibernateProveedorDAO;

import java.util.List;


public class ProveedorSRV {
	private static ProveedorSRV instancia;

	public static ProveedorSRV getinstancia(){
		if (instancia == null){
			instancia = new ProveedorSRV();
		}
		return instancia;
	}
	
	public List<Proveedor> levantarProveedores() {
		return HibernateProveedorDAO.getInstancia().levantarProveedores();
	}

	public void guardarProveedor(Proveedor prov) {
		HibernateProveedorDAO.getInstancia().guardarProveedor(prov);		
	}
}
