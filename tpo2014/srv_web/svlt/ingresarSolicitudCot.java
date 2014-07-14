package svlt;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import org.jdom2.Document;         // |
import org.jdom2.Element;          // |\ Librerías
import org.jdom2.input.SAXBuilder; // |

import beans.BeanItemSolicitudCotizacion;
import beans.BeanMarca;
import beans.BeanRodamiento;
import beans.BeanSolicitudCotizacion;
import beans.BeansCliente;
import controlador.RemotoClient;

//@WebServlet(name = "ingSolCot", urlPatterns = {"/jsp/ingSolCot"})
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
		String valor = req.getParameter("idSol");
		
		int idSol = Integer.valueOf( valor );
		
		creaSolicitud("c:\\SolicitudCotizacion" + idSol + ".xml");
			
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/ingresoSolicitudCot.jsp");
		rd.forward(req, resp);						
	}

	private void creaSolicitud(String archivo) {
		BeanSolicitudCotizacion solcot ;
		
		System.out.println( "comienza sol");
		solcot = CrearBeanDDeXML(archivo);
		System.out.println( RemotoClient.getInstancia().guardarCotizacion(solcot) );

		
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
	        Element nrod = rootNode.getChild("Rodamientos");
	        List<Element> rods = nrod.getChildren("Item");
	        for(Element item: rods){
	        	
		        //Nodo Rodamientos/Items
		        //Element item = erod.getChild("Item");
		        
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
