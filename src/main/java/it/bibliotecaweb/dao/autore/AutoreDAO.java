package it.bibliotecaweb.dao.autore;

import it.bibliotecaweb.dao.IBaseDAO;
import it.bibliotecaweb.model.autore.Autore;

public interface AutoreDAO extends IBaseDAO<Autore>{

	public Autore findAutore(Autore autore) throws Exception;

}
