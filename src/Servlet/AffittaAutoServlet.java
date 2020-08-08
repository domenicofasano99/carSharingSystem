package Servlet;

import java.io.IOException;
import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import Database.dao.factory.DAOFactory;
import model.Noleggio;
import model.Utente;
import utilities.CookieUtilities;

/**
 * Servlet implementation class AffittaAutoServlet
 */
@WebServlet("/AffittaAutoServlet")
public class AffittaAutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AffittaAutoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Noleggio noleggio = (Noleggio) request.getSession().getAttribute("noleggio");
		response.sendRedirect(request.getContextPath() + "/utente/home");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Noleggio noleggio = (Noleggio) request.getSession().getAttribute("noleggio");
		String targa = request.getParameter("auto");
		try {
			DAOFactory.getDAOFactory().getNoleggioDAO().aggiungiNoleggio(noleggio);
			request.getSession().setAttribute("veicoli",
					DAOFactory.getDAOFactory().getVeicoloDAO().getAutoNonNoleggiate((java.util.Date)request.getSession().getAttribute("dataInizio"), (java.util.Date)request.getSession().getAttribute("datafine")));
			request.setAttribute("categorie", DAOFactory.getDAOFactory().getCategoriaDAO().getCategorie());
			request.getSession().setAttribute("noleggi", DAOFactory.getDAOFactory().getNoleggioDAO()
					.getNoleggiByUtente(DAOFactory.getDAOFactory().getUtenteDAO().getUtenteByMail(((Utente)request.getSession().getAttribute("utente")).getEmail())));
			
			response.sendRedirect(request.getContextPath() + "/utente/home?messaggio=Noleggio andato a buon fine");

		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/utente/home?errore=Qualcosa e' andato storto");
		}
	}

}
