<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.Noleggio"%>
<%@page import="model.Utente"%>
<%@page import="java.sql.Date"%>
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
<%
	Utente utente = (Utente) session.getAttribute("utente");

	String messaggio = request.getParameter("messaggio");
	String errore = request.getParameter("errore");
%>
<title>Preventivo Auto</title>
</head>
<body style="text-align: center">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" >AutoNoleggio</a>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item active"><a   class="nav-link" href="<%=request.getContextPath()%>/utente/home">Home </a>
				</li>
			</ul>
		</div>
		<ul class="navbar-nav pull-right">
					
				<li class="nav-item active "><a style="color:red" class="nav-link"
					href="<%=request.getContextPath()%>/Logout">Logout </a></li>
			</ul>
	</nav>
	<%
		Noleggio noleggio = (Noleggio) session.getAttribute("noleggio");
	%>
	<h1>
		Salve
		<%=utente.getNome()%>
		<%=utente.getCognome()%>,
	</h1>
	<br>
	<h4>
		il costo per affittare l'auto
		<%=noleggio.getVeicolo().getMarca()%>
		<%=noleggio.getVeicolo().getModello()%>
		dal
		<%= new SimpleDateFormat("dd/MM/yyyy").format(noleggio.getInizioNoleggio())%>
		al
		<%=new SimpleDateFormat("dd/MM/yyyy").format(noleggio.getFineNoleggio())%>
		e' di <b style="color: blue"><%=noleggio.getImportoDovuto()%>&euro;</b>
	</h4>
	<form action="<%=request.getContextPath()%>/utente/affittaAuto" method="POST">
		<input type="submit" class="btn btn-primary" value="Affitta">
	</form>
	<%
		if (errore != null) {
	%>
	<p style="color: red"><%=errore%>
	</p>
	<%
		}
		if (messaggio != null) {
	%>
	<p>
		<%=messaggio%>
		<%
			}
		%>
	
</body>
</html>