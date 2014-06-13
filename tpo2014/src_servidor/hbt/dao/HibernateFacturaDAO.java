package hbt.dao;

import java.util.Date;
import java.util.List;

import util.HibernateUtil;
import model.Factura;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import beans.BeansFactura;

public class HibernateFacturaDAO {
	private static HibernateFacturaDAO instancia = null;
	private static SessionFactory sf = null;
	
	public static HibernateFacturaDAO getInstancia(){
		if(instancia == null){
			sf = HibernateUtil.getSessionFactory();
			instancia = new HibernateFacturaDAO();
		} 
		return instancia;
	}

	public boolean guardarFactura(Factura factura) {
		try {
			Session session = sf.openSession();
			session.beginTransaction();
			session.persist(factura);
			session.flush();
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<BeansFactura> Facturar(Date fecha, String fhventa)
	{
		try{
			List<BeansFactura> facturas = null;
			return facturas;
		}
		catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

}
