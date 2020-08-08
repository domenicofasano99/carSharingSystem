package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.bind.v2.model.core.ID;

import Database.dao.factory.DAOFactory;
import model.Utente;

/**
 * Servlet implementation class ModificaProfiloServlet
 */
@WebServlet(name = "ModificaProfileServlet", urlPatterns = { "/ModificaProfileServlet" })
public class ModificaProfiloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificaProfiloServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/utente/modificaProfilo");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String regexPass = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}";
		if (request.getParameter("newPassword").equals(request.getParameter("confPassword"))) {
			if (request.getParameter("newPassword").matches(regexPass))
				try {
					DAOFactory.getDAOFactory().getUtenteDAO().modifyPassword(request.getParameter("newPassword"),
							(Utente) request.getSession().getAttribute("utente"));
					response.sendRedirect(request.getContextPath() + "/utente/modificaProfilo?messaggio=Password cambiata correttamente");
				} catch (Exception e) {
					response.sendRedirect(request.getContextPath() + "/utente/modificaProfilo?errore=Qualcosa e' andato storto");
				}
			else
				response.sendRedirect(request.getContextPath()
						+ "/utente/modificaProfilo?errore=La password deve contenere almeno 8 caratteri di cui almeno una Maiuscola e un numero");
		} else 
			response.sendRedirect(
					request.getContextPath() + "/utente/modificaProfilo?errore=Le password inserite sono diverse");

	}

}
