package RMI.Server;

import Interfaz.InterfazRMI;
import RMI.controller.RMIController;

import java.io.FileWriter;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.ClienteSRV;
import model.CotizacionRodamiento;
import model.FacturaSRV;
import model.Remito;
import beans.*;
import model.Cliente;
import model.Factura;

import org.jdom2.Attribute;
import org.jdom2.Document;            
import org.jdom2.Element;             
import org.jdom2.output.Format;       
import org.jdom2.output.XMLOutputter; 


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
