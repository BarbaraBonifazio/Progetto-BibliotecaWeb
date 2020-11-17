package it.bibliotecaweb.service.autore;

import java.util.Set;

import it.bibliotecaweb.dao.autore.AutoreDAO;
import it.bibliotecaweb.model.autore.Autore;

public interface AutoreService {
	
	//per injection
	void setAutoreDAO(AutoreDAO autoreDAO);
	
	public Set<Autore> setAll() throws Exception;

	public Autore trova(Long id) throws Exception;

	public boolean aggiorna(Autore autoreInstance) throws Exception;

	public boolean inserisciNuovo(Autore autoreInstance) throws Exception;

	public boolean rimuovi(Autore autoreInstance) throws Exception;

	public Autore trovaAutore(Autore autoreInstance) throws Exception;

}
