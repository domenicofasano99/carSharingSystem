package Database.dao.interfaces;


import java.util.List;

import model.Utente;

public interface UtenteDAO {
	// UTENTE CONSTANT

	public void aggiungiUtente(Utente nuovoU) throws Exception;

	public List<Utente> getUtenti();
	public Utente getUtente(String email, String password);
	public Utente getUtenteByMail(String email);
	public void modifyPassword(String newPassword, Utente utente);
}
