package Database.dao.interfaces;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import model.Veicolo;

public interface VeicoloDAO {
	public List<Veicolo> getAutoPerCategoria(int categoria);
	
	public List<Veicolo> getAutoNonNoleggiate(Date dataInizio, Date dataFine);
	
	public void aggiungiAutomobile(Veicolo automobile) throws Exception;
	
	public Veicolo getAutoByTarga(String targa);
	
	public List<Veicolo> getAuto();
	
	public void eliminaVeicolo(String targa);

}
