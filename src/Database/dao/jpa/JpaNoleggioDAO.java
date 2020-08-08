package Database.dao.jpa;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import Database.dao.factory.JpaDAOfactory;
import Database.dao.interfaces.NoleggioDAO;
import model.Categoria;
import model.Noleggio;
import model.Utente;
import model.Veicolo;

public class JpaNoleggioDAO implements NoleggioDAO {
	private static JpaNoleggioDAO instance;

	private JpaNoleggioDAO() {
	}

	public static JpaNoleggioDAO getInstance() {
		if (instance == null)
			instance = new JpaNoleggioDAO();
		return instance;
	}

	@Override
	public void aggiungiNoleggio(Noleggio noleggio) {
		EntityManager manager = JpaDAOfactory.getManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		manager.persist(noleggio);
		transaction.commit();
	}
	@Override
	public List<Noleggio> getNoleggi() {
		Query query = JpaDAOfactory.getManager().createNamedQuery("Noleggio.findAll", Noleggio.class);
		List<Noleggio> noleggi = query.getResultList();
		return noleggi;
	}

	@Override
	public List<Noleggio> getNoleggiByUtente(Utente utente) {
		Query query2 = JpaDAOfactory.getManager().createQuery("select n from Noleggio n where n.utente=:utente and n.attivo=1",
				Noleggio.class);
		query2.setParameter("utente", utente);
		List<Noleggio> noleggi = (List<Noleggio>) query2.getResultList();
		return noleggi;
	}
	
	@Override
	public Noleggio getNoleggioById(int idNoleggio) {
		Query query2 = JpaDAOfactory.getManager().createQuery("select n from Noleggio n where n.idNoleggio=:idNoleggio",
				Noleggio.class);
		query2.setParameter("idNoleggio", idNoleggio);
		return (Noleggio)query2.getSingleResult();
	}

	@Override
	public void eliminaNoleggio(int idNoleggio) {
		EntityManager manager = JpaDAOfactory.getManager();
		EntityTransaction tx= manager.getTransaction();
		tx.begin();
		manager.createQuery("UPDATE Noleggio n " + 
				"SET n.attivo = 0 " + 
				"WHERE n.idNoleggio = :noleggio and n.inizioNoleggio > :dataOggi",
				Noleggio.class).setParameter("noleggio", idNoleggio).setParameter("dataOggi", Date.from(Instant.now())).executeUpdate();//
	
		tx.commit();
		

	}

	@Override
	public List<Utente> totGiorniNoleggioPerUtente() {
		return null;
	}
	
	

}
