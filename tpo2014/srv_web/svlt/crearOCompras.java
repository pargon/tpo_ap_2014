package svlt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlador.RemotoClient;


//@WebServlet(name = "crearOC", urlPatterns = {"/jsp/crearOC"})
public class crearOCompras extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public crearOCompras() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String rdo = RemotoClient.getInstancia().crearOCompras();
		
	    resp.setContentType("text/html");
	    PrintWriter out = resp.getWriter();
	    out.write(rdo);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}
	
	

}
