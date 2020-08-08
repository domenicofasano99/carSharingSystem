<%@page import="model.Categoria"%>
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
<title>Aggiungi Categoria</title>
</head>
<body  style="text-align: center; background-color: grey; color: white">
<% String messaggio= request.getParameter("messaggio");
String errore= request.getParameter("errore");
Categoria categoria= (Categoria)session.getAttribute("categoria");
%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" >AutoNoleggio</a>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item active"><a   class="nav-link" href="<%=request.getContextPath()%>/admin/home">Home
				</a></li>
			</ul>
		</div>
		<ul class="navbar-nav pull-right">
					
				<li class="nav-item active "><a style="color:red" class="nav-link"
					href="<%=request.getContextPath()%>/Logout">Logout </a></li>
			</ul>
	</nav>
<h1>Aggiungi Auto nella categoria <%=categoria.getNomeCategoria()%></h1>
	<form action="<%=request.getContextPath()%>/admin/addCarServlet" method="POST">
	<div class="row justify-content-md-center">
	<div class="form-group col-5">	
		<label for="formControlRange">Targa:</label><br>
		<input type="text" class="form-control-sm" name="targa" placeholder="AB000CD"><br>
		<label for="formControlRange">Marca:</label><br>
        <input type="text" class="form-control-sm" name="marca" placeholder="Fiat"><br>
        <label for="formControlRange">Modello:</label><br>
        <input type="text"class="form-control-sm" name="modello" placeholder="Panda"><br>
        <label for="formControlRange">Numero di posti:</label><br>
        <input type="number" class="from-control-sm" step="1" name="nPosti" placeholder="4"><br>
        <label for="formControlRange">Colore:</label><br>
        <input type="text" class="form-control-sm" step="1" name="colore" placeholder="nero"><br>
        <input type="submit" class="btn btn-primary" value="Aggiungi">
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
</html>