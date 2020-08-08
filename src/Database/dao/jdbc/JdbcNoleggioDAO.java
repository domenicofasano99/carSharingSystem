package Database.dao.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import Database.dao.factory.JdbcDAOFactory;
import model.Noleggio;
import model.Utente;
import Database.dao.interfaces.NoleggioDAO;

public class JdbcNoleggioDAO implements NoleggioDAO {
	// NOLEGGI CONSTANT
	private final String TABELLA_NOLEGGI = "noleggi";
	private final String IDNOLEGGIO_NOLEGGI = "idNoleggio";
	private final String TARGA_NOLEGGI = "targa";
	private final String INIZIONOLEGGIO_NOLEGGI = "inizioNoleggio";
	private final String FINENOLEGGIO_NOLEGGI = "fineNoleggio";
	private final String IDUTENTE_NOLEGGI = "idUtente";
	private final String IMPORTO_NOLEGGI = "importoDovuto";

	// singleton
	private static JdbcNoleggioDAO instance;

	private JdbcNoleggioDAO() {
	}

	public static JdbcNoleggioDAO getInstance() {
		if (instance == null)
			instance = new JdbcNoleggioDAO();
		return instance;
	}

	@Override
	public void aggiungiNoleggio(Noleggio noleggio) {
		try (Connection connection = JdbcDAOFactory.getConnection()) {
			String sql = "INSERT INTO " + TABELLA_NOLEGGI + "(" + IDNOLEGGIO_NOLEGGI + "," + TARGA_NOLEGGI + ","
					+ INIZIONOLEGGIO_NOLEGGI + "," + FINENOLEGGIO_NOLEGGI + "," + IDUTENTE_NOLEGGI + ","
					+ IMPORTO_NOLEGGI + ") VALUES (?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(2, noleggio.getVeicolo().getTarga());
			statement.setDate(3, (Date) noleggio.getInizioNoleggio());
			statement.setDate(4, (Date)noleggio.getFineNoleggio());
			statement.setLong(5,  noleggio.getUtente().getId());
			statement.setDouble(6, noleggio.getImportoDovuto());
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Noleggio> getNoleggi() {
		return null;
	}


	@Override
	public List<Noleggio> getNoleggiByUtente(Utente utente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Noleggio getNoleggioById(int idNoleggio) {
		return null;
	}

	@Override
	public void eliminaNoleggio(int idNoleggio) {

	}

	@Override
	public List<Utente> totGiorniNoleggioPerUtente() {
		return null;
	}

}
