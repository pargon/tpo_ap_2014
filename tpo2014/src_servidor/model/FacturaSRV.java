package model;

import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import hbt.dao.HibernateFacturaDAO;
import beans.BeansFactura;
import beans.BeansRemito;



public class FacturaSRV {
	
	private static FacturaSRV instancia;
	private static Document doc;

	public static FacturaSRV getinstancia(){
		if (instancia == null){
			instancia = new FacturaSRV();
		}
		return instancia;
	}

	public Factura fromBean(BeansFactura beanFactura) {
		Factura factura = new Factura();
		factura.setFecha(beanFactura.getFecha());
		factura.setNroFactura(beanFactura.getNroFactura());
		factura.setTotal(beanFactura.getTotal());
		return factura;
	}

	public boolean guardar(Factura factura) {
		return HibernateFacturaDAO.getInstancia().guardarFactura(factura);
	}
	
	public BeansFactura Facturar(List<BeansRemito> beansremitos) {
		return HibernateFacturaDAO.getInstancia().Facturar(beansremitos);
	}
		
	public void newDomXML(Factura f) {
		// "Factura.xml";
	
	    try{
	        //Crea instancia de DocumentBuilderFactory
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        //obtiene objeto DocumentBuilder
	        DocumentBuilder docBuilder = factory.newDocumentBuilder();
	        //Crea documento DOM en blanco
	        	doc = docBuilder.newDocument();
	        //crea el elemento raíz y lo agrega al documento
	            Element root = doc.createElement("Factura");
	            //
		     //crea comentario y lo agrega a continuación
	        	Comment comment = doc.createComment("FACTURA");  
	           root.appendChild(comment);
	           doc.appendChild(root);
	        //agrega un atributo al elemento y le asigna un valor
	            root.setAttribute("numero",String.valueOf(f.getNroFactura()));
	         //   hijo.setAttribute("valor","valor del atributo");
	          //  root.appendChild(hijo);
     
		        //crea elemento hijo
	            Element emision = doc.createElement("Emision");
	            emision.setTextContent(String.valueOf(f.getFecha()));
	            root.appendChild(emision);
	            
	            Element cliente = doc.createElement("Cliente");
	            root.appendChild(cliente);
	           
	            Element cuil = doc.createElement("Cuil");
	            cuil.setTextContent(String.valueOf(f.getCliente().getCuit()));
	            cliente.appendChild(cuil);
	            
	            Element razonsoc = doc.createElement("RazonSocial");
	            razonsoc.setTextContent(String.valueOf(f.getCliente().getRazonSocial()));
	            cliente.appendChild(razonsoc);
	            
	            Element condVenta = doc.createElement("CondicionesDeVenta");
	            root.appendChild(condVenta);
	            
	            Element pagoContado = doc.createElement("PagoContado");
	            condVenta.appendChild(pagoContado);
	            
	            Element desc = doc.createElement("Descuento");
	            desc.setTextContent(String.valueOf(f.getCliente().getPorcentajeDesc()));
	            pagoContado.appendChild(desc);
	            
	            Element financ = doc.createElement("Financiacion");
	            condVenta.appendChild(financ);
	            
	           /* Element cantDias = doc.createElement("CantidadDias");
	            cantDias.setTextContent(String.valueOf(f.getCliente().?));
	            financ.appendChild(cantDias);*/
	            
		           /* Element recargo = doc.createElement("Recargo");
		            recargo.setTextContent(String.valueOf(f.getCliente().?));
		            financ.appendChild(recargo);*/
	            
	            Element referencia = doc.createElement("Referencia");
	            root.appendChild(referencia);
	           
	            
	            List<Remito> remitos = f.getRemitos();
	            //recorre los Remitos
	            for(Remito r: remitos){
	            	
	            	Element remito = doc.createElement("Remito");
	 	            referencia.appendChild(remito);
	 	            
	 	           Element numero = doc.createElement("Numero");
		            numero.setTextContent(String.valueOf(r.getId()));
		            remito.appendChild(numero);
		            
		            Element fecha = doc.createElement("Fecha");
		            fecha.setTextContent(String.valueOf(r.getFecha()));
		            remito.appendChild(fecha);
	 	            
	 	           Element rodamiento = doc.createElement("Rodamiento");
	 	            referencia.appendChild(rodamiento);
	            	
	            	List<ItemRodamiento> ldetf = r.getItems();
	            	// recorre los items de los Remitos
	            	for(ItemRodamiento itr: ldetf){
	            	
	            	Element item = doc.createElement("Item");
	            	rodamiento.appendChild(item);
	            	item.setAttribute("serie", itr.getRodamiento().getRodamientoId().getCodigo());
	            
	            	Element sufijo = doc.createElement("Sufijo");
	            	sufijo.setTextContent(itr.getRodamiento().getTipo());
	            	item.appendChild(sufijo);
	            
	            	Element marca = doc.createElement("Marca");
	            	marca.setTextContent(itr.getRodamiento().getRodamientoId().getMarca().getDescripcion());
	            	item.appendChild(marca);
	            
	            	Element origen = doc.createElement("Origen");
	            	origen.setTextContent(itr.getRodamiento().getRodamientoId().getMarca().getPais());
	            	item.appendChild(origen);
	            
	            	Element cantidad = doc.createElement("Cantidad");
	            	cantidad.setTextContent(String.valueOf( itr.getCantidad()));
	            	item.appendChild(cantidad);
	            }
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