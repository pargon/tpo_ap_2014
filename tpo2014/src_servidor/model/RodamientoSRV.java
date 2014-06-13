package model;

import hbt.dao.HibernateRodamientoDAO;

import java.util.List;

import model.Marca.MarcaId;
import model.Rodamiento.RodamientoId;
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
//		Rodamiento.RodamientoId rodamientoId = new Rodamiento.RodamientoId();
		rodamientoId.setCodigo(beanRodamiento.getCodigo());
		Marca marca = new Marca();
//		marca.setDescripcion(beanRodamiento.getBeanMarca().getDescripcion());
//		marca.setPais(beanRodamiento.getBeanMarca().getPais());
//		marca.setId(beanRodamiento.getBeanMarca().getId());
//		rodamiento.setMarca(marca);
		MarcaId mId = new MarcaId();
		mId.setDescripcion(beanRodamiento.getBeanMarca().getDescripcion());
		mId.setPais(beanRodamiento.getBeanMarca().getPais());
		marca.setMarcaId(mId);
		rodamientoId.setMarca(marca);
		rodamiento.setTipo(beanRodamiento.getTipo());
		rodamiento.setCaracteristicas(beanRodamiento.getCaracteristicas());
		rodamiento.setMedida(beanRodamiento.getMedida());
		rodamiento.setRodamientoId(rodamientoId);
//		if (0 != beanRodamiento.getId())
//			rodamiento.setId(beanRodamiento.getId());
		return rodamiento;
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
