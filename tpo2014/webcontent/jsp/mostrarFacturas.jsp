<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="beans.BeanFacturaSmall"%>
<%@ page import="java.util.List;"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Facturas</title>
</head>
<body>
	
	<table width="40%" border="2" bordercolor="#00000"
		   style="border-collapse: collapse;">
		<tr>
			<td class="titulo">Mostrar Facturas </td>
		</tr>
	</table>
	</br>

	<form action="BuscarFacturas.do" method="post">
		<input type="submit" value="Buscar Facturas" />
	</form>
	<%if(request.getAttribute("Facturas")!= null){
		List<BeanFacturaSmall> bfs = (List<BeanFacturaSmall>)request.getAttribute("Facturas");%>
		<tbody>
		<%
			
			for (BeanFacturaSmall bf : bfs){
		%>
		<tr class="datosTabla">
			<td><%=bf.getId()%></td>
			<td><%=bf.getClienteID()%></td>
			<td><%=bf.getRazonSocial()%></td>
			<td><%=bf.getFecha()%></td>
			<td><%=bf.getEstado()%></td>
		</tr>
		<%
			}//Cierra el for
		%>
	</tbody>
	<%
	}
		
	%>
</body>
</html>