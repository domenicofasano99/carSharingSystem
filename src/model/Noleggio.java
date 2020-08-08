package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the noleggio database table.
 * 
 */
@Entity
@NamedQuery(name="Noleggio.findAll", query="SELECT n FROM Noleggio n")
public class Noleggio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long idNoleggio;

	@Temporal(TemporalType.DATE)
	private Date fineNoleggio;

	private double importoDovuto;

	@Temporal(TemporalType.DATE)
	private Date inizioNoleggio;

	//bi-directional many-to-one association to Utente
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idUtente")
	private Utente utente;

	//bi-directional many-to-one association to Veicolo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="targa")
	private Veicolo veicolo;
	
	private int attivo;

	public Noleggio() {
	}
	
	public Noleggio(Veicolo veicolo, LocalDate inizioNoleggio,LocalDate fineNoleggio, Utente utente, double importoDovuto) {
		this.veicolo=veicolo;
		this.inizioNoleggio=Date.from(inizioNoleggio.atStartOfDay(ZoneId.systemDefault()).toInstant());
		this.fineNoleggio=Date.from(fineNoleggio.atStartOfDay(ZoneId.systemDefault()).toInstant());
		this.utente=utente;
		this.importoDovuto=importoDovuto;
		this.attivo=1;
		// TODO Auto-generated constructor stub
	}

	public long getIdNoleggio() {
		return this.idNoleggio;
	}

	public void setIdNoleggio(long idNoleggio) {
		this.idNoleggio = idNoleggio;
	}
	public long getAttivo() {
		return this.attivo;
	}

	public void setAttivo(int attivo) {
		this.attivo = attivo;
	}

	public Date getFineNoleggio() {
		return this.fineNoleggio;
	}

	public void setFineNoleggio(LocalDate fineNoleggio) {
		this.fineNoleggio = Date.from(fineNoleggio.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public double getImportoDovuto() {
		return this.importoDovuto;
	}

	public void setImportoDovuto(double importoDovuto) {
		this.importoDovuto = importoDovuto;
	}

	public Date getInizioNoleggio() {
		return this.inizioNoleggio;
	}

	public void setInizioNoleggio(LocalDate inizioNoleggio) {
		this.inizioNoleggio = Date.from(inizioNoleggio.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public Utente getUtente() {
		return this.utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Veicolo getVeicolo() {
		return this.veicolo;
	}

	public void setVeicolo(Veicolo veicolo) {
		this.veicolo = veicolo;
	}

}