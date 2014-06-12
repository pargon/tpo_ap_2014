package model;

import hbt.dao.HibernateClientesDAO;

import java.util.List;

import beans.BeansCliente;

public class ClienteSRV {
	
	private static ClienteSRV instancia;

	public static ClienteSRV getinstancia(){
		if (instancia == null){
			instancia = new ClienteSRV();
		}
		return instancia;
	}

	public Cliente fromBean(BeansCliente beanCliente) {
		Cliente cliente = new Cliente();
		cliente.setCuit(beanCliente.getCuit());
		cliente.setContacto(beanCliente.getContacto());
		cliente.setPorcentajeDesc(beanCliente.getPorcentajeDesc());
		cliente.setRazonSocial(beanCliente.getRazonSocial());
		cliente.setTelefono(beanCliente.getTelefono());
		return cliente;
	}

	public void guardar(Cliente c) {
		HibernateClientesDAO.getInstancia().guardarCliente(c);
	}
	
	public List<Cliente> levantarClientes() {
		try{
		return HibernateClientesDAO.getInstancia().levantarClientes();
		}catch(Exception e){
			e.toString();
			return null;
		}
	}

	public List<Cliente> buscarClientes(String nombreCliente, String cuitCliente) {
		try{
			return HibernateClientesDAO.getInstancia().buscarClientes(nombreCliente,cuitCliente);
			}catch(Exception e){
				e.toString();
				return null;
			}
	}
	
	public boolean actualizarCliente (Cliente cliente){
		return HibernateClientesDAO.getInstancia().actualizarCliente(cliente);
	}

	public boolean borrarCliente(Cliente cliente) {
		return HibernateClientesDAO.getInstancia().borrarCliente(cliente);
	}
	
}