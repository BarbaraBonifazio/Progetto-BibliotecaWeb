package it.bibliotecaweb.dao.ruolo;

import javax.persistence.EntityManager;

import it.bibliotecaweb.dao.IBaseDAO;
import it.bibliotecaweb.model.ruolo.Ruolo;

public interface RuoloDAO extends IBaseDAO<Ruolo>{
	
	public void setEntityManager(EntityManager entityManager);

	Ruolo findByDescrizione(Ruolo ruolo) throws Exception;

}
