package Database.dao.interfaces;

import java.util.List;

import com.sun.xml.internal.bind.v2.model.core.ID;

import model.Noleggio;
import model.Utente;

public interface NoleggioDAO {

	public void aggiungiNoleggio(Noleggio noleggio);
	
	public List<Noleggio> getNoleggi();
	
	public List<Noleggio> getNoleggiByUtente(Utente utente);
	
	public Noleggio getNoleggioById(int idNoleggio);

	public void eliminaNoleggio(int idNoleggio);
	
	public List<Utente> totGiorniNoleggioPerUtente();
}
