<%@page import="model.Utente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Aggiungi Categoria</title>
</head>
<body  style="text-align: center; background-color: grey; color: white">
<% String messaggio= request.getParameter("messaggio");
String errore= request.getParameter("errore");
%>
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
<h1>Modifica Password <%=((Utente)session.getAttribute("utente")).getNome() %></h1>
	<form action="<%=request.getContextPath()%>/utente/modificaProfiloServlet" method="POST">
	<div class="row justify-content-md-center">
	<div class="form-group col-5">	
		<label for="formControlRange">Nuova Password:</label><br>
		<input type="password" class="form-control-sm" name="newPassword"><br>
		<label for="formControlRange">Conferma Password:</label><br>
        <input type="password" class="form-control-sm" name="confPassword"><br><br>
        <input type="submit" class="btn btn-primary" value="Modifica">
        </div>
        </div>
    </form>
     <%
	if(errore != null ){
		%>
		<p style="color:red"><%= errore %> </p>
		<%
	}
	if(messaggio!=null){
		%>
		<p> <%=messaggio %>
		<%
	}
	%>
</body>

</body>
</html>