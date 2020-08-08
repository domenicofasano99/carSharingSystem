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
<title>Registrazione</title>
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
				<li class="nav-item active"><a class="nav-link" href="welcome.jsp">Home
				</a>
			</ul>
		</div>
	</nav>
	<h3>Inserisci i tuoi dati per registrarti</h3>
	<form action="RegistrazioneServlet" method="POST">
		<div class="row justify-content-md-center">
			<div class="form-group col-5">
				<label for="formControlRange">Nome:</label> <input type="text"
					class="form-control form-control-sm" name="nome"
					placeholder="inserire nome"> <label for="formControlRange">Cognome:
				</label><input type="text" class="form-control form-control-sm"
					name="cognome" placeholder="inserire cognome"> <label
					for="formControlRange">Data di nascita:</label> <input type="date"
					class="form-control form-control-sm" name="dataNascita"
					placeholder="Data di nascita"> <label
					for="formControlRange">Email:</label> <input type="email"
					class="form-control form-control-sm" name="mail"
					placeholder="Inserire email"> <label for="formControlRange">Password:
				</label><input type="password" class="form-control form-control-sm"
					name="password" placeholder="inserire password"> <label
					for="formControlRange">Conferma password:</label> <input
					type="password" class="form-control form-control-sm"
					name="confermaPass" placeholder="confermare password"><br>
				<input type="submit" class="btn btn-primary" value="Sign In">
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