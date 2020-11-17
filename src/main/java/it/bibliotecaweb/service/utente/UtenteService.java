package it.bibliotecaweb.service.utente;

import java.util.Set;

import it.bibliotecaweb.dao.utente.UtenteDAO;
import it.bibliotecaweb.model.ruolo.Ruolo;
import it.bibliotecaweb.model.utente.Utente;

public interface UtenteService {

	void setUtenteDAO(UtenteDAO utenteDAO);
	
	public Set<Utente> setAll() throws Exception;

	public Utente trova(Long id) throws Exception;

	public boolean aggiorna(Utente utenteInstance) throws Exception;

	public boolean inserisciNuovo(Utente utenteInstance) throws Exception;

	public boolean rimuovi(Utente utenteInstance) throws Exception;

	boolean aggiungiRuolo(Utente utenteEsistente, Ruolo ruoloEsistente) throws Exception;

	boolean rimuoviRuolo(Utente utenteEsistente, Ruolo ruoloEsistente) throws Exception;

	Utente trovaDaUsernameEPassword(Utente utente) throws Exception;

}
