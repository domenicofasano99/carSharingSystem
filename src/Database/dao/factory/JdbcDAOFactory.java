package Database.dao.factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import Database.dao.jdbc.*;
import Database.dao.factory.DAOFactory;
import Database.dao.interfaces.*;

public class JdbcDAOFactory extends DAOFactory {

	public JdbcDAOFactory() {
		// TODO Auto-generated constructor stub
	}

	public static Connection getConnection() throws SQLException {
		try {
			// Rimosso il DriverManager per utilizzare i parametri in context.xml
			DataSource source = InitialContext.doLookup("java:comp/env/jdbc/autonoleggio");
			return source.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CategoriaDAO getCategoriaDAO() {
		// TODO Auto-generated method stub
		return JdbcCategoriaDAO.getInstance();
	}

	@Override
	public UtenteDAO getUtenteDAO() {
		// TODO Auto-generated method stub
		return JdbcUtenteDAO.getInstance();
	}

	@Override
	public NoleggioDAO getNoleggioDAO() {
		// TODO Auto-generated method stub
		return JdbcNoleggioDAO.getInstance();
	}

	@Override
	public VeicoloDAO getVeicoloDAO() {
		// TODO Auto-generated method stub
		return JdbcVeicoloDAO.getInstance();
	}

}
