package svlt;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlador.RemotoClient;
import beans.BeanRemito;


public class BuscarRemitoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/jsp/mostrarRemitos.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		List<BeanRemito> remitos = (List<BeanRemito>)RemotoClient.getInstancia().buscarRemitos();
		req.setAttribute("Remitos", remitos);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/jsp/mostrarRemitos.jsp");
		rd.forward(req, resp);

	}
	
}
