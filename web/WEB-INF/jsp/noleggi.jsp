<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="model.Noleggio"%>
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
<title>Noleggi</title>
</head>
<body style="text-align: center; background-color:grey; color:white">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" >AutoNoleggio</a>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item active"><a class="nav-link" href="<%=request.getContextPath()%>/admin/home">Home </a>
				</li>
			</ul>
		</div>
		<ul class="navbar-nav pull-right">
					
				<li class="nav-item active "><a style="color:red"  class="nav-link"
					href="<%=request.getContextPath()%>/Logout">Logout </a></li>
			</ul>
	</nav>
<h1>Noleggi</h1>
<div class="table-responsive-xl">
		<table align="center"
			class="table table-striped table-dark table-sm col-9">
			<tr>
				<%
				List<Noleggio> noleggi = (List<Noleggio>) session.getAttribute("noleggi");
				if (noleggi != null) {
			%>
				<th>idNoleggio</th>
				<th>Saldo</th>
				<th>Data Inizio</th>
				<th>Data Fine</th>
				<th>Email</th>
				<th>Categoria</th>
				<th>Marca</th>
				<th>Modello</th>
				<th>Targa</th>
				<th>Attivo</th>
				
			</tr>
			<%
			for (Noleggio n : noleggi){
		%>
			<tr>
				<td><%=n.getIdNoleggio()%></td>
				<td><%=n.getImportoDovuto()%></td>
				<td><%=new SimpleDateFormat("dd/MM/yyyy").format(n.getInizioNoleggio())%></td>
				<td><%=new SimpleDateFormat("dd/MM/yyyy").format(n.getFineNoleggio())%></td>
				<td><%=n.getUtente().getEmail()%></td>
				<td><%=n.getVeicolo().getCategoria().getNomeCategoria()%></td>
				<td><%=n.getVeicolo().getMarca()%></td>
				<td><%=n.getVeicolo().getModello()%></td>
				<td><%=n.getVeicolo().getTarga()%></td>
				<td><%=n.getAttivo()%></td>

			</tr>
			<%
		}
	}
	%>
		</table>
	</div>
</body>
</html>