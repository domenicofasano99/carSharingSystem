package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the veicolo database table.
 * 
 */
@Entity
@NamedQuery(name="Veicolo.findAll", query="SELECT v FROM Veicolo v")
public class Veicolo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String targa;

	private String colore;

	private String marca;

	private String modello;

	private int nPosti;
	private int attivo;

	//bi-directional many-to-one association to Noleggio
	@OneToMany(mappedBy="veicolo")
	private List<Noleggio> noleggio;

	//bi-directional many-to-one association to Categoria
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idCategoria")
	private Categoria categoria;

	public Veicolo() {
	}
	
	public Veicolo(String targa,String marca, String modello, int numeroPosti, Categoria categoria, String colore) {
		this.targa=targa;
		this.marca=marca;
		this.modello=modello;
		this.nPosti=numeroPosti;
		this.categoria=categoria;
		this.colore=colore;
		this.attivo=1;
	}

	public String getTarga() {
		return this.targa;
	}
	public long getAttivo() {
		return this.attivo;
	}

	public void setAttivo(int attivo) {
		this.attivo = attivo;
	}
	public void setTarga(String targa) {
		this.targa = targa;
	}

	public String getColore() {
		return this.colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModello() {
		return this.modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public int getNPosti() {
		return this.nPosti;
	}

	public void setNPosti(int nPosti) {
		this.nPosti = nPosti;
	}

	public List<Noleggio> getNoleggios() {
		return this.noleggio;
	}

	public void setNoleggios(List<Noleggio> noleggios) {
		this.noleggio = noleggios;
	}

	public Noleggio addNoleggio(Noleggio noleggio) {
		getNoleggios().add(noleggio);
		noleggio.setVeicolo(this);

		return noleggio;
	}

	public Noleggio removeNoleggio(Noleggio noleggio) {
		getNoleggios().remove(noleggio);
		noleggio.setVeicolo(null);

		return noleggio;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}