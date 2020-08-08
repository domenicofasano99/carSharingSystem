package Servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Utente;
import Database.dao.factory.DAOFactory;

/**
 * Servlet implementation class RegistrazioneServlet
 */
@WebServlet("/RegistrazioneServlet")
public class RegistrazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrazioneServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("registrazione.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String mail = request.getParameter("mail");
		LocalDate dataNascita = null;
		String password = "";
		String errore = "";
		String regexMail = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		String regexPass = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}";// (?=.*[@#$%^&+=])(?=\\\\S+$) special char and

		if (request.getParameter("password").equals(request.getParameter("confermaPass"))) {
			password = request.getParameter("password");
		} else {
			response.sendRedirect("registrazione.jsp?errore=Password non corrispondenti");
			return;
		}
		try {
			dataNascita = LocalDate.parse(request.getParameter("dataNascita"));
		} catch (DateTimeParseException e) {
			response.sendRedirect("registrazione.jsp?errore=data inserita erroneamente");
			return;
		}
		if (dataNascita != null) {
			long p2 = ChronoUnit.DAYS.between(dataNascita, LocalDate.now());
			if (password.matches(regexPass) && mail.matches(regexMail) && p2 > 6480) {
				String inserito = "";
				try {
					DAOFactory.getDAOFactory().getUtenteDAO()
							.aggiungiUtente(new Utente(nome, cognome, mail, dataNascita, password));
					inserito = "messaggio=Utente registrato correttamente";
				} catch (Exception e) {
					inserito = "errore=Utente gia' registrato";
				}
				response.sendRedirect("registrazione.jsp?" + inserito);
		}else if (!password.matches(regexPass))
			response.sendRedirect("registrazione.jsp?errore=La password deve contenere almeno una MAIUSCOLA, una minuscola e un numero");
		else if (p2 < 6480)
			response.sendRedirect("registrazione.jsp?errore=Bisogna aver raggiunto la maggiore eta' per registrarsi");
		else
			response.sendRedirect("registrazione.jsp?errore=Indirizzo e-Mail non esistente");
	}


	}

}
