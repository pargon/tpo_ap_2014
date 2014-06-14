package hbt.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import util.HibernateUtil;
import model.ClienteSRV;
import model.CotizacionRodamiento;
import model.Factura;
import model.ItemCotizacion;
import model.Remito;
import net.sourceforge.jtds.jdbc.DateTime;
import beans.*;

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

	public boolean guardarRemito(Remito r) {
		try {
			Session session = sf.openSession();
			session.beginTransaction();
			session.persist(r);
			session.flush();
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public BeansFactura Facturar(List<BeansRemito> beansremitos)
	{
		try{
			
			float total = 1000;
			for (BeansRemito beansremito : beansremitos) {
//				List<BeansItemCotizacionRodamiento> it = beansremito.getCotizacion().getBeanitemsCotizacion();
//				for (BeansItemCotizacionRodamiento beansitemCotizacion : it) {
					//total = beansitemCotizacion.getCantidad() * beansitemCotizacion.getBeanitemsRodamiento().getPrecio() + total;
//				}
			}

			BeansFactura f = new BeansFactura();
			f.setFecha(Calendar.getInstance().getTime());
			f.setRemitos(beansremitos);
			f.setTotal(total);
			f.setNroFactura(10215);		
			return f;
		}
		catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

	public List<BeansRemito> buscarRemitoPendientesFacturacion(int idcliente) {
		Session session = sf.openSession();
		//Query query = session.createQuery("from Remito as rem where rem.id not in (select fm.idremito from Factura.fac_rem as fm) AND rem.Cliente.cliente_id =: cliente_id");
		//query.setParameter("cliente_id", idcliente); 
		Query query = session.createQuery("from Remito");
		List<Remito> remitos = new ArrayList<Remito>();
		remitos = query.list();
		
		List<BeansRemito> beansremitos = new ArrayList<BeansRemito>();		
		for (Remito remito : remitos) {
			BeansRemito beansremito = new BeansRemito();	
			//Creo BeansCliente en base al remito
			BeansCliente beansCliente = new BeansCliente();
			beansCliente.setContacto(remito.getCliente().getContacto());
			beansCliente.setCuit(remito.getCliente().getCuit());
			beansCliente.setPorcentajeDesc(remito.getCliente().getPorcentajeDesc());
			beansCliente.setRazonSocial(remito.getCliente().getRazonSocial());
			beansCliente.setTelefono(remito.getCliente().getTelefono());
			beansremito.setCliente(beansCliente);
			beansremitos.add(beansremito);
		}
		
		return beansremitos;
	}

}
