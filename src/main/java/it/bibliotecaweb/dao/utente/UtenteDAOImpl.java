package it.bibliotecaweb.dao.utente;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.bibliotecaweb.model.utente.Utente;

public class UtenteDAOImpl implements UtenteDAO{

private EntityManager entityManager;
	
	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}	
	
	@Override
	public Set<Utente> set() throws Exception {
		return entityManager.createQuery("from Utente",Utente.class).getResultList().stream().collect(Collectors.toSet());
	}

	@Override
	public Utente get(Long id) throws Exception {
		if (id == null) {
			throw new Exception("Problema valore in input");
		}
		return entityManager.find(Utente.class, id);
	}

	@Override
	public boolean update(Utente utente) throws Exception {
		boolean result = false;
		if (utente == null) {
			throw new Exception("Problema valore in input");
		} else {
		utente = entityManager.merge(utente);
		result = true;
		}
		return result;
	}

	@Override
	public boolean insert(Utente utente) throws Exception {
		boolean result = false;
		if (utente == null) {
			throw new Exception("Problema valore in input");
		} else {
		entityManager.persist(utente);
		result = true;
		}
		return result;
	}

	@Override
	public boolean delete(Utente utente) throws Exception {
		boolean result = false;
		if (utente == null) {
			throw new Exception("Problema valore in input");
		} else {
		entityManager.remove(entityManager.merge(utente));
		result = true;
		}
		return result;
	}

	@Override
	public Utente findByUsernameAndPassword(Utente utente) throws Exception{
		TypedQuery<Utente> query = entityManager
				.createQuery("SELECT DISTINCT u from Utente u JOIN FETCH u.ruoli r where u.username like ?1 AND u.password like ?2", Utente.class);
				query.setParameter(1, "%"+utente.getUsername()+"%");
				query.setParameter(2, "%"+utente.getPassword()+"%");
		
		return query.getSingleResult();
	}
}


