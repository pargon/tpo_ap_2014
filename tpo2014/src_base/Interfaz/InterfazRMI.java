package Interfaz;

import java.rmi.Remote;
import java.rmi.RemoteException;

import beans.BeansCliente;

public interface InterfazRMI extends Remote {

	public abstract boolean agregarCliente(BeansCliente dtoCliente) throws RemoteException;
	public abstract boolean borrarCliente (BeansCliente beanCliente) throws RemoteException;
	public abstract boolean actualizarCliente (BeansCliente beanCliente) throws RemoteException;
	
}
