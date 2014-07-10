package model;

import hbt.dao.HibernateDAO;
import hbt.dao.HibernateOPedidoDAO;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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


public class OrdenCompraSRV {

	private static OrdenCompraSRV instancia;
	private static Document doc;
	
	public static OrdenCompraSRV getinstancia(){
		if (instancia == null){
			instancia = new OrdenCompraSRV();
		}
		return instancia;
	}
	
	public OrdenCompraSRV() {
		// TODO Auto-generated constructor stub
	}

	public OrdenCompra confimarRec(Integer idOC) {
		OrdenCompra oc1 = new OrdenCompra();
		oc1.setId(idOC);
		
		OrdenCompra oc = (OrdenCompra) HibernateDAO.getInstancia().get(OrdenCompra.class, oc1.getId());
		
		oc.setEstado("REC");
		HibernateDAO.getInstancia().persistir(oc);
		
		return oc;
	}
	
	
	
	public void newDomXML(OrdenCompra oc) {
		// "OrdenCompra.xml";
	
	    try{
	        //Crea instancia de DocumentBuilderFactory
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        //obtiene objeto DocumentBuilder
	        DocumentBuilder docBuilder = factory.newDocumentBuilder();
	        //Crea documento DOM en blanco
	        	doc = docBuilder.newDocument();
	        //crea el elemento raíz y lo agrega al documento
	            Element root = doc.createElement("OrdenDeCompra");
	            //
		     //crea comentario y lo agrega a continuación
	        	Comment comment = doc.createComment("ORDEN DE COMPRA");  
	           root.appendChild(comment);
	           doc.appendChild(root);
	        //agrega un atributo al elemento y le asigna un valor
	            root.setAttribute("numero",String.valueOf(oc.getId()));
	         //   hijo.setAttribute("valor","valor del atributo");
	          //  root.appendChild(hijo);
     
		        //crea elemento hijo
	            Element emision = doc.createElement("Emision");
	            emision.setTextContent(String.valueOf(oc.getFecha()));
	            root.appendChild(emision);
	            
	            Element cliente = doc.createElement("Cliente");
	            root.appendChild(cliente);
	           
	            Element cuil = doc.createElement("Cuil");
	            cuil.setTextContent(String.valueOf(oc.getProveedor().getCuit()));
	            cliente.appendChild(cuil);
	            
	            Element razonsoc = doc.createElement("RazonSocial");
	            razonsoc.setTextContent(String.valueOf(oc.getProveedor().getRazonSocial()));
	            cliente.appendChild(razonsoc);
	            
	            Element rodamiento = doc.createElement("Rodamiento");
	            root.appendChild(rodamiento);
	           
	            // recorre los items de la OC
	            List<ItemRodamiento> ldetoc = oc.getItemsOC();
	            for(ItemRodamiento itr: ldetoc){
	            	
	            	Element item = doc.createElement("Item");
	            	rodamiento.appendChild(item);
	            	item.setAttribute("serie", itr.getRodamiento().getRodamientoId().getCodigo());
	            
	            	Element sufijo = doc.createElement("Sufijo");
	            	sufijo.setTextContent(itr.getRodamiento().getTipo());
	            	item.appendChild(sufijo);
	            
	            	Element marca = doc.createElement("Marca");
	            	marca.setTextContent(itr.getRodamiento().getRodamientoId().getMarca().getMarcaId().getDescripcion());
	            	item.appendChild(marca);
	            
	            	Element origen = doc.createElement("Origen");
	            	origen.setTextContent(itr.getRodamiento().getRodamientoId().getMarca().getMarcaId().getPais());
	            	item.appendChild(origen);
	            
	            	Element cantidad = doc.createElement("Cantidad");
	            	cantidad.setTextContent(String.valueOf( itr.getCantidad()));
	            	item.appendChild(cantidad);
	            }
	            
	    }catch(Exception e){
	    	System.out.println(e.getMessage());
	    }
	}
	        
	public void saveDomXML(String archivo) {
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
		    System.out.println("archivo creado");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
