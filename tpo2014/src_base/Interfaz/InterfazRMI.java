package Interfaz;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import beans.BeanCotizacionRodamiento;
import beans.BeanFacturaSmall;
import beans.BeanOP;
import beans.BeanRemito;
import beans.BeanSolicitudCotizacion;
import beans.BeansCliente;
import beans.BeansFactura;
import beans.BeansRemito;

public interface InterfazRMI extends Remote {

	//--Metodos de ABM de Clientes----//
	public abstract boolean agregarCliente(BeansCliente dtoCliente) throws RemoteException;
	public abstract boolean borrarCliente (BeansCliente beanCliente) throws RemoteException;
	public abstract boolean actualizarCliente (BeansCliente beanCliente) throws RemoteException;

	
	//--Metodos de Venta de Rodamientos--//
	public abstract BeansFactura ventaRodamiento(List<BeansRemito> beansremitos) throws RemoteException;
	
	//--Orden de compra--//
	public abstract void crearOrdenCompra() throws RemoteException;
	//--Recepción Mercaderia--//
	public abstract void recepcionMercaderia(int idOrdenCompra) throws RemoteException;
	//--Orden de pedido--//
	public abstract void crearOrdenPedido(int idCotizacion) throws RemoteException;
	
	//--Facturar--//
	public abstract void facturar() throws RemoteException;
	
	public abstract List<BeanOP> buscarOP() throws RemoteException;
	
	public abstract List<BeanRemito> buscarRemitos() throws RemoteException;
	
	public abstract List<BeanFacturaSmall> buscarFacturas() throws RemoteException;
		
	
	public abstract int guardarSolicitudCotizacion(BeanSolicitudCotizacion beanSolicitudCotizacion) throws RemoteException;
}
