package model;

import hbt.dao.HibernateFacturaDAO;
import hbt.dao.HibernateOPedidoDAO;
import java.io.*;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


import java.util.List;

public class OrdenPedidoSRV {


	private static OrdenPedidoSRV instancia;
	private static Document doc;

	public static OrdenPedidoSRV getinstancia(){
		if (instancia == null){
			instancia = new OrdenPedidoSRV();
		}
		return instancia;
	}
	public OrdenPedidoSRV() {
		// TODO Auto-generated constructor stub
	}
	
	public List<OrdenPedido> getListaPendientes() {

		String sql = "from OrdenPedido where estado=:est ";
		return (List<OrdenPedido>) HibernateOPedidoDAO.getInstancia().parametros(sql, "est", "PEN");
	}

		
		
		public void newDomXML(String archivo) {
			// "OrdenPedido.xml";
		
		    try{
		        //Crea instancia de DocumentBuilderFactory
		        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		        //obtiene objeto DocumentBuilder
		        DocumentBuilder docBuilder = factory.newDocumentBuilder();
		        //Crea documento DOM en blanco
		        	doc = docBuilder.newDocument();
		        //crea el elemento raíz y lo agrega al documento
		            Element root = doc.createElement("OrdenDePedido");
		            //
			     //crea comentario y lo agrega a continuación
		        	Comment comment = doc.createComment("ORDEN DE PEDIDO");  
		           root.appendChild(comment);
		           doc.appendChild(root);
		        //agrega un atributo al elemento y le asigna un valor
		            root.setAttribute("numero","Nro proporcionado por el cliente");
		         //   hijo.setAttribute("valor","valor del atributo");
		          //  root.appendChild(hijo);
	     
			        //crea elemento hijo
		            Element hijo = doc.createElement("Fecha");
		            hijo.setTextContent("Fecha de la Orden");
		            root.appendChild(hijo);
		           
		            hijo = doc.createElement("nodo");
		            hijo.setAttribute("id","id02");
		            root.appendChild(hijo);
		            
		            Element prm = doc.createElement("parametro");
		            prm.setTextContent("contenido");
		            hijo.appendChild(prm);
		    }catch(Exception e){
		    	System.out.println(e.getMessage());
		    }
		}
		        
		void saveDomXML(String archivo) {
			//Crea instancia de TransformerFactory
		    TransformerFactory tranFactory = TransformerFactory.newInstance(); 
		    Transformer tran;
			try {
				//Crea un objeto Transformer
				tran = tranFactory.newTransformer();
				//interface Source implementada con un documento dom 
			    Source src = new DOMSource(doc); 
			    //interface Result implementada con un subtipo de OutputStream 
			    Result dest = new StreamResult(new FileWriter(new File(archivo)));
			    //método transform que enlaza el documento con el stream
			    tran.transform(src, dest); 
			    System.out.print("archivo creado");
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		
		public static void main(String[] args) {
			OrdenPedidoSRV ins = new OrdenPedidoSRV();
			ins.newDomXML("C:\\Lenguajes Visuales\\OrdenPedido.xml");
			ins.saveDomXML("C:\\Lenguajes Visuales\\OrdenPedido.xml");
		}

	}
