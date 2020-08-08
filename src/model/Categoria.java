package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the categoria database table.
 * 
 */
@Entity
@NamedQuery(name="Categoria.findAll", query="SELECT c FROM Categoria c")
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long idCategoria;

	@Lob
	private String descrizione;

	private String nomeCategoria;

	private double tariffaGiornaliera;

	private double tariffaMensile;

	private double tariffaSettimanale;

	//bi-directional many-to-one association to Veicolo
	@OneToMany(mappedBy="categoria")
	private List<Veicolo> veicolos;

	public Categoria() {
	}
	
	public Categoria(int id,String nome,String desc, double prezzoG,double prezzoS,double prezzoM) {
		this.idCategoria=id;
		this.nomeCategoria=nome;
		this.descrizione=desc;
		this.tariffaGiornaliera=prezzoG;
		this.tariffaSettimanale=prezzoS;
		this.tariffaMensile=prezzoM;	
	}

	public long getIdCategoria() {
		return this.idCategoria;
	}

	public void setIdCategoria(long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getNomeCategoria() {
		return this.nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public double getTariffaGiornaliera() {
		return this.tariffaGiornaliera;
	}

	public void setTariffaGiornaliera(float tariffaGiornaliera) {
		this.tariffaGiornaliera = tariffaGiornaliera;
	}

	public double getTariffaMensile() {
		return this.tariffaMensile;
	}

	public void setTariffaMensile(float tariffaMensile) {
		this.tariffaMensile = tariffaMensile;
	}

	public double getTariffaSettimanale() {
		return this.tariffaSettimanale;
	}

	public void setTariffaSettimanale(float tariffaSettimanale) {
		this.tariffaSettimanale = tariffaSettimanale;
	}

	public List<Veicolo> getVeicolos() {
		return this.veicolos;
	}

	public void setVeicolos(List<Veicolo> veicolos) {
		this.veicolos = veicolos;
	}

	public Veicolo addVeicolo(Veicolo veicolo) {
		getVeicolos().add(veicolo);
		veicolo.setCategoria(this);

		return veicolo;
	}

	public Veicolo removeVeicolo(Veicolo veicolo) {
		getVeicolos().remove(veicolo);
		veicolo.setCategoria(null);

		return veicolo;
	}

}