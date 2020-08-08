package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.dao.factory.DAOFactory;
import model.Utente;


/**
 * Servlet implementation class ViewAutoClientServlet
 */
@WebServlet("/ViewAutoClientServlet")
public class ViewAutoClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAutoClientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("welcome.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String categoria = request.getParameter("categoria");
		int idcategoria = Integer.parseInt(request.getParameter("idcategoria").trim());
		request.setAttribute("veicoli", DAOFactory.getDAOFactory().getVeicoloDAO()
				.getAutoPerCategoria(idcategoria));
		request.setAttribute("categorie", DAOFactory.getDAOFactory().getCategoriaDAO().getCategorie());
		request.getSession().setAttribute("noleggi", DAOFactory.getDAOFactory().getNoleggioDAO()
				.getNoleggiByUtente(DAOFactory.getDAOFactory().getUtenteDAO().getUtenteByMail(((Utente)request.getSession().getAttribute("utente")).getEmail())));
		
		request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp?categoriaView=" + categoria).forward(request, response);
	}

}
