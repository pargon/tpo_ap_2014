package test;

import hbt.dao.HibernateDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Cliente;
import model.Factura;
import model.Remito;


public class Test {
	private static HibernateDAO hdao;
	
	public Test(){
	
	}
	
	public static void persistirCliente(Object c){
		hdao.getInstancia().persistir(c);
	}
	
	
	public static void main(String[] args) {
		
		Cliente c = new Cliente();
		c.setCuit("2132131");
		c.setRazonSocial("Razon SRL");
		persistirCliente(c);
		
		Remito r = new Remito();
		r.setCliente(c);
		
		Remito r2 = new Remito();
		r.setCliente(c);
		
		@SuppressWarnings("deprecation")
		Date d = new Date(14,01,01);
		
		r.setFecha( d);
		r2.setFecha(d);
		persistirCliente(r);
		persistirCliente(r2);
		
		List<Remito> lr = new ArrayList<Remito>();
		lr.add(r);
		lr.add(r2);
		
		Factura f = new Factura();
		f.setRemitos(lr);
		persistirCliente(f);
		
		Factura f2 = new Factura();
		f.setRemitos(lr);
		try{
		persistirCliente(f2);
		}catch(Exception e)
		{	e.printStackTrace();}
		
		System.exit(0);
	}

}


