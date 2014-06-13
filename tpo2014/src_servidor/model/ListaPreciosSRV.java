package model;

import hbt.dao.HibernateItemRodamientoDAO;
import hbt.dao.HibernateListaPreciosDAO;

import java.util.List;

import beans.BeanListaPrecios;
import beans.BeansListaPrecios;

public class ListaPreciosSRV {
	private static ListaPreciosSRV instancia;

	public static ListaPreciosSRV getinstancia(){
		if (instancia == null){
			instancia = new ListaPreciosSRV();
		}
		return instancia;
	}
	
	public List<ListaPrecios> levantarListasPrecios() {
		return HibernateListaPreciosDAO.getInstancia().levantarListasPrecios();
	}

	public void guardarLista(ListaPrecios listaPrecios) {
		HibernateListaPreciosDAO.getInstancia().guardarListaPrecios(listaPrecios);		
	}
	
	public ListaPrecios fromBean (BeansListaPrecios beansListaPrecios){
		return HibernateListaPreciosDAO.getInstancia().fromBeanListasPrecios(beansListaPrecios);
	}
}
