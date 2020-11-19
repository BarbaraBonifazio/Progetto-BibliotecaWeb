package it.bibliotecaweb.dao.utente;

import java.util.Set;

import it.bibliotecaweb.dao.IBaseDAO;
import it.bibliotecaweb.model.utente.Utente;

public interface UtenteDAO extends IBaseDAO<Utente>{

	public Utente findByUsernameAndPassword(Utente utente) throws Exception;

	public Set<Utente> findUtente(Utente utente) throws Exception;
	

}
