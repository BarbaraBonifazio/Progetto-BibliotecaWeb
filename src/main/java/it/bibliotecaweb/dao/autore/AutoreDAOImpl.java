package it.bibliotecaweb.dao.autore;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.bibliotecaweb.model.autore.Autore;

public class AutoreDAOImpl implements AutoreDAO{

private EntityManager entityManager;
	
	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public Set<Autore> set() throws Exception {
		return entityManager.createQuery("from Autore",Autore.class).getResultList().stream().collect(Collectors.toSet());
	}

	@Override
	public Autore get(Long id) throws Exception {
		if (id == null) {
			throw new Exception("Problema valore in input");
		}
		return entityManager.find(Autore.class, id);
	}

	@Override
	public boolean update(Autore autore) throws Exception {
		boolean result = false;
		if (autore == null) {
			throw new Exception("Problema valore in input");
		} else {
		autore = entityManager.merge(autore);
		result = true;
		}
		return result;
	} 

	@Override
	public boolean insert(Autore autore) throws Exception {
		boolean result = false;
		if (autore == null) {
			throw new Exception("Problema valore in input");
		} else {
		entityManager.persist(autore);
		result = true;
		}
		return result;
	}

	@Override
	public boolean delete(Autore autore) throws Exception {
		boolean result = false;
		if (autore == null) {
			throw new Exception("Problema valore in input");
		} else {
		entityManager.remove(entityManager.merge(autore));
		result = true;
		}
		return result;
	}

	@Override
	public Autore findAutore(Autore autore) throws Exception{
		String query1 = "FROM Autore a WHERE 1=1 ";
		if(autore.getNome() != null) {
			query1 = query1 + " AND a.nome like :nome ";
		}
		if(autore.getCognome() != null) {
			query1 = query1 + " AND a.cognome like :cognome ";
		}
		if(autore.getDataNascita() != null) {
			query1 = query1 + " AND a.dataNascita = :dataNascita";
		}
		
		TypedQuery<Autore> query2 = entityManager.createQuery(query1, Autore.class);
		if(autore.getNome() != null && !autore.getNome().isEmpty()) {
			query2.setParameter("nome", "%"+autore.getNome()+"%");
		}
		if(autore.getCognome() != null && !autore.getCognome().isEmpty()) {
			query2.setParameter("cognome", "%"+autore.getCognome()+"%");
		}
		if(autore.getDataNascita() != null) {
			query2.setParameter("dataNascita", autore.getDataNascita());
		}
		if(autore.equals(null)) {
			this.set();
		}
		return query2.getSingleResult();
	}
}
