package RMI.Server;

import Interfaz.InterfazRMI;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import model.ClienteSRV;
import RMI.controller.*;
import beans.*;
import model.Cliente;

//import com.thoughtworks.xstream.XStream;
//import com.thoughtworks.xstream.io.xml.DomDriver;


public class Server {
    
	RMIController objetoRemoto;
	
	public Server() {
		iniciar();
	}

	public static void main(String[] args)
	{
		System.out.println("Ejecuta main");	
		new Server();	
		
		System.out.println("Ejecuta Carga de clientes");	
		BeansCliente nuevoClienteBeans = new BeansCliente();
		nuevoClienteBeans.setCuit("20-30734253-222");
		nuevoClienteBeans.setContacto("254847");
		nuevoClienteBeans.setPorcentajeDesc((float)1.51);
		nuevoClienteBeans.setRazonSocial("Jorge y Cia 222");
		nuevoClienteBeans.setTelefono("15654898897");
		Cliente cliente = ClienteSRV.getinstancia().fromBean(nuevoClienteBeans);
		ClienteSRV.getinstancia().guardar(cliente);
		
		System.out.println("Ejecuta Modificacion de clientes");
		Cliente modificacionCliente = new Cliente();
		modificacionCliente = ClienteSRV.getinstancia().buscarClientes("Jorge y Cia 222", "20-30734253-222").get(0);
		modificacionCliente.setPorcentajeDesc((float)2.2);
		ClienteSRV.getinstancia().actualizarCliente(modificacionCliente);
		

		System.out.println("Ejecuta Eliminacion de clientes");
		Cliente eliminaCliente = new Cliente();
		eliminaCliente = ClienteSRV.getinstancia().buscarClientes("Jorge y Cia 222", "20-30734253-222").get(0);
		ClienteSRV.getinstancia().borrarCliente(eliminaCliente);

	}

	public void iniciar() {
    	try {
    		LocateRegistry.createRegistry(1099);	
    		InterfazRMI controller = new RMIController();
            Naming.rebind ("//localhost/rmiController", controller);
            System.out.println("Fijado en //localhost/rmiController");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	

	
	
}
