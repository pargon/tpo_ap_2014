package hbt.dao;

import java.util.List;

import model.Cliente;
import model.CotizacionRodamiento;
import model.SolicitudCotizacion;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateCotizacionRodamientoDAO {
	
private HibernateDAO hdao;
	
	public HibernateCotizacionRodamientoDAO(){
		
	}
	
	public void guardarCotizacionRodamiento(CotizacionRodamiento cotizacion){
		 hdao.getInstancia().persistir(cotizacion);
	}

}
/*
public class HibernateCotizacionRodamientoDAO {
	private static HibernateCotizacionRodamientoDAO instancia = null;
	private static SessionFactory sf = null;
	
	public static HibernateCotizacionRodamientoDAO getInstancia(){
		if(instancia == null){
			sf = HibernateUtil.getSessionFactory();
			instancia = new HibernateCotizacionRodamientoDAO();
		} 
		return instancia;
	}

	public boolean guardarCotizacion(CotizacionRodamiento cotizacionRodamiento) {
		try {
			Session session = sf.openSession();
			session.beginTransaction();
			session.persist(cotizacionRodamiento);
			session.flush();
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}		
	}

	public List<CotizacionRodamiento> levantarCotizaciones(String numeroCuit) {
		Session session = sf.openSession();
		Query query = session.createQuery("from CotizacionRodamiento cr where cr.activa = 1 AND cr.cliente.cuit = :cuit");
		query.setParameter("cuit", numeroCuit);
		List<CotizacionRodamiento> cotizaciones = query.list();
		return cotizaciones;
	}

	public boolean actualizarEstadoCotizacion(CotizacionRodamiento cotizacionRodamiento) {
		try {
			Session session = sf.openSession();
			session.beginTransaction();
			session.saveOrUpdate(cotizacionRodamiento);
			session.flush();
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		
		
	}
	
}
*/