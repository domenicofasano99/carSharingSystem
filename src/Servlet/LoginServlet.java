package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.dao.factory.DAOFactory;
import model.Categoria;
import model.Utente;
import utilities.CookieUtilities;

/**
 * Servlet implementation class HomePage
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String utenteMail = CookieUtilities.getUserMail(request, response);
		if(utenteMail==null)
			response.sendRedirect(request.getContextPath()+"/welcome.jsp");
		else {
		if(utenteMail.equals("admin")) {
			ArrayList<Categoria> categorie = new ArrayList<Categoria>(
					DAOFactory.getDAOFactory().getCategoriaDAO().getCategorie());
			request.getSession().setAttribute("categorie", categorie);
			response.sendRedirect(request.getContextPath()+"/admin/home");
		}
		else if (utenteMail != null && !utenteMail.equals("-1")) {
			response.sendRedirect(request.getContextPath()+"/utente/home");
		} else {
			response.sendRedirect(request.getContextPath()+"/welcome.jsp");
		}}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext context = getServletContext();
		String admin = context.getInitParameter("admin");
		String adminPass = context.getInitParameter("password");
		String utente = request.getParameter("mail");
		String utentePass = request.getParameter("pass");
		Utente utentedb = null;
		ArrayList<Categoria> categorie = null;

		try {
			if (admin.equals(utente) && adminPass.equals(utentePass)) {
				 categorie = new ArrayList<Categoria>(
						DAOFactory.getDAOFactory().getCategoriaDAO().getCategorie());
				Cookie cookie = new Cookie("mail", "admin");
				response.addCookie(cookie);
				request.getSession().setAttribute("admin", admin);
				request.getSession().setAttribute("categorie", categorie);
				response.sendRedirect(request.getContextPath()+"/admin/home");
			} else if (DAOFactory.getDAOFactory().getUtenteDAO().getUtente(utente, utentePass) != null) {
				utentedb = DAOFactory.getDAOFactory().getUtenteDAO().getUtente(utente, utentePass);
				Cookie cookie = new Cookie("mail", utentedb.getEmail());
				response.addCookie(cookie);
				request.getSession().setAttribute("utente", utentedb);
				response.sendRedirect(request.getContextPath()+"/utente/home");

			} else {
				response.sendRedirect(request.getContextPath()+"/welcome.jsp?errore=login errata");
			}
		} catch (Exception e) {
			if(utente!= null && utente.length()<3 && utentePass.length()<4)
				response.sendRedirect(request.getContextPath()+"/welcome.jsp?errore=Mail o Password errate");
			else response.sendRedirect(request.getContextPath()+"/welcome.jsp?errore=Qualcosa e' andato storto");
		}

	}

}
