package Servlet;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.dao.factory.DAOFactory;
import utilities.CookieUtilities;

/**
 * Servlet implementation class GoToAffittaAutoServlet
 */
@WebServlet("/GoToAffittaAutoServlet")
public class CercaAutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CercaAutoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/utente/home");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Date dataInizio = Date.from(LocalDate.parse(request.getParameter("dataInizio"))
					.atStartOfDay(ZoneId.systemDefault()).toInstant());
			Date dataFine = Date.from(
					LocalDate.parse(request.getParameter("dataFine")).atStartOfDay(ZoneId.systemDefault()).toInstant());
			System.out.println(dataInizio + " " + dataFine);
			if (dataInizio.before(dataFine) && dataInizio.after(Date.from(Instant.now())) && dataFine != null
					&& dataInizio != null) {
				request.getSession().setAttribute("dataInizio", dataInizio);
				request.getSession().setAttribute("datafine", dataFine);
				request.getSession().setAttribute("veicoli",
						DAOFactory.getDAOFactory().getVeicoloDAO().getAutoNonNoleggiate(dataInizio, dataFine));

				response.sendRedirect(request.getContextPath() + "/utente/home");
			} else {
				response.sendRedirect(request.getContextPath() + "/utente/home?errore=Date inserite erroneamente");
			}
		} catch (DateTimeParseException e) {
			response.sendRedirect(request.getContextPath() + "/utente/home?errore=Data non inserita");

		}

	}

}
