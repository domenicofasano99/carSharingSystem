package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Categoria;
import model.Veicolo;
import utilities.CookieUtilities;
import Database.dao.factory.DAOFactory;

/**
 * Servlet implementation class AggiungiAutoServlet
 */
@WebServlet("/AggiungiAutoServlet")
public class AggiungiAutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AggiungiAutoServlet() {
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
		String regexTarga = "[A-Z]{2,2}[0-9]{3,3}[A-Z]{2,2}";
		String targa = request.getParameter("targa");
		String marca = request.getParameter("marca");
		String modello = request.getParameter("modello");
		String colore = request.getParameter("colore");
		String inserito="";
		Categoria categoria = (Categoria)request.getSession().getAttribute("categoria");
		try {
			int nPosti = Integer.parseInt(request.getParameter("nPosti"));
			if (targa.matches(regexTarga) && marca.length() > 2 && modello.length() > 2 && nPosti < 10 && nPosti > 0) {
				try {
					DAOFactory.getDAOFactory().getVeicoloDAO().aggiungiAutomobile(new Veicolo(targa, marca, modello, nPosti, categoria, colore));
					inserito="messaggio=Automobile aggiunta correttamente";
				} catch (Exception e) {
					inserito="errore=automobile già inserita";
				}
				request.getSession().setAttribute("categorie", DAOFactory.getDAOFactory().getCategoriaDAO().getCategorie());
				response.sendRedirect(request.getContextPath()+"/admin/home?"+inserito);
			} else if (!targa.matches(regexTarga)) {
				response.sendRedirect(request.getContextPath()+"/admin/aggiungiAuto?errore=La targa non rispetta i canoni");
						
			} else if (nPosti > 10 || nPosti < 0) {
				response.sendRedirect(request.getContextPath()+"/admin/aggiungiAuto?errore=Non e' possibile inserire il numero di posti da lei richiesto");
						
			} else {
				response.sendRedirect(request.getContextPath()+"/admin/aggiungiAuto?errore=I campi non rispettano i requisiti minimi");
			}
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath()+"/admin/aggiungiAuto?errore=I campi non rispettano i requisiti minimi");

		}

	}

}
