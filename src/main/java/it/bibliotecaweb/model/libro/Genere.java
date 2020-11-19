package it.bibliotecaweb.model.libro;

public enum Genere {
	
	THRILLER, FANTASCIENZA, ROMANZO, HORROR, BIOGRAFIA, TRATTATO, SAGGIO, FANTASY, OPERA_TEATRALE, EMPTY;
	
	@Override
	public String toString() {
		return this == EMPTY ? "" : this.name();
	}
}
