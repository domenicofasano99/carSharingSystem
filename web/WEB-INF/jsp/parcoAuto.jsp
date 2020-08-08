<%@page import="java.util.List"%>
<%@page import="model.Veicolo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Parco auto</title>
</head>
<body style="text-align: center; background-color:grey; color:white">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" >AutoNoleggio</a>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item active"><a class="nav-link" href="<%=request.getContextPath()%>/utente/home">Home </a>
				</li>
			</ul>
		</div>
		<ul class="navbar-nav pull-right">
					
				<li class="nav-item active "><a style="color:red"  class="nav-link"
					href="<%=request.getContextPath()%>/Logout">Logout </a></li>
			</ul>
	</nav>
<h1>Parco Auto</h1>
<div class="table-responsive-xl">
		<table align="center"
			class="table table-striped table-dark table-sm col-9">
			<tr>
				<%
				List<Veicolo> veicoli = (List<Veicolo>) session.getAttribute("veicoli");
				if (veicoli != null) {
			%>
				<th>Targa</th>
				<th>Marca</th>
				<th>Modello</th>
				<th>Numero Posti</th>
				<th>Colore</th>
				<th>Categoria</th>
				<th>Costo Giornaliero</th>
				<th>Costo Settimanale</th>
				<th>Costo Mensile</th>
				
			</tr>
			<%
			for (Veicolo c : veicoli){
		%>
			<tr>
				<td><%=c.getTarga()%></td>
				<td><%=c.getMarca()%></td>
				<td><%=c.getModello()%></td>
				<td><%=c.getNPosti()%></td>
				<td><%=c.getColore()%></td>
				<td><%=c.getCategoria().getNomeCategoria()%></td>
				<td><%=c.getCategoria().getTariffaGiornaliera()%></td>
				<td><%=c.getCategoria().getTariffaSettimanale()%></td>
				<td><%=c.getCategoria().getTariffaMensile()%></td>

			</tr>
			<%
		}
	}
	%>
		</table>
	</div>
</body>
</html>