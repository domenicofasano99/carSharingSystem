package Database.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Database.dao.factory.JdbcDAOFactory;
import model.Categoria;
import Database.dao.interfaces.CategoriaDAO;

public class JdbcCategoriaDAO implements CategoriaDAO {

	// CATEGORIA CONSTANT
	private final String TABELLA_CATEGORIA = "categorie";
	private final String IDCATEGORIA_CATEGORIA = "idCategoria";
	private final String TARIFFAG_CATEGORIA = "tariffaGiornaliera";
	private final String TARIFFAS_CATEGORIA = "tariffaSettimanale";
	private final String TARIFFAM_CATEGORIA = "tariffaMensile";
	private final String NOMECATEGORIA_CATEGORIA = "nomeCategoria";
	private final String DESCRIZIONE_CATEGORIA = "descrizione";

	// singleton
	private static JdbcCategoriaDAO instance;

	private JdbcCategoriaDAO() {
	}

	public static JdbcCategoriaDAO getInstance() {
		if (instance == null)
			instance = new JdbcCategoriaDAO();
		return instance;
	}

	@Override
	public long getIdCategoria(String nomeCategoria) {
		try (Connection connection = JdbcDAOFactory.getConnection()) {
			String sql = "SELECT * FROM " + TABELLA_CATEGORIA + " where " + NOMECATEGORIA_CATEGORIA + " = ?";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, nomeCategoria);
			ResultSet set = preparedStatement.executeQuery();

			if (set.next())
				return set.getLong(set.findColumn(IDCATEGORIA_CATEGORIA));
			return -1;
		} catch (SQLException e) {
			return -1;
		}

	}

	@Override
	public List<Categoria> getCategorie() {
		List<Categoria> categorie = new ArrayList<>();
		try (Connection connection = JdbcDAOFactory.getConnection()) {
			String sql = "SELECT * FROM " + TABELLA_CATEGORIA;

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet set = preparedStatement.executeQuery();
			while (set.next()) {
				int id = set.getInt(set.findColumn(IDCATEGORIA_CATEGORIA));
				String nome = set.getString(set.findColumn(NOMECATEGORIA_CATEGORIA));
				double tariffaG = set.getDouble(set.findColumn(TARIFFAG_CATEGORIA));
				double tariffaS = set.getDouble(set.findColumn(TARIFFAS_CATEGORIA));
				double tariffaM = set.getDouble(set.findColumn(TARIFFAM_CATEGORIA));
				String desc = set.getString(set.findColumn(DESCRIZIONE_CATEGORIA));
				Categoria categoria = new Categoria(id, nome, desc, tariffaG, tariffaS, tariffaM);
				categorie.add(categoria);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categorie;
	}

	@Override
	public void aggiungiCategoria(Categoria categoria) throws Exception {
		try (Connection connection = JdbcDAOFactory.getConnection()) {
			String sql = "INSERT INTO " + TABELLA_CATEGORIA + "(" + TARIFFAG_CATEGORIA + "," + TARIFFAS_CATEGORIA + ","
					+ TARIFFAM_CATEGORIA + "," + NOMECATEGORIA_CATEGORIA + "," + DESCRIZIONE_CATEGORIA
					+ ") VALUES (?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setDouble(1, categoria.getTariffaGiornaliera());
			statement.setDouble(2, categoria.getTariffaSettimanale());
			statement.setDouble(3, categoria.getTariffaMensile());
			statement.setString(4, categoria.getNomeCategoria());
			statement.setString(5, categoria.getDescrizione());
			if (!(statement.executeUpdate() == 1))
				throw new Exception( "errore=Problema con la connessione al DB");
		} catch (SQLException e) {
			throw new Exception("errore=Categoria gia' presente");

		}
	}

	@Override
	public Categoria getCategoriaById(int idCategoria) {
		// TODO Auto-generated method stub
		return null;
	}

}
