package hbt.dao;

import java.util.List;

import model.ListaPrecios;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import beans.BeansListaPrecios;

public class HibernateListaPreciosDAO {

	private static HibernateListaPreciosDAO instancia = null;
	private static SessionFactory sf = null;
	
	public static HibernateListaPreciosDAO getInstancia(){
		if(instancia == null){
			sf = HibernateUtil.getSessionFactory();
			instancia = new HibernateListaPreciosDAO();
		} 
		return instancia;
	}
	
	public List<ListaPrecios> levantarListasPrecios() {
		Session session = sf.openSession();
		Query query = session.createQuery("from ListaPrecios");
		List<ListaPrecios> listasPrecios = query.list();
		return listasPrecios;
	}

	public void guardarListaPrecios(ListaPrecios listaPrecios) {
		Session session = sf.openSession();
		session.beginTransaction();
		session.persist(listaPrecios);
		session.flush();
		session.getTransaction().commit();
		session.close();		
	}
	
	public ListaPrecios fromBeanListasPrecios(BeansListaPrecios beansListaPrecios) {
		Session session = sf.openSession();
		Query query = session.createQuery("from ListaPrecios lr where id = :id");
		query.setParameter("id", beansListaPrecios.getId());
		List<ListaPrecios> listasPrecios = query.list();
		return listasPrecios.get(0);
	}
	
	public List<?> getLista(String sql){
		return HibernateDAO.getInstancia().getlista(sql);
	}
}
