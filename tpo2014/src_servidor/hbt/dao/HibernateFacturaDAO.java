package hbt.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.HibernateUtil;
import model.CotizacionRodamiento;
import model.Factura;
import model.ItemCotizacion;
import model.Remito;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.mapping.Array;

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
	
	public List<BeansFactura> Facturar(Date fecha, int idCliente)
	{
		try{
			//Obtengo los remitos que no estan facturados para un cliente			
			Session session = sf.openSession();
			Query query = session.createQuery("from remitos as rem where rem.id not in (select fm.idremito from fac_rem as fm) AND rem.cliente.id = :cliente_id");
			query.setParameter("cliente_id", idCliente);
			List<Remito> remitos = query.list();
			
			float total;
			//Por cada remito que pertenece a un cliente se lo asocia a una misma factura
			for (Remito remito : remitos) {
				List<ItemCotizacion> it = remito.getCotizacion().getItemsCotizacion();
				for (ItemCotizacion itemCotizacion : it) {
					total = itemCotizacion.getCantidad() * itemCotizacion.getItemRodamiento().getPrecio() + total;
				}
			}

			
			
			BeansFactura f = new BeansFactura();
			f.setFecha(fecha);
			f.setRemitos(remitos);
			f.setTotal(total);
			List<BeansFactura> facturas = new ArrayList<BeansFactura>();
			return facturas.add(f);
		}
		catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

}
