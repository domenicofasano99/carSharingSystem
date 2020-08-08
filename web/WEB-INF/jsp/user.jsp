
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.Utente"%>
<%@page import="model.Noleggio"%>
<%@page import="java.time.Instant"%>
<%@page import="java.util.Date"%>
<%@page import="model.Veicolo"%>
<%@page import="java.util.List"%>
<%@page import="model.Categoria"%>
<%@page import="java.util.ArrayList"%>
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
%>
<title>Benvenuto <%=utente.getNome()%> <%=utente.getCognome()%></title>
</head>
<body style="text-align: center; background-color: grey; color: white">
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand">AutoNoleggio</a>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item active"><a class="nav-link"
					href="<%=request.getContextPath()%>/utente/noleggiAttivi">Noleggi
						attivi </a></li>
				<li class="nav-item active"><a class="nav-link"
					href="<%=request.getContextPath()%>/utente/viewParcoAuto">Parco
						auto </a></li>

			</ul>

		</div>
		<ul class="navbar-nav pull-right">
			<li class="nav-item active"><a class="nav-link"
				href="<%=request.getContextPath()%>/utente/modificaProfilo">Modifica
					profilo </a></li>
			<li class="nav-item active"><a style="color: red"
				class="nav-link" href="<%=request.getContextPath()%>/Logout">Logout
			</a></li>
		</ul>
	</nav>
	<h1>
		Benvenuto
		<%=utente.getNome()%>
		<%=utente.getCognome()%></h1>



	<%
		List<Veicolo> veicoli = (List<Veicolo>) session.getAttribute("veicoli");
		List<Noleggio> noleggi = (List<Noleggio>) session.getAttribute("noleggi");
		//System.out.println("test test test" + veicoli);
		String messaggio = request.getParameter("messaggio");
		String errore = request.getParameter("errore");
		if (noleggi != null && noleggi.size()>0) {
	%>
	<h4>Ecco i tuoi noleggi effettuati</h4>
	<div class="table-responsive-xl">
		<table align="center"
			class="table table-striped table-dark table-sm col-9">
			<tr>
				<th>targa</th>
				<th>inizio Noleggio</th>
				<th>fine Noleggio</th>
				<th>importo dovuto</th>
				<th></th>
			</tr>
			<%
				for (Noleggio c : noleggi) {
			%>
			<tr>
				<td><%=c.getVeicolo().getTarga()%></td>
				<td><%=new SimpleDateFormat("dd/MM/yyyy")
							.format(new java.sql.Date(c.getInizioNoleggio().getTime()))%></td>
				<td><%=new SimpleDateFormat("dd/MM/yyyy")
							.format(new java.sql.Date(c.getFineNoleggio().getTime()))%></td>
				<td><%=c.getImportoDovuto()%></td>
				<td><form
						action="<%=request.getContextPath()%>/utente/eliminaNoleggio"
						method="POST">
						<input type="hidden" name="idNoleggio"
							value="<%=c.getIdNoleggio()%>">
							<%if(c.getInizioNoleggio().after(Date.from(Instant.now()))){ %> <input type="submit"
							class="btn-sm btn-outline-dark btn-warning"
							value="Elimina Noleggio"><%} %>
					</form></td>

			</tr>
			<%
				}
			%>
		</table>
	</div>
	<%
		}
	%>


	<form action="<%=request.getContextPath()%>/utente/cercaAuto"
		method="POST">
		<div class="row justify-content-md-center">
			<div class="form-group col-6">
				<label for="formControlRange">Data inizio affitto:</label><br>
				<input type="date" class="form-control form-control-sm"
					name="dataInizio"><br> <label for="formControlRange">Data
					fine affitto:</label><br> <input type="date"
					class="form-control form-control-sm" name="dataFine"><br>
				<input type="submit" class="btn btn-primary" value="Cerca Auto">
			</div>
		</div>
	</form>

	<%
		if (veicoli != null) {
			if (session.getAttribute("dataInizio") != null &&  session.getAttribute("datafine")!=null) {
				Date dataInizio = (Date) session.getAttribute("dataInizio");
				Date dataFine = (Date) session.getAttribute("datafine");
	%>
	<h2>
		Auto disponibili dal
		<%=new SimpleDateFormat("dd/MM/yyyy").format(new java.sql.Date(dataInizio.getTime()))%>
		al
		<%=new SimpleDateFormat("dd/MM/yyyy").format(new java.sql.Date(dataFine.getTime()))%></h2>
	<div class="table-responsive-xl">
		<table align="center"
			class="table table-striped table-dark table-sm col-9">
			<tr>

				<th>Targa</th>
				<th>Marca</th>
				<th>Modello</th>
				<th>Numero Posti</th>
				<th>Colore</th>
				<th>Affitta</th>
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

				<td><form
						action="<%=request.getContextPath()%>/utente/calcoloAffitto"
						method="POST">
						<%
							session.setAttribute("dataInizio", dataInizio);
										session.setAttribute("dataFine", dataFine);
						%>
						<input type="hidden" name="auto" value="<%=c.getTarga()%>">
						<input type="submit" class="btn-sm btn-outline-dark btn-warning"
							value="Preventivo affitto">
					</form></td>

			</tr>
			<%
				}
					}
				}
			%>
		</table>
	</div>

	<%
		if (errore != null) {
	%>
	<script>
	var errore="<%=errore%>";
	alert(errore);	
</script>
	<%
		}
		if (messaggio != null) {
	%>
	<script>
	var messaggio="<%=messaggio%>";
	alert(messaggio);	
</script>
	<%
		}
	%>

</body>
</html>