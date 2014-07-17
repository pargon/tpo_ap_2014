package hbt.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import model.SolicitudCotizacion;

import org.hibernate.*;

public class HibernateDAO {

	protected static HibernateDAO hdao;
	protected static Session sesion;
	protected static SessionFactory sf;
	
	public HibernateDAO() {
		sf = HibernateUtil.getSessionFactory(); 
	}

	private Session getSession(){
		if (sesion == null)
			sesion = sf.openSession();
		return sesion;
	}
	
	public static HibernateDAO getInstancia(){
		if(hdao == null)
			hdao = new HibernateDAO();
		return hdao;
	}
	
	public void persistir(Object o){
		Session ses = getSession();
		ses.beginTransaction();
		ses.persist(o);
		ses.flush();
		ses.getTransaction().commit();
	}

	public List<?> getlista(String sql) {
		Session ses = getSession();
		return ses.createQuery(sql).list();
	}

	public List<?> getProp(String sql, String sx, String prm, String[] funcs, String prm2) {
		Session ses = getSession();
		return ses.createQuery(sql).setParameterList(prm2, funcs).setParameter(prm, sx).list();
		
	}
	public List<?> parametros(String query, String prmName, String prm) {
		Session session = getSession();
		List<?> lista = session.createQuery(query).setParameter(prmName, prm).list();
		return lista;
	}
	public List<?> parametros2(String query, String prmName1, String prm1, String prmName2, String prm2) {
		Session session = getSession();
		List<?> lista = session.createQuery(query).setParameter(prmName1, prm1).setParameter(prmName2, prm2).list();
		return lista;
	}
	public List<?> parametros3(String query, String prmName1, Date prm1, String prmName2, String prm2) {
		Session session = getSession();
		List<?> lista = session.createQuery(query).setDate(prmName1, prm1).setParameter(prmName2, prm2).list();
		return lista;
	}
	
	@SuppressWarnings("rawtypes")
	public Object get (Class c, Object o) {
		Session session = getSession();
		return session.get(c, (Serializable) o);
	}

	public void guardar(Object o){
		Session ses = getSession();
		ses.beginTransaction();
		ses.saveOrUpdate(o);
		ses.flush();
		ses.getTransaction().commit();
	}
	
	public void update(Object o){
		Session ses = getSession();
		ses.beginTransaction();
		ses.update(o);
		ses.flush();
		ses.getTransaction().commit();
	}
}
