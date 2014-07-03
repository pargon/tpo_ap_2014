package model;


import hbt.dao.HibernateListaPreciosDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beans.BeansListaPrecios;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;




import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import model.Marca.MarcaId;
import model.Rodamiento.RodamientoId;

import org.jdom2.Document;         // |
import org.jdom2.Element;          // |\ Librerías
import org.jdom2.JDOMException;    // |/ JDOM
import org.jdom2.input.SAXBuilder; // |
import org.jdom2.output.XMLOutputter;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
	
	public ListaPrecios fromBean (BeansListaPrecios blp){
		return HibernateListaPreciosDAO.getInstancia().fromBeanListasPrecios(blp);
	}
	
	public List<ListaPrecios> getListas(List<String> archivos){
		//Se crea un SAXBuilder para poder parsear el archivo
	    SAXBuilder builder = new SAXBuilder();
	    List<ListaPrecios> listaPosta = new ArrayList<ListaPrecios>();
	    for(int j=0;j<archivos.size();j++){
	    	
		    //File xmlFile = new File( "C:\\ListaPrecio1.xml" );
	    	File xmlFile = new File(archivos.get(j) );
		    ListaPrecios lista = new ListaPrecios();
		    Proveedor prov = new Proveedor();
		     
		    try
		    {
		        //Se crea el documento a traves del archivo
		        Document document = (Document) builder.build( xmlFile );
		 
		        //Se obtiene la raiz 'tables'
		        Element rootNode = document.getRootElement();
		        lista.setId(Integer.parseInt(rootNode.getAttributeValue("numero")));
		        System.out.println(lista.getId());
		        
		        Element proveedor = rootNode.getChild("Proveedor");
		        String cuil = proveedor.getChildTextTrim("Cuil");
		        prov.setCuit(cuil);
		        String rz = proveedor.getChildTextTrim("RazonSocial");
		        prov.setRazonSocial(rz);
		        lista.setProveedor(prov);
		        Element condVenta = rootNode.getChild("CondicionesDeVenta");
		        Element contado = condVenta.getChild("PagoContado");
		        String descuento = contado.getChildTextTrim("Descuento");
		        lista.setDescuento(Integer.valueOf(descuento));
		        List financ =condVenta.getChildren("Financiacion");
		        List<Map<Integer, Float>> listaMapas = new ArrayList<Map<Integer, Float>>();
		        for(int i = 0; i < financ.size();i++){
		        	Element fin = (Element) financ.get(i);
		        	String dias = fin.getChildTextTrim("Dias");
		        	String recargo = fin.getChildTextTrim("Recargo");
		        	Map<Integer,Float> mapa = new HashMap<Integer, Float>();
		        	mapa.put(Integer.valueOf(dias), Float.valueOf(recargo));
		        	listaMapas.add(mapa);
		        }
		        lista.setFinanciacion(listaMapas);
		        
		        //System.out.println(lista.getFinanciacion().toString());
		        Element rod = rootNode.getChild("Rodamientos");
		        List items =rod.getChildren("Item");
		        List<ItemRodamiento> ir = new ArrayList<ItemRodamiento>();
		        for(int i = 0; i < items.size();i++){
		        	Element item = (Element) items.get(i);
		        	String codigo = item.getChildTextTrim("Codigo");
		        	String marca = item.getChildTextTrim("Marca");
		        	String origen = item.getChildTextTrim("Origen");
		        	String tipo = item.getChildTextTrim("Tipo");
		        	String medida = item.getChildTextTrim("Medida");
		        	String caract = item.getChildTextTrim("Caracteristica");
		        	String precio = item.getChildTextTrim("Precio");
			        String cant= item.getChildTextTrim("Cantidad");
		        	Rodamiento roda = new Rodamiento();
		        	roda.setCaracteristicas(caract);
		        	roda.setMedida(Float.valueOf(medida));
		        	roda.setTipo(tipo);
		        	RodamientoId ri = new RodamientoId();
		        	ri.setCodigo(codigo);
		        	Marca marca2 = new Marca();
		        	MarcaId marcaId = new MarcaId();
		        	marcaId.setDescripcion(marca);
		        	marcaId.setPais(origen);
		        	marca2.setMarcaId(marcaId);
		        	ri.setMarca(marca2);
		        	roda.setRodamientoId(ri);
		        	ItemRodamiento itemRoda = new ItemRodamiento();
		        	//SUPER HARDCODE HERE
		        	itemRoda.setId(20+i);
		        	itemRoda.setPrecio(Float.valueOf(precio));
		        	itemRoda.setRodamiento(roda);
		        	ir.add(itemRoda);
		        }
		        lista.setItemsRodamiento(ir);
		        System.out.println("***********************Lista "+j+"*************************");
		        System.out.println("Descuento: "+lista.getDescuento());
		        System.out.println("Id: "+lista.getId());
		        //System.out.println("Financiacion: "+lista.getFinanciacion().toString());
		        System.out.println("Proveedor: "+lista.getProveedor().getRazonSocial());
		        System.out.println("ItemsRod: Precio= "+lista.getItemsRodamiento().get(0).getPrecio()+" Marca= "+lista.getItemsRodamiento().get(0).getRodamiento().getRodamientoId().getMarca().getMarcaId().getDescripcion());
		        
		    }catch ( IOException io ) {
		        System.out.println( io.getMessage() );
		    }catch ( JDOMException jdomex ) {
		        System.out.println( jdomex.getMessage() );
		    }
		    listaPosta.add(lista);
	    }//del for archivos
	    
	return listaPosta;
	}
	
	public void actualizarListas(String filepath, String codRod, int cantidad){
		//Se crea un SAXBuilder para poder parsear el archivo
	    SAXBuilder builder = new SAXBuilder();
    

    	File xmlFile = new File(filepath );

	    try
	    {
	        //Se crea el documento a traves del archivo
	        Document document = (Document) builder.build( xmlFile );
	 
	        //Se obtiene la raiz 'tables'
	        Element rootNode = document.getRootElement(); 
	        Element Rodamiento = rootNode.getChild("Rodamientos");
			List<Element> items = Rodamiento.getChildren("Item");
			
			// busca el que quiere actualizar
			for(Element eit: items){
				if (eit.getChildTextTrim("Codigo").equals( codRod) )
					eit.getChild("Cantidad").setText(String.valueOf(cantidad));
			}
			
			XMLOutputter xmloutput = new XMLOutputter();
			xmloutput.output(document, new FileWriter(filepath));
			
			
			
	      
	    }catch ( IOException io ) {
	        System.out.println( io.getMessage() );
	    }catch ( Exception jdomex ) {
	        System.out.println( jdomex.getMessage() );
	    }

	}
	
	public void actualizarCantidad(String filepath, String codRod, int cantidad){
	 try {
		 	SAXBuilder builder = new SAXBuilder();
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			//Document doc = (Document) docBuilder.parse(filepath);
			
	        Document doc = (Document) builder.build( filepath );

			Element rootNode = doc.getRootElement();
			Element Rodamiento = rootNode.getChild("Rodamientos");
			List<Element> items = Rodamiento.getChildren("Item"); 
	 
			//loop por cada item
			for(int i=0; i<items.size();i++){
				Element it = items.get(i);
			    if(it.getChild("Codigo").equals(codRod)){
			    	Element cant = it.getChild("Cantidad");
			    	cant.setText(Integer.toString(cantidad));
			    }
			}
	 
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			javax.xml.transform.Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource((Node) doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);
	 
			System.out.println("Done");
	 
		   } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		   } catch (TransformerException tfe) {
			tfe.printStackTrace();
		   } catch (IOException ioe) {
			ioe.printStackTrace();
		   } catch (Exception sae) {
			sae.printStackTrace();
		   }
	}
}