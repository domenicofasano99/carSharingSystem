package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.Categoria;
import utilities.CookieUtilities;
import Database.dao.factory.DAOFactory;

/**
 * Servlet implementation class ViewAutoServlet
 */
@WebServlet("/ViewAutoServlet")
public class ViewAutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewAutoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			request.getSession().setAttribute("veicoli", DAOFactory.getDAOFactory().getVeicoloDAO().getAutoPerCategoria((int)((Categoria)request.getSession().getAttribute("categoria")).getIdCategoria()));
			ArrayList<Categoria> categorie = new ArrayList<Categoria>(
					DAOFactory.getDAOFactory().getCategoriaDAO().getCategorie());
			request.getSession().setAttribute("categorie", categorie);
			response.sendRedirect(request.getContextPath()+"/admin/home");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String categoria = request.getParameter("categoria");
		int idcategoria = Integer.parseInt(request.getParameter("idcategoria").trim());
		System.out.println(categoria);
		request.getSession().setAttribute("categoria",
				DAOFactory.getDAOFactory().getCategoriaDAO().getCategoriaById(idcategoria));
		request.getSession().setAttribute("veicoli", DAOFactory.getDAOFactory().getVeicoloDAO().getAutoPerCategoria((int)((Categoria)request.getSession().getAttribute("categoria")).getIdCategoria()));
		request.getSession().setAttribute("categorie", DAOFactory.getDAOFactory().getCategoriaDAO().getCategorie());
		response.sendRedirect(request.getContextPath()+"/admin/home");
	}

}
