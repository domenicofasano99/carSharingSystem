package Database.dao.factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Database.dao.interfaces.CategoriaDAO;
import Database.dao.interfaces.NoleggioDAO;
import Database.dao.interfaces.UtenteDAO;
import Database.dao.interfaces.VeicoloDAO;
import Database.dao.jpa.JpaCategoriaDAO;
import Database.dao.jpa.JpaNoleggioDAO;
import Database.dao.jpa.JpaUtenteDAO;
import Database.dao.jpa.JpaVeicoloDAO;

public class JpaDAOfactory extends DAOFactory{

	public static EntityManager getManager() {
		try {
		Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		}
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("AutonoleggioJPA");
		EntityManager manager = factory.createEntityManager();
		return manager;
		}
	
	@Override
	public CategoriaDAO getCategoriaDAO() {
		// TODO Auto-generated method stub
		return JpaCategoriaDAO.getInstance();
	}

	@Override
	public UtenteDAO getUtenteDAO() {
		// TODO Auto-generated method stub
		return JpaUtenteDAO.getInstance();
	}

	@Override
	public NoleggioDAO getNoleggioDAO() {
		// TODO Auto-generated method stub
		return JpaNoleggioDAO.getInstance();
	}

	@Override
	public VeicoloDAO getVeicoloDAO() {
		// TODO Auto-generated method stub
		return JpaVeicoloDAO.getInstance();
	}
//getManager
}
