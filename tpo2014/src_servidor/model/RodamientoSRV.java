package model;

import hbt.dao.HibernateRodamientoDAO;

import java.util.List;

import model.Rodamiento.RodamientoId;
import beans.BeanMarca;
import beans.BeanRodamiento;

public class RodamientoSRV {

	private static RodamientoSRV instancia;

	public static RodamientoSRV getinstancia(){
		if (instancia == null){
			instancia = new RodamientoSRV();
		}
		return instancia;
	}

	public void guardar(Rodamiento rodamiento) {
		HibernateRodamientoDAO.getInstancia().guardar(rodamiento);
	}

	public Rodamiento fromBean(BeanRodamiento beanRodamiento) {
		Rodamiento rodamiento = new Rodamiento();
		RodamientoId rodamientoId = new RodamientoId();
		rodamientoId.setCodigo(beanRodamiento.getCodigo());
		Marca marca = new Marca();
		marca.setDescripcion(beanRodamiento.getBeanMarca().getDescripcion());
		marca.setPais(beanRodamiento.getBeanMarca().getPais());
		
		rodamientoId.setMarca(marca);
		rodamiento.setTipo(beanRodamiento.getTipo());
		rodamiento.setCaracteristicas(beanRodamiento.getCaracteristicas());
		rodamiento.setMedida(beanRodamiento.getMedida());
		rodamiento.setRodamientoId(rodamientoId);
		return rodamiento;
	}
	
	public BeanRodamiento toBean(Rodamiento rodamiento) {
		BeanRodamiento brodamiento = new BeanRodamiento();
		brodamiento.setCodigo(rodamiento.getRodamientoId().getCodigo());
		BeanMarca bmarca = new BeanMarca();
		bmarca.setDescripcion(rodamiento.getRodamientoId().getMarca().getDescripcion());
		bmarca.setPais(rodamiento.getRodamientoId().getMarca().getPais());
		brodamiento.setBeanMarca(bmarca);
		brodamiento.setTipo(rodamiento.getTipo());
		brodamiento.setCaracteristicas(rodamiento.getCaracteristicas());
		brodamiento.setMedida(String.valueOf(rodamiento.getMedida()));
		return brodamiento;
	}
	
	public List<Rodamiento> levantarRodamientos() {
		return HibernateRodamientoDAO.getInstancia().levantarRodamientos();
	}
	
	public List<Rodamiento> getRodamientosByCriterio(Rodamiento rodamiento) {
		return HibernateRodamientoDAO.getInstancia().getRodamientosByCriterio(rodamiento);		
	}
	
	public void borrarRodamiento (Rodamiento rodamiento){
		HibernateRodamientoDAO.getInstancia().borrarRodamiento(rodamiento);
	}
	
	public void actualizarRodamiento (Rodamiento rNuevo){
		HibernateRodamientoDAO.getInstancia().actualizarRodamiento(rNuevo);
	}
	

	

}
