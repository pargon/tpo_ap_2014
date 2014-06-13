package Interfaz;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import beans.BeansCliente;
import beans.BeansFactura;

public interface InterfazRMI extends Remote {

	//--Metodos de ABM de Clientes----//
	public abstract boolean agregarCliente(BeansCliente dtoCliente) throws RemoteException;
	public abstract boolean borrarCliente (BeansCliente beanCliente) throws RemoteException;
	public abstract boolean actualizarCliente (BeansCliente beanCliente) throws RemoteException;

	
	//--Metodos de Venta de Rodamientos--//
	public abstract List<BeansFactura> ventaRodamiento(Date fecha, String fhventa) throws RemoteException;
}
