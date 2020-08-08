
<%@page import="java.util.List"%>
<%@page import="model.Veicolo"%>
<%@page import="model.Categoria"%>
<%@page import="java.util.HashMap"%>
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
<title>Administrator Page</title>
</head>
<body style="text-align: center; background-color: grey; color: white">
	<%
	List<Categoria> categorie = (List) session.getAttribute("categorie");
	System.out.println(categorie);
	List<Veicolo> veicoli = (List) session.getAttribute("veicoli");
	%>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" >AutoNoleggio</a>
		 <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
		 <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="<%=request.getContextPath()%>/admin/aggiungiCategoria">Aggiungi Categoria
				</a></li>
				<li class="nav-item active"><a class="nav-link" href="<%=request.getContextPath()%>/admin/ViewAllNoleggi">Vedi Noleggi
				</a></li>
			</ul>
		</div>
		<ul class="navbar-nav pull-right">
					
				<li class="nav-item active "><a style="color:red"  class="nav-link"
					href="<%=request.getContextPath()%>/Logout">Logout </a></li>
			</ul>
	</nav>
	<h1>Benvenuto Admin</h1>
	<%
		String messaggio = request.getParameter("messaggio");
		String errore = request.getParameter("errore");
		//String categoriaView = request.getParameter("categoriaView");
		Categoria categoria = (Categoria) session.getAttribute("categoria");
	%>

	<div class="table-responsive-xl">
		<table align="center"
			class="table table-striped table-dark table-sm col-9">
			<tr>
				<%
					if (categorie != null) {
				%>
				<th>ID</th>
				<th>Nome</th>
				<th>Prezzo giornaliero</th>
				<th>Prezzo settimanale</th>
				<th>Prezzo mensile</th>
				<th>Aggiungere auto a categoria</th>
				<th>Vedere auto nella categoria</th>
			</tr>
			<%
				for (Categoria c : categorie) {
			%>
			<tr>

				<td><%=c.getIdCategoria()%></td>
				<td><%=c.getNomeCategoria()%></td>
				<td><%=c.getTariffaGiornaliera()%></td>
				<td><%=c.getTariffaSettimanale()%></td>
				<td><%=c.getTariffaMensile()%></td>
				<td><form action="<%=request.getContextPath()%>/admin/goToAddCarServlet" method="POST">
						<input type="hidden" name="categoria"
							value="<%=c.getNomeCategoria()%>"> <input type="submit"
							class="btn-sm btn-outline-dark btn-warning" value="aggiungi auto">
					</form></td>
				<td><form action="<%=request.getContextPath()%>/admin/ViewCar" method="POST">
						<input type="hidden" name="categoria"
							value="<%=c.getNomeCategoria()%>"> <input type="hidden"
							name="idcategoria" value="<%=c.getIdCategoria()%>  "> <input
							type="submit" class="btn-sm btn-outline-dark btn-warning"
							value="Vedi auto">
					</form></td>

			</tr>
			<%
				//AGGIUNGERE LA VISTA DELLE AUTOOOOOO
					}
				}
			%>

		</table>
	</div>
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
		<%=messaggio%></p>
	<%
		}
		if (veicoli != null) {
		if (categoria != null) {
			
	%>
	<h2>
		Auto in
		<%=categoria.getNomeCategoria()%></h2>
	<div class="table-responsive-xl">
		<table align="center"
			class="table table-striped table-dark table-sm col-9">
			<tr>
				<th>Targa</th>
				<th>Marca</th>
				<th>Modello</th>
				<th>Numero Posti</th>
				<th>Colore</th>
				<th></th>
			</tr>
			<%
				for (Veicolo c : veicoli) {
			%>
			<tr>
				<td><%=c.getTarga()%></td>
				<td><%=c.getMarca()%></td>
				<td><%=c.getModello()%></td>
				<td><%=c.getNPosti()%></td>
				<td><%=c.getColore()%></td>
				<td><form action="<%=request.getContextPath()%>/admin/deleteCar" method="POST"><input type="hidden"
							name="veicolo" value="<%=c.getTarga()%>"> <input
							type="submit" class="btn-sm btn-outline-dark btn-warning"
							value="Elimina auto"></form></td>
			</tr>
			<%
				}
					}
				}
			%>
		</table>
	</div>
</body>
</html>