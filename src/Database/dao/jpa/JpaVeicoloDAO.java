package Database.dao.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.sun.org.apache.bcel.internal.generic.NEW;

import Database.dao.factory.JpaDAOfactory;
import Database.dao.interfaces.VeicoloDAO;
import model.Categoria;
import model.Noleggio;
import model.Veicolo;

public class JpaVeicoloDAO implements VeicoloDAO {
	private static JpaVeicoloDAO instance;

	private JpaVeicoloDAO() {
	}

	public static JpaVeicoloDAO getInstance() {
		if (instance == null)
			instance = new JpaVeicoloDAO();
		return instance;
	}

	@Override
	public List<Veicolo> getAutoPerCategoria(int categoria) {
		Query query2 = JpaDAOfactory.getManager()
				.createQuery("select c from Categoria c where c.idCategoria=:categoria");
		query2.setParameter("categoria", categoria);
		Categoria cat = (Categoria)query2.getSingleResult();
		
		
		Query query = JpaDAOfactory.getManager().createQuery("SELECT v FROM Veicolo v WHERE v.categoria=:cat and v.attivo=1");
		query.setParameter("cat", cat);
		List<Veicolo> veicoli= query.getResultList(); 
		return veicoli;
	}
	
	@Override
	public List<Veicolo> getAuto() {
		Query query = JpaDAOfactory.getManager().createQuery("SELECT v FROM Veicolo v where v.attivo=1");
		List<Veicolo> veicoli= query.getResultList(); 
		return veicoli;
	}

	@Override
	public void aggiungiAutomobile(Veicolo automobile) {
		EntityManager manager = JpaDAOfactory.getManager();
		EntityTransaction transaction= manager.getTransaction();
		transaction.begin();
		manager.persist(automobile);
		transaction.commit();
	}

	@Override
	public List<Veicolo> getAutoNonNoleggiate(Date dataInizio, Date dataFine) {
		Query query = JpaDAOfactory.getManager().createNativeQuery("select * from veicolo where attivo=1 and targa not in(select targa from noleggio where ((inizioNoleggio<= ? and fineNoleggio>=?) or" + 
				"									(inizioNoleggio<=? and fineNoleggio>=?) or" + 
				"                                    (inizioNoleggio>=? and fineNoleggio<=?)) and attivo=1)", Veicolo.class);

		query.setParameter(1, dataInizio);
		query.setParameter(2, dataInizio);
		query.setParameter(3, dataFine);
		query.setParameter(4, dataFine);
		query.setParameter(5, dataInizio);
		query.setParameter(6, dataFine);
		System.out.println(""+dataFine);
		
		List<Veicolo> veicoli= query.getResultList();
		return veicoli;
	}

	@Override
	public Veicolo getAutoByTarga(String targa) {
		Query query = JpaDAOfactory.getManager()
				.createQuery("select v from Veicolo v where v.targa=:targa and v.attivo=1");
		query.setParameter("targa", targa);
		return (Veicolo) query.getSingleResult();
	}
	
	@Override
	public void eliminaVeicolo(String targa) {
		EntityManager manager = JpaDAOfactory.getManager();
		EntityTransaction tx= manager.getTransaction();
		tx.begin();
		manager.createQuery("UPDATE Veicolo v " + 
				"SET v.attivo = 0 " + 
				"WHERE v.targa = :veicolo",
				Veicolo.class).setParameter("veicolo", targa).executeUpdate();
		tx.commit();
		

	}

}
