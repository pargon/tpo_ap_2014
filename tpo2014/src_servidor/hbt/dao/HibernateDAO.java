package hbt.dao;

import java.util.List;

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
	
}
