package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.dao.factory.DAOFactory;
import utilities.CookieUtilities;

/**
 * Servlet implementation class ViewParcoAutoServlet
 */
@WebServlet("/ViewParcoAutoServlet")
public class ViewParcoAutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewParcoAutoServlet() {
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
					request.getSession().setAttribute("veicoli", DAOFactory.getDAOFactory().getVeicoloDAO().getAuto());
					response.sendRedirect(request.getContextPath()+"/utente/parcoAuto");
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
				try {
					request.getSession().setAttribute("veicoli", DAOFactory.getDAOFactory().getVeicoloDAO().getAuto());
					response.sendRedirect(request.getContextPath()+"/utente/parcoAuto");
				} catch (Exception e) {
					response.sendRedirect(request.getContextPath()+"/utente/home?errore=Qualcosa e' andato storto");
				}
	}

}
