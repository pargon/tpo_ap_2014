package controlador;



import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;

import beans.BeanCotizacionRodamiento;
import beans.BeanSolicitudCotizacion;
import Interfaz.InterfazRMI;


public class RemotoClient {
	private static RemotoClient instancia;
	private static String hostname = "localhost";
	InterfazRMI rmiController;

	public static void main(String[] args)
	{
		new RemotoClient();
	}

	public static RemotoClient getInstancia(){
		if (instancia==null){
			instancia = new RemotoClient();
		}
		return instancia;
	}

	public RemotoClient(){
		instancia = this;
		getStub();
	}

	private boolean getStub() {
		try {
			rmiController = (InterfazRMI)Naming.lookup ("//"+hostname+"/rmiController");
			System.out.println("GetStub");
			return true;
		} catch (Exception e) {
			System.out.println("No me puedo conectar al server");
			e.printStackTrace();
		}
		return false;
	}
	

	public String guardarCotizacion(
			BeanSolicitudCotizacion solcot) {
		try{
			int idCot = rmiController.guardarSolicitudCotizacion(solcot);
			if (idCot > 0)
				return "Se genero Cotizacion: " + String.valueOf(idCot);
			else
				return "Hubo error de datos en la Solicitud";
			
		}catch (RemoteException e){
			e.printStackTrace();
		}
		return null;
		
	}
	
}
	
	