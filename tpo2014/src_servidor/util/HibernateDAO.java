package util;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateDAO {
	protected static HibernateDAO instancia = null;
	protected static SessionFactory sf = null;
	protected static Session session = null;

	
	public static HibernateDAO getInstancia(){
		if(instancia == null){
			instancia = new HibernateDAO();
		} 
		return instancia;
	}
	
	protected HibernateDAO()  {
		sf = HibernateUtil.getSessionFactory();
	}

	protected Session getSession(){
		if(session == null || !session.isOpen()){
			session = sf.openSession();
		}
		return session;
	}
	
	public void closeSession(){
		if (session.isOpen()) {
			session.close();
		}
	}
	
	@SuppressWarnings("rawtypes")
	public Object get (Class c, Object o) {
		Session session = getSession();
		return session.get(c, (Serializable) o);
	}
	
	public void persistir (Object o) {
		Session session = getSession();
		try{	
		session.persist(o);
		session.flush();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}

	public List<?> getLista(String query) {
		Session session = getSession();
		List<?> lista = session.createQuery(query).list();
		return lista;
	}
	
	public List<?> parametros(String query, String prmName, String prm) {
		Session session = getSession();
		List<?> lista = session.createQuery(query).setParameter(prmName, prm).list();
		return lista;
	}
	
	public List<?> propiedades(String query, Object obj) {
		Session session = getSession();
		List<?> lista = session.createQuery(query).setProperties(obj).list();
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> tuplas(String query) {
		Session session = getSession();
		List<Object[]> lista = session.createQuery(query).list();
		return lista;
	}
	
}
