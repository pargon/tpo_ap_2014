package hbt.dao;

import java.util.List;

import model.Proveedor;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import beans.BeansListaPrecios;

public class HibernateProveedorDAO {

		private static HibernateProveedorDAO instancia = null;
		private static SessionFactory sf = null;
		
		public static HibernateProveedorDAO getInstancia(){
			if(instancia == null){
				sf = HibernateUtil.getSessionFactory();
				instancia = new HibernateProveedorDAO();
			} 
			return instancia;
		}
		
		public List<Proveedor> levantarProveedores() {
			Session session = sf.openSession();
			Query query = session.createQuery("from proveedores");
			List<Proveedor> proveedores = query.list();
			return proveedores;
		}

		public void guardarProveedor(Proveedor proveedores) {
			Session session = sf.openSession();
			session.beginTransaction();
			session.saveOrUpdate(proveedores);
			session.flush();
			session.getTransaction().commit();
			session.close();		
		}
		
		public Proveedor fromBeanListasPrecios(BeansListaPrecios beansListaPrecios) {
			Session session = sf.openSession();
			Query query = session.createQuery("from proveedores p where cuit = :cuit");
			query.setParameter("id", beansListaPrecios.getId());
			List<Proveedor> proveedores = query.list();
			return proveedores.get(0);
		}
	}

