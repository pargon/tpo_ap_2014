package RMI.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import beans.BeansCliente;
import Interfaz.InterfazRMI;
import model.Cliente;
import model.ClienteSRV;

public class RMIController extends UnicastRemoteObject implements InterfazRMI {

	public RMIController() throws RemoteException {
		super();
	}

	private static final long serialVersionUID = 1L;

	@Override
	public boolean agregarCliente(BeansCliente dtoCliente) throws RemoteException {
		System.out.println("Alta Cliente en Servidor!");
		Cliente c = ClienteSRV.getinstancia().fromBean(dtoCliente);
		System.out.println(c.getCuit());
		ClienteSRV.getinstancia().guardar(c);
		return true;
	}

	@Override
	public boolean borrarCliente(BeansCliente beanCliente) throws RemoteException {
		System.out.println("Ejecuta Modificacion de clientes");
		Cliente modificacionCliente = new Cliente();
		modificacionCliente = ClienteSRV.getinstancia().buscarClientes("Jorge y Cia 222", "20-30734253-222").get(0);
		modificacionCliente.setPorcentajeDesc((float)2.2);
		ClienteSRV.getinstancia().actualizarCliente(modificacionCliente);
		return true;
	}

	@Override
	public boolean actualizarCliente(BeansCliente beanCliente) throws RemoteException {
		System.out.println("Ejecuta Eliminacion de clientes");
		Cliente eliminaCliente = new Cliente();
		eliminaCliente = ClienteSRV.getinstancia().buscarClientes("Jorge y Cia 222", "20-30734253-222").get(0);
		ClienteSRV.getinstancia().borrarCliente(eliminaCliente);
		return true;
	}

}
