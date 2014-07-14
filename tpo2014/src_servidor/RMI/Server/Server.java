package RMI.Server;

import Interfaz.InterfazRMI;
import RMI.controller.RMIController;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;


public class Server {
    
	protected InterfazRMI objetoRemoto;
	
	public Server() {
		iniciar();
	}

	public static void main(String[] args)
	{
		new Server();
		
	}

	public void iniciar() {
		//System.setSecurityManager(new RMISecurityManager());
    	try {
    		System.getProperty("java.security.policy");
    		LocateRegistry.createRegistry(1099);	
    	    objetoRemoto = new RMIController();
            Naming.rebind ("//localhost:1099/rmiController", objetoRemoto);
           
            System.out.println("Fijado en //localhost:1099/rmiController");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	

	
	
}
