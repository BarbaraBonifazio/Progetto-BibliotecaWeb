package it.bibliotecaweb.dao.utente;

import it.bibliotecaweb.dao.IBaseDAO;
import it.bibliotecaweb.model.utente.Utente;

public interface UtenteDAO extends IBaseDAO<Utente>{

	Utente findByUsernameAndPassword(Utente utente) throws Exception;
	

}
