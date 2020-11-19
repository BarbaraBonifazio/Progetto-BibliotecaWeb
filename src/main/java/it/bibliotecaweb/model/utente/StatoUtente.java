package it.bibliotecaweb.model.utente;

public enum StatoUtente {

	ATTIVO, NON_ATTIVO, EMPTY;
	
	@Override
	public String toString() {
		return this == EMPTY ? "" : this.name();
	}
}
