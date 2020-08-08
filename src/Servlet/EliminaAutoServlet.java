package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.dao.factory.DAOFactory;
import model.Categoria;
import utilities.CookieUtilities;

/**
 * Servlet implementation class EliminaAutoServlet
 */
@WebServlet("/EliminaAutoServlet")
public class EliminaAutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaAutoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String utenteMail = CookieUtilities.getUserMail(request, response);
			ArrayList<Categoria> categorie = new ArrayList<Categoria>(
					DAOFactory.getDAOFactory().getCategoriaDAO().getCategorie());
			request.getSession().setAttribute("categorie", categorie);
			response.sendRedirect(request.getContextPath()+"/admin/home");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String targa = request.getParameter("veicolo");
		try {
			DAOFactory.getDAOFactory().getVeicoloDAO().eliminaVeicolo(targa);
		} catch (Exception e) {
			request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp?errore=Auto non eliminata").forward(request, response);
		}
		request.getSession().setAttribute("veicoli", DAOFactory.getDAOFactory().getVeicoloDAO().getAutoPerCategoria((int)((Categoria)request.getSession().getAttribute("categoria")).getIdCategoria()));
		request.getSession().setAttribute("categorie", DAOFactory.getDAOFactory().getCategoriaDAO().getCategorie());
		response.sendRedirect(request.getContextPath()+"/admin/home");
	}

}
