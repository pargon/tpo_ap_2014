<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="beans.BeanRemito"%>
<%@ page import="java.util.List;"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Remitos</title>
</head>
<body>
	
	<table width="40%" border="2" bordercolor="#00000"
		   style="border-collapse: collapse;">
		<tr>
			<td class="titulo">Mostrar remitos pendientes </td>
		</tr>
	</table>
	</br>

	<form action="BuscarRemitos.do" method="post">
		<input type="submit" value="Buscar Remitos" />
	</form>
	<table width="40%" border="2" bordercolor="#00000"
		   style="border-collapse: collapse;">
	
	<%if(request.getAttribute("Remitos")!= null){
		List<BeanRemito> brs = (List<BeanRemito>)request.getAttribute("Remitos");%>
		<tbody>
		<%
			
			for (BeanRemito br : brs){
		%>
		<tr class="datosTabla">
			<td><%=br.getId()%></td>
			<td><%=br.getClienteID()%></td>
			<td><%=br.getRazonSocial()%></td>
			<td><%=br.getFecha()%></td>
			<td><%=br.getEstado()%></td>
		</tr>
		<%
			}//Cierra el for
		%>
	</tbody>
	</table>
	<%
	}
		
	%>
</body>
</html>