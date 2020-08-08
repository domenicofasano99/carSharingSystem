package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.dao.factory.DAOFactory;
import model.Utente;
import utilities.CookieUtilities;

/**
 * Servlet implementation class EliminaNoleggioServlet
 */
@WebServlet("/EliminaNoleggioServlet")
public class EliminaNoleggioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EliminaNoleggioServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.getSession().setAttribute("noleggi", DAOFactory.getDAOFactory().getNoleggioDAO()
					.getNoleggiByUtente(DAOFactory.getDAOFactory().getUtenteDAO().getUtenteByMail(((Utente)request.getSession().getAttribute("utente")).getEmail())));
			response.sendRedirect(request.getContextPath()+"/utente/home");
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath()+"/utente/home?errore=Qualcosa e' andato storto");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idNoleggio = Integer.parseInt(request.getParameter("idNoleggio"));
		try {
		System.out.println(idNoleggio);
		DAOFactory.getDAOFactory().getNoleggioDAO().eliminaNoleggio(idNoleggio);
		request.getSession().setAttribute("noleggi", DAOFactory.getDAOFactory().getNoleggioDAO()
				.getNoleggiByUtente(DAOFactory.getDAOFactory().getUtenteDAO().getUtenteByMail(((Utente)request.getSession().getAttribute("utente")).getEmail())));
		response.sendRedirect(request.getContextPath()+"/utente/home");
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath()+"/utente/home?errore=Non e' possibile eliminare questo noleggio");
		}
	}

}
