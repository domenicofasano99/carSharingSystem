package Servlet;

import java.io.IOException;
import java.util.Date;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.dao.factory.DAOFactory;
import model.Noleggio;
import model.Utente;
import model.Veicolo;
import utilities.CookieUtilities;

/**
 * Servlet implementation class CalcoloAffittoServlet
 */
@WebServlet("/CalcoloAffittoServlet")
public class CalcoloAffittoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalcoloAffittoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Noleggio noleggio = (Noleggio) request.getSession().getAttribute("noleggio");
		response.sendRedirect(request.getContextPath()+"/utente/preventivoAffitto");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LocalDate dataInizio= Instant.ofEpochMilli(((Date)request.getSession().getAttribute("dataInizio")).getTime())
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate();
		LocalDate dataFine=Instant.ofEpochMilli(((Date)request.getSession().getAttribute("datafine")).getTime())
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
		String targa= request.getParameter("auto");
		Utente utente=(Utente)request.getSession().getAttribute("utente");
		Veicolo veicolo= DAOFactory.getDAOFactory().getVeicoloDAO().getAutoByTarga(targa);
		double costoAffitto=0;
		Duration diff = Duration.between(dataInizio.atStartOfDay(), dataFine.atStartOfDay());
		long diffDays = diff.toDays();
		if(((int)diffDays/30)>0) {
			costoAffitto+=veicolo.getCategoria().getTariffaMensile()*((int)diffDays/30);
			diffDays=diffDays%30;
		}
		if(((int)diffDays/7)>0) {
			costoAffitto+=veicolo.getCategoria().getTariffaMensile()*((int)diffDays/7);
			diffDays=diffDays%7;
		}
		if(((int)diffDays/7)==0)
			costoAffitto+=veicolo.getCategoria().getTariffaGiornaliera()*diffDays;
		Noleggio noleggio=new Noleggio(veicolo, dataInizio, dataFine, utente, costoAffitto);
		request.getSession().setAttribute("noleggio", noleggio);
		response.sendRedirect(request.getContextPath()+"/utente/preventivoAffitto");
	
		
		
	}

}
