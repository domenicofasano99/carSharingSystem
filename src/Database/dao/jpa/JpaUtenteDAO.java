package Database.dao.jpa;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import Database.dao.factory.JpaDAOfactory;
import Database.dao.interfaces.UtenteDAO;
import model.Utente;
import model.Veicolo;

public class JpaUtenteDAO implements UtenteDAO{

	private static JpaUtenteDAO instance;
	private JpaUtenteDAO() {
	}
	public static JpaUtenteDAO getInstance() {
		if(instance==null)
			instance= new JpaUtenteDAO();
		return instance;
	}
	@Override
	public void aggiungiUtente(Utente utente) {
		EntityManager manager = JpaDAOfactory.getManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		manager.persist(utente);
		transaction.commit();
		
	}
	@Override
	public List<Utente> getUtenti() {
		Query query = JpaDAOfactory.getManager()
				.createNamedQuery("Utente.findAll", Utente.class);
				List<Utente> utenti = query.getResultList();
				return utenti;
	}
	@Override
	public Utente getUtente(String email, String password) {
		Query query = JpaDAOfactory.getManager()
				.createQuery("SELECT u from Utente u WHERE u.email like :email and u.password like :pass");
				query.setParameter("email", email);
				query.setParameter("pass", password);
				return (Utente) query.getSingleResult();
	}
	@Override
	public Utente getUtenteByMail(String email) {
		Query query = JpaDAOfactory.getManager()
				.createQuery("SELECT u from Utente u WHERE u.email like :email");
				query.setParameter("email", email);
				return (Utente) query.getSingleResult();
	}
	@Override
	public void modifyPassword(String newPassword, Utente utente) {
		EntityManager manager = JpaDAOfactory.getManager();
		EntityTransaction tx= manager.getTransaction();
		tx.begin();
		manager.createQuery("UPDATE Utente u " + 
				"SET u.password = :password " + 
				"WHERE u.email = :email",
				Veicolo.class).setParameter("password", newPassword).setParameter("email", utente.getEmail()).executeUpdate();
		tx.commit();
		
	}
	
	
	
}
