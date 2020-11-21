package it.bibliotecaweb.model.libro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import it.bibliotecaweb.model.autore.Autore;

@Entity
@Table(name = "libro")
public class Libro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "titolo")
	private String titolo;
	@Column(name = "trama")
	private String trama;
	
	@Enumerated(EnumType.STRING)
	private Genere genere;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "autore_id")
	private Autore autore;

	public Libro() {
	}
	
	public Libro(String titolo, String trama) {
		this.titolo = titolo;
		this.trama = trama;
	}
	
	public Libro(String titolo, String trama, Genere genere) {
		this.titolo = titolo;
		this.trama = trama;
		this.genere = genere;
	}
	
	public Libro(String titolo, String trama, Genere genere, Autore autore) {
		this.titolo = titolo;
		this.trama = trama;
		this.genere = genere;
		this.autore = autore;
	}
	public Libro(String titolo) {
		this.titolo=titolo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getTrama() {
		return trama;
	}

	public void setTrama(String trama) {
		this.trama = trama;
	}

	public Genere getGenere() {
		return genere;
	}

	public void setGenere(Genere genere) {
		this.genere = genere;
	}

	public Autore getAutore() {
		return autore;
	}

	public void setAutore(Autore autore) {
		this.autore = autore;
	}

	@Override
	public String toString() {
		return "Libro [ Titolo = " + titolo + ", trama = " + trama + ", genere = " + genere + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		if (autore == null) {
			if (other.autore != null)
				return false;
		} else if (!autore.equals(other.autore))
			return false;
		if (genere != other.genere)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (titolo == null) {
			if (other.titolo != null)
				return false;
		} else if (!titolo.equals(other.titolo))
			return false;
		if (trama == null) {
			if (other.trama != null)
				return false;
		} else if (!trama.equals(other.trama))
			return false;
		return true;
	}
	
}
