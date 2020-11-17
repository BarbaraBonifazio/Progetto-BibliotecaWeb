package it.bibliotecaweb.service.ruolo;

import java.util.Set;

import it.bibliotecaweb.dao.ruolo.RuoloDAO;
import it.bibliotecaweb.model.ruolo.Ruolo;

public interface RuoloService {

	void setRuoloDAO(RuoloDAO ruoloDAO);

	public Ruolo trova(Long id) throws Exception;
	
	public Set<Ruolo> setAll() throws Exception;

	public boolean aggiorna(Ruolo ruoloInstance) throws Exception;

	public boolean inserisciNuovo(Ruolo ruoloInstance) throws Exception;

	public boolean rimuovi(Ruolo ruoloInstance) throws Exception;
	
	Ruolo trovaDaDescrizione(Ruolo ruolo) throws Exception;
}
