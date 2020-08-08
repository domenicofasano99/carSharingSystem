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
 * Servlet implementation class AggiungiCategoriaServlet
 */
@WebServlet("/AggiungiCategoriaServlet")
public class AggiungiCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AggiungiCategoriaServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			response.sendRedirect(request.getContextPath()+"/admin/aggiungiCategoria");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nome = request.getParameter("nome");
		String inserito = "";
		try {
			double prezzoG = Double.parseDouble(request.getParameter("prezzoG"));
			double prezzoS = Double.parseDouble(request.getParameter("prezzoS"));
			double prezzoM = Double.parseDouble(request.getParameter("prezzoM"));
			String desc = request.getParameter("descrizione");
			if (nome != null && prezzoG > 0 && desc != null && prezzoM > 0 && prezzoS > 0) {
				try {
					DAOFactory.getDAOFactory().getCategoriaDAO()
							.aggiungiCategoria(new Categoria(0, nome, desc, prezzoG, prezzoS, prezzoM));
					inserito="messaggio=Categoria aggiunta";
				} catch (Exception e) {
					inserito= "errore=Categoria non aggiunta";
				}
				
				request.getSession().setAttribute("categorie", DAOFactory.getDAOFactory().getCategoriaDAO().getCategorie());
				response.sendRedirect(request.getContextPath()+"/admin/home?"+inserito);
			} else {
				response.sendRedirect(request.getContextPath()+"/admin/aggiungiCategoria?errore=Inserimento non riuscito");
			}
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath()+"/admin/aggiungiCategoria?errore=Inserimento non riuscito");
		}

	}

}
