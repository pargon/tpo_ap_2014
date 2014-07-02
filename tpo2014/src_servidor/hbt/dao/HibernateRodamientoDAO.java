package hbt.dao;

import java.util.List;

import model.Cliente;
import model.Marca;
import model.Rodamiento;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import beans.BeanRodamiento;

public class HibernateRodamientoDAO {
	
	private static HibernateRodamientoDAO instancia = null;
	private static SessionFactory sf = null;
	
	public static HibernateRodamientoDAO getInstancia(){
		if(instancia == null){
			sf = HibernateUtil.getSessionFactory();
			instancia = new HibernateRodamientoDAO();
		} 
		return instancia;
	}
	
	public void guardar(Rodamiento rodamiento) {
		Session session = sf.openSession();
		session.beginTransaction();
		session.persist(rodamiento);
		session.flush();
		session.getTransaction().commit();
		session.close();		
	}
	
	public List<Rodamiento> levantarRodamientos() {
		Session session = sf.openSession();
		Query query = session.createQuery("from Rodamiento");
		List<Rodamiento> rodamiento= query.list();
		return rodamiento;
	}
	
	public List<Rodamiento> getRodamientosByCriterio(Rodamiento rodamiento) {
		Session session = sf.openSession();
		StringBuffer hql = new StringBuffer();
		int cantCriterios = 0;
		cantCriterios = hayCriterio(rodamiento, cantCriterios);
		hql.append("FROM Rodamiento r");
		if (cantCriterios>0){
			hql.append(" WHERE ");
//			if (null != rodamiento.getBeanMarca().g.getId()){
			if (null != rodamiento.getRodamientoId().getMarca().getMarcaId().getDescripcion()
					|| null != rodamiento.getRodamientoId().getMarca().getMarcaId().getPais()){
//				hql.append(" r.marca.id = :marcaId");
				hql.append(" r.rodamientoId.marca = :marca");
				cantCriterios--;
				if (cantCriterios>0)
					hql.append(" AND");
			}
			if (!rodamiento.getRodamientoId().getCodigo().isEmpty()){
				hql.append(" r.rodamientoId.codigo = :codigo");
				cantCriterios--;
				if (cantCriterios>0)
					hql.append(" AND");
			}
			if (!rodamiento.getTipo().isEmpty()){
				hql.append(" r.tipo = :tipo");
			}
		}
		try{
		Query query = session.createQuery(hql.toString());
//		if (null != rodamiento.getBeanMarca().getId())
		if (null != rodamiento.getRodamientoId().getMarca().getMarcaId().getDescripcion()
				|| null != rodamiento.getRodamientoId().getMarca().getMarcaId().getPais())
//			query.setParameter("marcaId", rodamiento.getBeanMarca().getId());
			query.setEntity("marca", rodamiento.getRodamientoId().getMarca());
		if (!rodamiento.getRodamientoId().getCodigo().isEmpty())
			query.setParameter("codigo", rodamiento.getRodamientoId().getCodigo());
		if (!rodamiento.getTipo().isEmpty())
			query.setParameter("tipo", rodamiento.getTipo());
		return query.list();
		}
		catch(Exception e){
			e.toString();
			return null;
		}
	}
	
	private int hayCriterio(Rodamiento rodamiento, int cantCriterios){
//		if (null != rodamiento.getBeanMarca().getId()){
		if (null != rodamiento.getRodamientoId().getMarca().getMarcaId().getDescripcion()
				|| null != rodamiento.getRodamientoId().getMarca().getMarcaId().getPais()){
			cantCriterios ++;
		}
		if (!rodamiento.getRodamientoId().getCodigo().isEmpty()){
			cantCriterios ++;
		}
		if (!rodamiento.getTipo().isEmpty()){
			cantCriterios ++;
		}
		return cantCriterios;
	}
	
	public void borrarRodamiento (Rodamiento rodamiento){
		Session session = sf.openSession();
		String borrar = "DELETE FROM Rodamiento r WHERE r.rodamientoId = :id";
		Query query = session.createQuery(borrar);
		query.setParameter("id", rodamiento.getRodamientoId());
		query.executeUpdate();
	}

	public void actualizarRodamiento (Rodamiento rNuevo){
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(rNuevo);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}

}
