<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="beans.BeanOP"%>
<%@ page import="java.util.List;"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ordenes de pedido</title>
</head>
<body>
	
	<table width="40%" border="2" bordercolor="#00000"
		   style="border-collapse: collapse;">
		<tr>
			<td class="titulo">Mostrar ordenes de pedido pendientes </td>
		</tr>
	</table>
	</br>

	<form action="BuscarOP.do" method="post">
		<input type="submit" value="Buscar OPP" />
	</form>
	<%if(request.getAttribute("OP")!= null){
		List<BeanOP> bop = (List<BeanOP>)request.getAttribute("OP");%>
		<tbody>
		<%
			
			for (BeanOP beanop : bop){
		%>
		<tr class="datosTabla">
			<td><%=beanop.getId()%></td>
			<td><%=beanop.getClienteID()%></td>
			<td><%=beanop.getRazonSocial()%></td>
			<td><%=beanop.getFecha()%></td>
			<td><%=beanop.getEstado()%></td>
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