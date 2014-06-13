package hbt.dao;

import util.HibernateUtil;

import java.util.List;

import model.ItemRodamiento;
import model.Rodamiento;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateItemRodamientoDAO {

	private static HibernateItemRodamientoDAO instancia = null;
	private static SessionFactory sf = null;
	
	public static HibernateItemRodamientoDAO getInstancia(){
		if(instancia == null){
			sf = HibernateUtil.getSessionFactory();
			instancia = new HibernateItemRodamientoDAO();
		} 
		return instancia;
	}
	
	public List<ItemRodamiento> levantarItemRodamientos() {
		Session session = sf.openSession();
		Query query = session.createQuery("from ItemRodamiento");
		List<ItemRodamiento> itemRodamiento= query.list();
		return itemRodamiento;
	}
	
	public void guardar(ItemRodamiento itr) {
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(itr);
		session.flush();
		session.getTransaction().commit();
		session.close();		
	}

}