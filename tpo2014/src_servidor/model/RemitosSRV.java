package model;

import java.util.List;

import beans.BeansFactura;

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
		remito.setCliente(beanRemito.cliente);		
		remito.setCotizacion(beanRemito.cotizacion);
		remito.setFecha(beanRemito.fecha);			
		return remito;
	}

}
