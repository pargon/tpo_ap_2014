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
	        //crea el elemento ra�z y lo agrega al documento
	            Element root = doc.createElement("OrdenDeCompra");
	            //
		     //crea comentario y lo agrega a continuaci�n
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
	            	marca.setTextContent(itr.getRodamiento().getRodamientoId().getMarca().getDescripcion());
	            	item.appendChild(marca);
	            
	            	Element origen = doc.createElement("Origen");
	            	origen.setTextContent(itr.getRodamiento().getRodamientoId().getMarca().getPais());
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
		    //m�todo transform que enlaza el documento con el stream
		    tran.transform(src, dest); 
		    System.out.println("archivo creado");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public List<ItemRodamiento> PedidoVSCompra(OrdenCompra oc, OrdenPedido op) {

		String sql = "select itroc from OrdenCompra c"
				+ " join c.pedidos p"
				+ " join p.cot.itemsRodamiento itroc"				
				+ " where CAST(c.id as string) = :idoc"
				+ " and CAST(p.id as string) = :idop"
				+ " and exists( select itc from c.itemsOC itc"
				+ "             where itc.rodamiento.rodamientoId.codigo = itroc.rodamiento.rodamientoId.codigo)";
		
		return (List<ItemRodamiento> )HibernateDAO.getInstancia().parametros2(sql, "idoc", String.valueOf(oc.getId()) , "idop", String.valueOf(op.getId() ));
	}

	public void cumplimientoOPedido(OrdenCompra oc) {
		
		// busca pedidos que partieron en OC, del cliente y a�n no est�n completos
		String sql = "select p from OrdenCompra oc "
				+ "	join oc.pedidos p "
				+ " join p.cot c"
				+ " join c.itemsRodamiento it"
				+ " where p.estado = 'OC'"
				+ " and it.cantidad < ( select sum(itr.cantidad) from Remito r"
				+ "					join r.items itr"
				+ "					where r.op.id = p.id"
				+ "					and r.estado = 'PAR'"
				+ "					and itr.rodamiento.rodamientoId.codigo = it.rodamiento.rodamientoId.codigo)";
		
		List<OrdenPedido> lop = (List<OrdenPedido>) HibernateDAO.getInstancia().getlista(sql);	
		
		boolean aunPendiente;
		
		// pedidos de la Oc
		List<OrdenPedido> lpoc = oc.getPedidos();
		for(OrdenPedido opc: lpoc){
			aunPendiente = false;
			
			// pedidos a�n pendientes en la OC
			for(OrdenPedido op: lop){
				if (opc.getId().equals(op.getId())){
					aunPendiente = true;
					break;
				}
			}
			// Si ya est� completo, el pedido est� listo, lo marca como COMPLETO 
			if (!aunPendiente ){
				opc.setEstado("COM");
				HibernateDAO.getInstancia().persistir(opc);
			}
		}
			
	}

	public List<Remito> remitosParaEmitir(OrdenCompra oc) {
		
		// busca remitos en estado PAR, los cuales tengan su pedido perteneciente a la OC, pero estos deben estar completos
		String sql = "select r from Remito r"
				+ " where r.op.estado = 'COM'"
				+ "	and r.estado = 'PAR'"
				+ " and exists(select c from OrdenCompra c"
				+ "			join c.pedidos p"
				+ "			where p.id = r.op.id"
				+ "			and CAST(c.id as string) = :idoc)";
		return (List<Remito>) HibernateDAO.getInstancia().parametros(sql, "idoc", String.valueOf( oc.getId()));
		
	}	
	
}
