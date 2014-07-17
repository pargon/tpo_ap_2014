package svlt;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlador.RemotoClient;
import beans.BeanOP;





public class BuscarOPServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 275634223576510179L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		ClienteBean cli = new ClienteBean();
		cli.setContacto("Contacto pepe");
		cli.setCuit("1234");
		cli.setRazonSocial("santos srl");
		
		req.setAttribute("OP", cli);
		*/
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/jsp/mostrarOrdenPedido.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		List<BeanOP> listaOP = (List<BeanOP>)RemotoClient.getInstancia().buscarOP();
		req.setAttribute("OP", listaOP);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/jsp/mostrarOrdenPedido.jsp");
		rd.forward(req, resp);

	}
	
	/*
	 <%
		ClienteBean cli = (ClienteBean) request.getAttribute("OP");
		out.println(cli.getRazonSocial());
	%>
	*/

}
