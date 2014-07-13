package svlt;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

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

		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/ingresoSolicitudCot.jsp");
		rd.forward(req, resp);
		
		
		
	}		
}
