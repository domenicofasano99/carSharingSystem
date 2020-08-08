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
 * Servlet implementation class goToAggiungiAutoServlet
 */
@WebServlet("/goToAggiungiAutoServlet")
public class goToAggiungiAutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public goToAggiungiAutoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Categoria> categorie = new ArrayList<Categoria>(
				DAOFactory.getDAOFactory().getCategoriaDAO().getCategorie());
		request.getSession().setAttribute("categorie", categorie);
		response.sendRedirect(request.getContextPath() + "/admin/home");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nomeCategoria = request.getParameter("categoria");
		System.out.println(nomeCategoria);
		Categoria categoria = DAOFactory.getDAOFactory().getCategoriaDAO()
				.getCategoriaById((int) DAOFactory.getDAOFactory().getCategoriaDAO().getIdCategoria(nomeCategoria));
		System.out.println(categoria.getNomeCategoria());
		request.getSession().setAttribute("categoria", categoria);
		response.sendRedirect(request.getContextPath() + "/admin/aggiungiAuto");
	}

}
