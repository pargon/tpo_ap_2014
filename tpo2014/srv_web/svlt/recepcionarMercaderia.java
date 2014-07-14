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


@WebServlet(name = "recMerc", urlPatterns = {"/jsp/recMerc"})
public class recepcionarMercaderia extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public recepcionarMercaderia() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String valor = req.getParameter("idOC");
		
		int idOC = Integer.valueOf( valor );
		
		String rdo = RemotoClient.getInstancia().recepcionarMercaderia(idOC);
		
	    resp.setContentType("text/html");
	    PrintWriter out = resp.getWriter();
	    out.write(rdo);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}
	
	

}
