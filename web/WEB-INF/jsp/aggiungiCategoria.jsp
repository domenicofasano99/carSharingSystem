<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="text-align: center; background-color: grey; color: white">
	<link rel="stylesheet"
		href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
		integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
		crossorigin="anonymous">
	<%
		String messaggio = request.getParameter("messaggio");
		String errore = request.getParameter("errore");
	%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" >AutoNoleggio</a>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item active"><a class="nav-link" href="<%=request.getContextPath()%>/admin/home">Home
				</a></li>
			</ul>
		</div>
		<ul class="navbar-nav pull-right">
					
				<li class="nav-item active "><a style="color:red"  class="nav-link"
					href="<%=request.getContextPath()%>/Logout">Logout </a></li>
			</ul>
	</nav>
	<h1>Aggiungi categoria</h1>
	<form action="<%=request.getContextPath()%>/admin/addCat" method="POST">
		<div class="row justify-content-md-center">
			<div class="form-group col-5">
				<label for="formControlRange">Nome:</label><br> <input
					type="text" class="form-control-sm" name="nome"
					placeholder="es. Berlina"><br> <label
					for="formControlRange">Prezzo giornaliero:</label><br> <input
					type="number" class="form-control-sm" step="0.01" name="prezzoG"
					placeholder="20.00"><br> <label for="formControlRange">Prezzo
					settimanale:</label><br> <input type="number" class="form-control-sm"
					step="0.01" name="prezzoS" placeholder="80.00"><br> <label
					for="formControlRange">Prezzo mensile: </label><br> <input
					type="number" class="form-control-sm" step="0.01" name="prezzoM"
					placeholder="200.00"><br> <label
					for="formControlRange"> Descrizione:</label><br>
				<textarea name="descrizione" cols="22" rows="5"></textarea>
				<br> <input type="submit" class="btn btn-primary"
					value="Aggiungi">
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
	
</body>
</html>