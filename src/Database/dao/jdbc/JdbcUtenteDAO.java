package Database.dao.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Database.dao.factory.JdbcDAOFactory;
import model.Utente;
import Database.dao.interfaces.UtenteDAO;

public class JdbcUtenteDAO implements UtenteDAO {
	private final String TABELLA_UTENTE = "utente";
	private final String IDUTENTE_UTENTE = "idUtente";
	private final String EMAIL_UTENTE = "email";
	private final String PASSWORD_UTENTE = "password";
	private final String NOME_UTENTE = "nome";
	private final String COGNOME_UTENTE = "cognome";
	private final String DATANASCITA_UTENTE = "dataDiNascita";

	private String regexMail = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
	private String regexPass = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}";// (?=.*[@#$%^&+=])(?=\\\\S+$) special char and

	// singleton
	private static JdbcUtenteDAO instance;

	private JdbcUtenteDAO() {
	}

	public static JdbcUtenteDAO getInstance() {
		if (instance == null)
			instance = new JdbcUtenteDAO();
		return instance;
	}

	// Override dei metodi

	@Override
	public void aggiungiUtente(Utente nuovoU) throws Exception {
		if (nuovoU.getDataDiNascita() != null) {
			long p2 = ChronoUnit.DAYS.between(nuovoU.getDataDiNascita().toInstant(), LocalDate.now());
			if (nuovoU.getPassword().matches(regexPass) && nuovoU.getEmail().matches(regexMail) && p2 > 6480) {
				try (Connection connection = JdbcDAOFactory.getConnection()) {
					String sql = "INSERT INTO " + TABELLA_UTENTE + "(" + EMAIL_UTENTE + "," + PASSWORD_UTENTE + ","
							+ NOME_UTENTE + "," + COGNOME_UTENTE + "," + DATANASCITA_UTENTE + ") VALUES (?,?,?,?,?)";
					PreparedStatement statement = connection.prepareStatement(sql);
					statement.setString(1, nuovoU.getEmail());
					statement.setString(2, nuovoU.getPassword());
					statement.setString(3, nuovoU.getNome());
					statement.setString(4, nuovoU.getCognome());
					statement.setDate(5, Date.valueOf((nuovoU.getDataDiNascita().toInstant().toString())));
					System.out.println(nuovoU.toString());
					if (!(statement.executeUpdate() == 1))
						throw new Exception("errore=Utente gia' registrato");
				} catch (SQLException e) {
					throw new Exception("errore=Problema con la connessione al DB");

				}
			} else if (!nuovoU.getPassword().matches(regexPass))
				throw new Exception( "errore=La password deve contenere almeno una MAIUSCOLA, una minuscola e un numero");
			else if (p2 < 6480)
				throw new Exception( "errore=Bisogna aver raggiunto la maggiore eta' per registrarsi");
			else
				throw new Exception( "errore=Indirizzo e-Mail non esistente");
		}
		throw new Exception( "errore=Data non corretta");
	}

	@Override
	public List<Utente> getUtenti() {
		List<Utente> utenti = new ArrayList<>();
		try (Connection connection = JdbcDAOFactory.getConnection()) {
			String sql = "SELECT * FROM " + TABELLA_UTENTE;

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet set = preparedStatement.executeQuery();
			while (set.next()) {
				int id = set.getInt(set.findColumn(IDUTENTE_UTENTE));
				String nome = set.getString(set.findColumn(NOME_UTENTE));
				String cognome = set.getString(set.findColumn(COGNOME_UTENTE));
				String email = set.getString(set.findColumn(EMAIL_UTENTE));
				LocalDate dataNascita = set.getDate(set.findColumn(DATANASCITA_UTENTE)).toLocalDate();
				String password = set.getString(set.findColumn(PASSWORD_UTENTE));
				Utente utente = new Utente(nome, cognome, email, dataNascita, password);
				utenti.add( utente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utenti;
	}

	@Override
	public Utente getUtente(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utente getUtenteByMail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modifyPassword(String newPassword, Utente utente) {

	}

}
