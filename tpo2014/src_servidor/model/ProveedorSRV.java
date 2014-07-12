package model;

import hbt.dao.HibernateProveedorDAO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import model.Marca.MarcaId;
import model.Rodamiento.RodamientoId;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.w3c.dom.Node;

import beans.BeansListaPrecios;

public class ProveedorSRV {
	private static ProveedorSRV instancia;

	public static ProveedorSRV getinstancia(){
		if (instancia == null){
			instancia = new ProveedorSRV();
		}
		return instancia;
	}
	
	public List<Proveedor> levantarProveedores() {
		return HibernateProveedorDAO.getInstancia().levantarProveedores();
	}

	public void guardarProveedor(Proveedor prov) {
		HibernateProveedorDAO.getInstancia().guardarProveedor(prov);		
	}
}
