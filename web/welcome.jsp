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
<title>Welcome</title>
</head>
<body style="text-align: center">
	<%
		String errore = request.getParameter("errore");
		String messaggio = request.getParameter("messaggio");
	%>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" >AutoNoleggio</a>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item active"><a class="nav-link" href="RegistrazioneServlet">Registrati
				</a>
				<li class="nav-item active"><a class="nav-link" href="<%=request.getContextPath()%>/recuperaPassword">Recupera password
				</a>
			</ul>
		</div>
	</nav>
	<h1>Benvenuto in Autonoleggio</h1>
	<br>
	<p>Effetua il login</p>
	<form action="Login" method="POST">
	<div class="row justify-content-md-center">
		<div class="form-group col-6">	
			<input type="email" class="form-control" name="mail"
				placeholder="Mail"> <p></p> <input type="password"
				class="form-control" name="pass" placeholder="password"><br>
			<br> <input type="submit" class="btn btn-primary" value="login">
		</div>
		</div>
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
		<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
			integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
			crossorigin="anonymous"></script>
		<script
			src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
			integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
			crossorigin="anonymous"></script>
		<script
			src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
			integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
			crossorigin="anonymous"></script>
</body>
</html>