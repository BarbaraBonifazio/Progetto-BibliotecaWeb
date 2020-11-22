package it.bibliotecaweb.dao.autore;

import java.util.Set;

import it.bibliotecaweb.dao.IBaseDAO;
import it.bibliotecaweb.model.autore.Autore;

public interface AutoreDAO extends IBaseDAO<Autore>{

	public Set<Autore> findAutori(Autore autore) throws Exception;

}
