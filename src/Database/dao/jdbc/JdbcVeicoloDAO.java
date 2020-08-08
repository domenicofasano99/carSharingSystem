package Database.dao.jdbc;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Database.dao.factory.DAOFactory;
import Database.dao.factory.JdbcDAOFactory;
import model.Veicolo;
import Database.dao.interfaces.VeicoloDAO;

public class JdbcVeicoloDAO implements VeicoloDAO{

	// AUTOMOBILE CONSTANT
	private final String TABELLA_AUTOMOBILE = "veicolo";
	private final String TARGA_AUTOMOBILE = "targa";
	private final String FK_CATEGORIA_AUTOMOBILE = "idCategoria";
	private final String MARCA_AUTOMOBILE = "marca";
	private final String MODELLO_AUTOMOBILE = "modello";
	private final String NUMERO_POSTI = "nPosti";
	private final String COLORE_AUTOMOBILE = "colore";

	// Singleton
	private static JdbcVeicoloDAO instance;

	private JdbcVeicoloDAO() {
	}

	public static JdbcVeicoloDAO getInstance() {
		if (instance == null)
			instance = new JdbcVeicoloDAO();
		return instance;
	}

	@Override
	public List<Veicolo> getAutoPerCategoria(int categoria) {
		List<Veicolo> automobili = new ArrayList<>();
		try (Connection connection = JdbcDAOFactory.getConnection()) {
			String sql = "SELECT * FROM " + TABELLA_AUTOMOBILE + " WHERE " +

					FK_CATEGORIA_AUTOMOBILE + " = ?";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, categoria);
			ResultSet set = preparedStatement.executeQuery();
			while (set.next()) {
				String targa = set.getString(set.findColumn(TARGA_AUTOMOBILE));
				String marca = set.getString(set.findColumn(MARCA_AUTOMOBILE));
				String modello = set.getString(set.findColumn(MODELLO_AUTOMOBILE));
				int nPosti = set.getInt(set.findColumn(NUMERO_POSTI));
				String colore = set.getString(set.findColumn(COLORE_AUTOMOBILE));
				Veicolo a = new Veicolo(targa, marca, modello, nPosti, DAOFactory.getDAOFactory().getCategoriaDAO().getCategorie().get(categoria), colore);
				automobili.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return automobili;
	}

	public void aggiungiAutomobile(Veicolo automobile) throws Exception{
		try (Connection connection = JdbcDAOFactory.getConnection()) {
			String sql = "INSERT INTO " + TABELLA_AUTOMOBILE + "(" + TARGA_AUTOMOBILE + "," + FK_CATEGORIA_AUTOMOBILE
					+ "," + MARCA_AUTOMOBILE + "," + MODELLO_AUTOMOBILE + "," + NUMERO_POSTI + "," + COLORE_AUTOMOBILE
					+ ") VALUES (?,?,?,?,?,?)";
			System.out.println(automobile.getTarga());
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, automobile.getTarga());
			statement.setLong(2, automobile.getCategoria().getIdCategoria());
			statement.setString(3, automobile.getMarca());
			statement.setString(4, automobile.getModello());
			statement.setInt(5, automobile.getNPosti());
			statement.setString(6, automobile.getColore());
			if (!(statement.executeUpdate() == 1))
				throw new Exception( "errore=Automobile gia' presente");
		} catch (SQLException e) {
			throw new Exception( "errore=Problema con la connessione al DB");

		}
	}


	@Override
	public List<Veicolo> getAutoNonNoleggiate(Date dataInizio, Date dataFine) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Veicolo getAutoByTarga(String targa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Veicolo> getAuto() {
		return null;
	}

	@Override
	public void eliminaVeicolo(String targa) {

	}

}
