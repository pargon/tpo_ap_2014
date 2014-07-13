package svlt;


import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.jdom2.Document;         // |
import org.jdom2.Element;          // |\ Librerías
import org.jdom2.JDOMException;    // |/ JDOM
import org.jdom2.input.SAXBuilder; // |
import org.jdom2.output.XMLOutputter;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import beans.BeanItemSolicitudCotizacion;
import beans.BeanMarca;
import beans.BeanRodamiento;
import beans.BeanSolicitudCotizacion;
import beans.BeansCliente;
import controlador.RemotoClient;

@WebServlet(name = "ingSolCot", urlPatterns = {"/jsp/ingSolCot"})
@MultipartConfig(location = "/tmp")
public class ingresarSolicitudCot extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ingresarSolicitudCot(){
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/jsp/ingresoSolicitudCot.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
       /*
        for (Part part : req.getParts()) {
        
    	   
            String filename = "";
            for (String s : part.getHeader("content-disposition").split(";")) {
                if (s.trim().startsWith("filename")) {
                    filename = s.split("=")[1].replaceAll("\"", "");
                }
            }
            part.write(filename);
        }
        
        
        -------------------- SUBIR Y DESCOMPONER EN BEAN SOLICITUD ----------------------
        
        */

		BeanSolicitudCotizacion solcot ;
//		solcot = CrearBeanDDeXML("c:\\SolicitudCotizacion1.xml");
//		RemotoClient.getInstancia().guardarCotizacion(solcot);
		
		solcot = CrearBeanDDeXML("c:\\SolicitudCotizacion2.xml");
		RemotoClient.getInstancia().guardarCotizacion(solcot);
		
		solcot = CrearBeanDDeXML("c:\\SolicitudCotizacion3.xml");
		RemotoClient.getInstancia().guardarCotizacion(solcot);
		
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/ingresoSolicitudCot.jsp");
		rd.forward(req, resp);						
	}

	private BeanSolicitudCotizacion CrearBeanDDeXML(String archivo) {
		
		BeanSolicitudCotizacion solcot = new BeanSolicitudCotizacion();		
        BeansCliente bc = new BeansCliente();
        List<BeanItemSolicitudCotizacion> listItemSolCot = new ArrayList<BeanItemSolicitudCotizacion>();        
        
		//Se crea un SAXBuilder para poder parsear el archivo
	    SAXBuilder builder = new SAXBuilder();
		try {
	    	    	
			File xmlFile = new File(archivo);
			
	        //Se crea el documento a traves del archivo
	        Document document = (Document) builder.build( xmlFile );
		 
	        //Se obtiene la raiz 'tables'
	        Element rootNode = document.getRootElement();
	        
	        int numSol = Integer.valueOf(rootNode.getAttributeValue("numero"));
	        String fecSolchr = rootNode.getAttributeValue("fecha");
	
	        //Nodo Cliente
	        Element cliente = rootNode.getChild("Cliente");
	        bc.setCuit(cliente.getChildTextTrim("Cuil"));
	        bc.setRazonSocial(cliente.getChildTextTrim("RazonSocial"));
	
	        
	        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	        Date fecSol = formatter.parse(fecSolchr);
	        
	        solcot.setBeansCliente(bc);
	        solcot.setFecha(fecSol);
	        solcot.setId(numSol);
	        
	        //Nodo Rodamientos
	        List<Element> rods = rootNode.getChildren("Rodamientos");
	        for(Element erod: rods){
	        	
		        //Nodo Rodamientos/Items
		        Element item = erod.getChild("Item");
		        
		        BeanItemSolicitudCotizacion itemSolCot = new BeanItemSolicitudCotizacion();
		        BeanRodamiento rod = new BeanRodamiento();
		        BeanMarca mar = new BeanMarca();
		        
		        mar.setDescripcion(item.getChildTextTrim("Marca"));
		        mar.setPais(item.getChildTextTrim("Origen"));
		        rod.setBeanMarca(mar);
		        rod.setCodigo(item.getChildTextTrim("Codigo"));
		        rod.setTipo(item.getChildTextTrim("Sufijo"));
		        
		        itemSolCot.setBeanRodamiento(rod);
		        itemSolCot.setCantidad(Integer.valueOf( item.getChildTextTrim("Cantidad")));
		        
				listItemSolCot.add(itemSolCot);
		        
	        }
	        solcot.setBeanItemsSolicitudCotizacion(listItemSolCot);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return solcot;
		
	}		
}
