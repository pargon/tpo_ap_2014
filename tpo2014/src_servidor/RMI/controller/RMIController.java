package RMI.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import beans.BeansCliente;
import Interfaz.InterfazRMI;

public class RMIController extends UnicastRemoteObject implements InterfazRMI {

	protected RMIController() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

	@Override
	public boolean agregarCliente(BeansCliente dtoCliente)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean borrarCliente(BeansCliente beanCliente)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean actualizarCliente(BeansCliente beanCliente)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

}
