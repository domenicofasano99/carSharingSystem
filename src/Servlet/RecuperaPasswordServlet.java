package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import Database.dao.factory.DAOFactory;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import model.Utente;
import utilities.SendMail;

/**
 * Servlet implementation class RecuperaPasswordServlet
 */
public class RecuperaPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RecuperaPasswordServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final String username = getServletContext().getInitParameter("email_username");
		final String password = getServletContext().getInitParameter("email_password");
		String mail = request.getParameter("mail");
		try {
			Utente utente = DAOFactory.getDAOFactory().getUtenteDAO().getUtenteByMail(mail);
			String generatedPassword = SendMail.generatePassword();
			SendMail.sendMail(generatedPassword,username, password, utente);
			DAOFactory.getDAOFactory().getUtenteDAO().modifyPassword(generatedPassword, utente);
			response.sendRedirect(
					request.getContextPath() + "/welcome.jsp?messaggio=Mail con la password temporanea inviata");
			return;
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/recuperaPassword?errore=mail non registrata");
			return;
		}
	}
}
