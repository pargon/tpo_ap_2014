package hbt.dao;

import model.SolicitudCotizacion;



public class HibernateSolicitudCotizacionDAO {
	
private HibernateDAO hdao;
	
	public HibernateSolicitudCotizacionDAO(){
		
	}
	
	public void guardarSolicitudCotizacion(SolicitudCotizacion solicitudCotizacion){
		 hdao.getInstancia().guardar(solicitudCotizacion);
	}

}
