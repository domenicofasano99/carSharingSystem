package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the utente database table.
 * 
 */
@Entity
@NamedQuery(name="Utente.findAll", query="SELECT u FROM Utente u")
public class Utente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String cognome;

	@Temporal(TemporalType.DATE)
	private Date dataDiNascita;

	private String email;

	private String nome;

	private String password;

	//bi-directional many-to-one association to Noleggio
	@OneToMany(mappedBy="utente")
	private List<Noleggio> noleggios;

	public Utente() {
	}

	public Utente(String nome, String cognome, String email, LocalDate dataN, String password) {
		this.nome = nome;
		this.cognome = cognome;
		this.email=email;
		this.dataDiNascita = Date.from(dataN.atStartOfDay(ZoneId.systemDefault()).toInstant());
		this.password = password;
	}
	
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDataDiNascita() {
		return this.dataDiNascita;
	}

	public void setDataDiNascita(LocalDate dataDiNascita) {
		this.dataDiNascita = Date.from(dataDiNascita.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Noleggio> getNoleggios() {
		return this.noleggios;
	}

	public void setNoleggios(List<Noleggio> noleggios) {
		this.noleggios = noleggios;
	}

	public Noleggio addNoleggio(Noleggio noleggio) {
		getNoleggios().add(noleggio);
		noleggio.setUtente(this);

		return noleggio;
	}

	public Noleggio removeNoleggio(Noleggio noleggio) {
		getNoleggios().remove(noleggio);
		noleggio.setUtente(null);

		return noleggio;
	}

}