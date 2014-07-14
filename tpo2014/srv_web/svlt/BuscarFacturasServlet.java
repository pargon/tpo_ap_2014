package svlt;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlador.RemotoClient;
import beans.BeanFacturaSmall;
import beans.BeanOP;

public class BuscarFacturasServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/jsp/mostrarFacturas.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		List<BeanFacturaSmall> bfs = (List<BeanFacturaSmall>)RemotoClient.getInstancia().buscarFacturas();
		req.setAttribute("Facturas", bfs);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/jsp/mostrarFacturas.jsp");
		rd.forward(req, resp);

	}
	
}
