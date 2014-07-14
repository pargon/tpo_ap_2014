package model;

import java.io.File;
import java.io.FileWriter;
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

import hbt.dao.HibernateDAO;

public class RemitoSRV {
	
	private static RemitoSRV instancia;
	private static Document doc;
	
	public static RemitoSRV getinstancia(){
		if (instancia == null){
			instancia = new RemitoSRV();
		}
		return instancia;
	}
	
	public RemitoSRV() {
		
	}

	public List<OrdenPedido> cumplenOPedido(Remito r){
		
		// busca pedidos que partieron en OC, del cliente y aún no están completos
		String sql = "select p from OrdenPedido p "
				+ " join p.cot c"
				+ " join c.itemsRodamiento it"
				+ " where CAST(p.cliente.id as string) =:cli"
				+ " and p.estado = 'OC'"
				+ " and it.cantidad < ( select sum(itr.cantidad) from Remito r"
				+ "					join r.items itr"
				+ "					where itr.rodamiento.rodamientoId.codigo = it.rodamiento.rodamientoId.codigo)";
		
		return (List<OrdenPedido>) HibernateDAO.getInstancia().parametros(sql, "cli", String.valueOf( r.getCliente().getId() ));	
	}
	

	public void newDomXML(Remito rem, List<OrdenPedido> listop) {
		// "OrdenCompra.xml";
	
	    try{
	        //Crea instancia de DocumentBuilderFactory
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        //obtiene objeto DocumentBuilder
	        DocumentBuilder docBuilder = factory.newDocumentBuilder();
	        //Crea documento DOM en blanco
	        doc = docBuilder.newDocument();
	        
	        //crea el elemento raíz y lo agrega al documento
	        Element root = doc.createElement("Remito");
	       
	        //crea comentario y lo agrega a continuación
	        Comment comment = doc.createComment("REMITO");  
	        root.appendChild(comment);
	        doc.appendChild(root);
	        
	        //agrega un atributo al elemento y le asigna un valor
	        root.setAttribute("numero",String.valueOf(rem.getId()));
	        	        
	        //crea elemento hijo
            Element emision = doc.createElement("Emision");
            emision.setTextContent(String.valueOf(rem.getFecha()));
            root.appendChild(emision);
            
	        Element cliente = doc.createElement("Cliente");
	        root.appendChild(cliente);
	           
			Element cuil = doc.createElement("Cuil");
			cuil.setTextContent(String.valueOf(rem.getCliente().getCuit()));
			cliente.appendChild(cuil);
	            
			Element razonsoc = doc.createElement("RazonSocial");
			razonsoc.setTextContent(String.valueOf(rem.getCliente().getRazonSocial()));
			cliente.appendChild(razonsoc);
	            
			Element refer = doc.createElement("Referencia");
	        root.appendChild(refer);
	        
	        // refencia de ordenes de pedido
	        for (OrdenPedido op: listop){
	        	Element ordenpedido = doc.createElement("OrdenPedido");
	        	refer.appendChild(ordenpedido);
	        
	        	Element numped = doc.createElement("Numero");
	        	numped.setTextContent(String.valueOf(op.getId()));
	        	ordenpedido.appendChild(numped);
			
	        	Element fecped = doc.createElement("Fecha");
	        	fecped .setTextContent(String.valueOf(op.getFecha()));
	        	ordenpedido.appendChild(fecped );
	        	
	            Element rodamiento = doc.createElement("Rodamiento");
	            ordenpedido.appendChild(rodamiento);
	           
	            // recorre los items del remito
	            List<ItemRodamiento> ldetrem = rem.getItems();
	            for(ItemRodamiento itr: ldetrem){
	            	
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
		    
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}

