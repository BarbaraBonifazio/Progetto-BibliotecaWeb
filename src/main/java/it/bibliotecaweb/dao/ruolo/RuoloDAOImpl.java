package it.bibliotecaweb.dao.ruolo;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.bibliotecaweb.model.ruolo.Ruolo;

public class RuoloDAOImpl implements RuoloDAO {

	private EntityManager entityManager;

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Set<Ruolo> set() throws Exception {
		return entityManager.createQuery("From Ruolo r", Ruolo.class).getResultList().stream()
				.collect(Collectors.toSet());
	}

	@Override
	public Ruolo get(Long id) throws Exception {
		if (id == null) {
			throw new Exception("Problema valore in input");
		}
		return entityManager.find(Ruolo.class, id);
	}

	@Override
	public boolean update(Ruolo ruolo) throws Exception {
		boolean result = false;
		if (ruolo == null) {
			throw new Exception("Problema valore in input");
		} else {
			ruolo = entityManager.merge(ruolo);
			result = true;
		}
		return result;
	}

	@Override
	public boolean insert(Ruolo ruolo) throws Exception {
		boolean result = false;
		if (ruolo == null) {
			throw new Exception("Problema valore in input");
		} else {
			entityManager.persist(ruolo);
			result = true;
		}
		return result;
	}

	@Override
	public boolean delete(Ruolo ruolo) throws Exception {
		boolean result = false;
		if (ruolo == null) {
			throw new Exception("Problema valore in input");
		} else {
			entityManager.remove(entityManager.merge(ruolo));
			result = true;
		}
		return result;
	}
	
	@Override
	public Ruolo findByDescrizione(Ruolo ruolo) throws Exception{
		TypedQuery<Ruolo> query = entityManager
				.createQuery("select r from Ruolo r where r.descrizione like ?1", Ruolo.class)
				.setParameter(1, "%"+ruolo.getDescrizione()+"%");
		
		return query.getResultStream().findFirst().orElse(null);
	}

}
