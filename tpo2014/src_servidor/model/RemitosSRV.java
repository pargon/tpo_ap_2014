package model;

import hbt.dao.HibernateClientesDAO;
import hbt.dao.HibernateFacturaDAO;

import java.util.List;

import beans.BeansFactura;
import beans.BeansRemito;

public class RemitosSRV {

	private static RemitosSRV instancia;

	public static RemitosSRV getinstancia(){
		if (instancia == null){
			instancia = new RemitosSRV();
		}
		return instancia;
	}
	
	public Remito fromBean(BeansRemito beanRemito) {
		Remito remito = new Remito();
		remito.setCliente(ClienteSRV.getinstancia().fromBean(beanRemito.getCliente()));		
		remito.setCotizacion(CotizacionRodamientoSRV.getinstancia().fromBean(beanRemito.getCotizacion()));
		remito.setFecha(beanRemito.getFecha());			
		return remito;
	}

	public void guardar(Remito r) {
		HibernateFacturaDAO.getInstancia().guardarRemito(r);
	}

	
	public List<BeansRemito> BuscarRemitosPendientesFacturacion(int idcliente) {		
		return HibernateFacturaDAO.getInstancia().buscarRemitoPendientesFacturacion(idcliente);
	}

}
