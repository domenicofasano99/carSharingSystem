package Database.dao.factory;

import Database.dao.interfaces.*;

public abstract class DAOFactory {
	public abstract CategoriaDAO getCategoriaDAO();

	public abstract UtenteDAO getUtenteDAO();

	public abstract NoleggioDAO getNoleggioDAO();

	public abstract VeicoloDAO getVeicoloDAO();

	public static DAOFactory getDAOFactory() {
		return new JpaDAOfactory();
	}
}
