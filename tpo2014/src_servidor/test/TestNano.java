package test;

import java.util.ArrayList;
import java.util.List;

import model.ListaPreciosSRV;

public class TestNano {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> archivos = new ArrayList<String>();
		archivos.add("C:\\ListaPrecio1.xml");
		archivos.add("C:\\ListaPrecio2.xml");
		new ListaPreciosSRV().getinstancia().getListas(archivos);

	}

}
