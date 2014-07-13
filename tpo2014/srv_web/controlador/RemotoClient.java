package controlador;



import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;

import beans.BeanCotizacionRodamiento;
import beans.BeanOP;
import beans.BeanSolicitudCotizacion;
import Interfaz.InterfazRMI;


public class RemotoClient {
	
	private static RemotoClient instancia;
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
			System.getProperty("java.security.policy");
			rmiController = (InterfazRMI)Naming.lookup ("//localhost:1099/rmiController");
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
	public List<BeanOP> buscarOP() {
		try{
			return  rmiController.buscarOP();
		}catch (RemoteException e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	public String confirmaCotizacion(int idCot){
		try {
			rmiController.crearOrdenPedido(idCot);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Exito";
	}
	
}
	
	