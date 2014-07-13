package hbt.dao;

import model.*;
import model.Rodamiento.RodamientoId;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;



@SuppressWarnings("deprecation")
public class HibernateUtil
{
	private static final SessionFactory sessionFactory;

	static
	{
		try
		{
			AnnotationConfiguration config = new AnnotationConfiguration();
			config.addAnnotatedClass(Cliente.class);
			config.addAnnotatedClass(Usuario.class);
			config.addAnnotatedClass(Perfil.class);
			config.addAnnotatedClass(OficinaVenta.class);
			config.addAnnotatedClass(ItemRodamiento.class);
			config.addAnnotatedClass(ListaPrecios.class);
			config.addAnnotatedClass(ListaOfertas.class);
			config.addAnnotatedClass(Rodamiento.class);
//			config.addAnnotatedClass(Marca.class);
			config.addAnnotatedClass(Proveedor.class);
			config.addAnnotatedClass(OrdenCompra.class);
			config.addAnnotatedClass(CotizacionRodamiento.class);
			
			config.addAnnotatedClass(Factura.class);
			config.addAnnotatedClass(Remito.class);
			config.addAnnotatedClass(CasaCentral.class);
			config.addAnnotatedClass(MovimientoStock.class);
			config.addAnnotatedClass(OrdenPedido.class);
			config.addAnnotatedClass(SolicitudCotizacion.class);
			config.addAnnotatedClass(ItemSolicitudCotizacion.class);
			
			sessionFactory = config.buildSessionFactory();
		}
		catch (Throwable ex)
		{
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
}
