package hbt.dao;

import model.SolicitudCotizacion;
import hbt.HibernateDAO;


public class HibernateSolicitudCotizacionDAO {
	
private HibernateDAO hdao;
	
	public HibernateSolicitudCotizacionDAO(){
		
	}
	
	public void guardarSolicitudCotizacion(SolicitudCotizacion solicitudCotizacion){
		 hdao.getInstancia().persistirGenerico(solicitudCotizacion);
	}

}
