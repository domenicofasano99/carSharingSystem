package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.dao.factory.DAOFactory;
import model.Categoria;
import utilities.CookieUtilities;

/**
 * Servlet implementation class backHomeServlet
 */
@WebServlet("/backHomeServlet")
public class BackHomeAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BackHomeAdminServlet() {
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
		if(utenteMail.equals("admin")) {
			ArrayList<Categoria> categorie = new ArrayList<Categoria>(
					DAOFactory.getDAOFactory().getCategoriaDAO().getCategorie());
			request.setAttribute("categorie", categorie);
			request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp").forward(request, response);
		}
		if (utenteMail != null && !utenteMail.equals("-1")) {
			request.getRequestDispatcher("/WEB-INF/jsp/user.jsp").forward(request, response);
		} else {
			response.sendRedirect("welcome.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
