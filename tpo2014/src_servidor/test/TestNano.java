package test;

import hbt.dao.HibernateDAO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import RMI.controller.RMIController;
import beans.BeanItemSolicitudCotizacion;
import beans.BeanMarca;
import beans.BeanRodamiento;
import beans.BeanSolicitudCotizacion;
import beans.BeansCliente;
import model.ListaPreciosSRV;
import model.Marca;
import model.Marca.MarcaId;

public class TestNano {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<String> archivos = new ArrayList<String>();
		archivos.add("C:\\Lenguajes Visuales\\ListaPrecio1.xml");
	//	archivos.add("C:\\ListaPrecio2.xml");
		
		new ListaPreciosSRV().getinstancia().getListas(archivos);

	/*	
		Marca mar = new Marca();
		MarcaId marid= new MarcaId();
		marid.setDescripcion("SDK");
		marid.setPais("Suecia");
		mar.setMarcaId(marid);
		HibernateDAO.getInstancia().persistir(mar);
		System.out.println("marca");
		
		
		BeanSolicitudCotizacion bsc = new BeanSolicitudCotizacion();
		BeanItemSolicitudCotizacion bisc = new BeanItemSolicitudCotizacion();
		BeansCliente bc = new BeansCliente();
		bc.setContacto("Contacto PEPE");
		bc.setCuit("123");
		bc.setPorcentajeDesc((float) 10.00);
		bc.setRazonSocial("Razon social SRL");
		bc.setTelefono("42064885");
		bsc.setBeansCliente(bc);
		bisc.setCantidad(5);
		
		BeanRodamiento br = new BeanRodamiento();
		br.setTipo("Bolilla");
		br.setCodigo("1");
		br.setCaracteristicas("456");
		
		BeanMarca bm = new BeanMarca();
		bm.setDescripcion("SDK");
		bm.setPais("Suecia");
		
		
		br.setBeanMarca(bm);
		bisc.setBeanRodamiento(br);
		List<BeanItemSolicitudCotizacion> blisc = new ArrayList<BeanItemSolicitudCotizacion>();
		blisc.add(bisc);
		bsc.setBeanItemsSolicitudCotizacion(blisc);
		bsc.setFecha(new Date());
		try {
			new RMIController().guardarSolicitudCotizacion(bsc);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//new ListaPreciosSRV().getinstancia().actualizarCantidad("C:\\ListaPrecio1.xml", "3", 200);
*/
	}

}
