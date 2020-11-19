package it.bibliotecaweb.dao.utente;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import it.bibliotecaweb.model.utente.Utente;

public class UtenteDAOImpl implements UtenteDAO {

	private EntityManager entityManager;

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Set<Utente> set() throws Exception {
		return entityManager.createQuery("From Utente u", Utente.class).getResultList().stream()
				.collect(Collectors.toSet());
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
	public Utente findByUsernameAndPassword(Utente utente) throws Exception {
		TypedQuery<Utente> query = entityManager.createQuery(
				"SELECT DISTINCT u from Utente u JOIN FETCH u.ruoli r where u.username like ?1 AND u.password like ?2",
				Utente.class);
		query.setParameter(1, "%" + utente.getUsername() + "%");
		query.setParameter(2, "%" + utente.getPassword() + "%");
		
		
		

		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Set<Utente> findUtente(Utente utente) throws Exception {
		String query1 = "FROM Utente u JOIN FETCH u.ruoli r WHERE 1=1 ";
		if (utente.getNome() != null) {
			query1 = query1 + " AND u.nome like :nome ";
		}
		if (utente.getCognome() != null) {
			query1 = query1 + " AND u.cognome like :cognome ";
		}
		if (utente.getUsername() != null) {
			query1 = query1 + " AND u.username like :username";
		}
		if (utente.getStato() != null) {
			query1 = query1 + " AND u.stato = :stato";
		}
		if (utente.getRuoli() != null && !utente.getRuoli().isEmpty()) {
			query1 = query1 + " AND r in (:ruoli)";
		}

		TypedQuery<Utente> query2 = entityManager.createQuery(query1, Utente.class);
		if (utente.getNome() != null) {
			query2.setParameter("nome", '%' + utente.getNome() + '%');
		}
		if (utente.getCognome() != null) {
			query2.setParameter("cognome", '%' + utente.getCognome() + '%');
		}
		if (utente.getUsername() != null) {
			query2.setParameter("username", '%' + utente.getUsername() + '%');
		}
		if (utente.getStato() != null) {
			query2.setParameter("stato", utente.getStato());
		}
		if (utente.getRuoli() != null && !utente.getRuoli().isEmpty()) {
			query2.setParameter("ruoli", utente.getRuoli());
		}
		if(utente.equals(null)) {
			this.set().toString();
		}
		return query2.getResultList().stream().collect(Collectors.toSet());
	}
}
