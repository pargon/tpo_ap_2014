package model;

import java.util.Date;
import java.util.List;

import hbt.dao.HibernateFacturaDAO;
import beans.BeansFactura;

public class FacturaSRV {
	
	private static FacturaSRV instancia;

	public static FacturaSRV getinstancia(){
		if (instancia == null){
			instancia = new FacturaSRV();
		}
		return instancia;
	}

	public Factura fromBean(BeansFactura beanFactura) {
		Factura factura = new Factura();
		factura.setFecha(beanFactura.getFecha());
		factura.setNroFactura(beanFactura.getNroFactura());
		factura.setTotal(beanFactura.getTotal());
		List<Remito> remitos = RemitosSRV.getinstancia().fromBean(beanFactura.getFecha());		
		factura.setRemitos(remitos);
		return factura;
	}

	public boolean guardar(Factura factura) {
		return HibernateFacturaDAO.getInstancia().guardarFactura(factura);
	}
	
	public List<BeansFactura> Facturar(Date fecha, String fhventa) {
		return HibernateFacturaDAO.getInstancia().Facturar(fecha, fhventa);
	}
	
	
}