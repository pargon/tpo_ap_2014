package hbt.dao;

import util.HibernateUtil;

import java.util.List;

import model.Cliente;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateClientesDAO {

	private static HibernateClientesDAO instancia = null;
		private static SessionFactory sf = null;
		
		public static HibernateClientesDAO getInstancia(){
			if(instancia == null){
				sf = HibernateUtil.getSessionFactory();
				instancia = new HibernateClientesDAO();
			} 
			return instancia;
		}

		public void guardarCliente(Cliente cliente) {
			Session session = sf.openSession();
			session.beginTransaction();
			session.persist(cliente);
			session.flush();
			session.getTransaction().commit();
			session.close();		
		}
		
		public List<Cliente> levantarClientes() {
			Session session = sf.openSession();
			Query query = session.createQuery("from Cliente");
			List<Cliente> clientes = query.list();
			return clientes;
		}

		public List<Cliente> buscarClientes(String nombreCliente, String cuitCliente) {
			Session session = sf.openSession();
			String queryString = "from Cliente c";
			if((nombreCliente != null && !"".equalsIgnoreCase(nombreCliente)) || (cuitCliente != null && !"".equalsIgnoreCase(cuitCliente)) )
				queryString += " where 1=1 ";
			if(nombreCliente != null && !"".equalsIgnoreCase(nombreCliente))
				queryString += " AND c.razonSocial LIKE :nombreCliente ";
			if(cuitCliente != null && !"".equalsIgnoreCase(cuitCliente))
				queryString += " AND c.cuit = :cuitCliente ";
			
			
			Query query = session.createQuery(queryString);
			
			if(nombreCliente != null && !"".equalsIgnoreCase(nombreCliente))
				query.setParameter("nombreCliente", "%"+nombreCliente+"%");
			if(cuitCliente != null && !"".equalsIgnoreCase(cuitCliente))
				query.setParameter("cuitCliente", cuitCliente);
			
			List<Cliente> clientes = query.list();
			return clientes;
		}

		public boolean actualizarCliente(Cliente cliente) {
				try {
					Session session = sf.openSession();
					session.beginTransaction();
					session.saveOrUpdate(cliente);
					session.flush();
					session.getTransaction().commit();
					session.close();
					return true;
				} catch (HibernateException e) {
					e.printStackTrace();return false;
				}
		}

		public boolean borrarCliente(Cliente cliente) {
			try {
				Session session = sf.openSession();
				String borrar = "DELETE FROM Cliente c WHERE c.cuit = :cuit";
				Query query = session.createQuery(borrar);
				query.setParameter("cuit", cliente.getCuit());
				query.executeUpdate();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
	}
