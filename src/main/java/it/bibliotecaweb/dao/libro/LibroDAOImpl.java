package it.bibliotecaweb.dao.libro;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import it.bibliotecaweb.model.libro.Libro;

public class LibroDAOImpl implements LibroDAO {

private EntityManager entityManager;
	
	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public Set<Libro> set() throws Exception {
		return entityManager.createQuery("SELECT DISTINCT l From Libro l JOIN FETCH l.autore a",Libro.class).getResultList().stream().collect(Collectors.toSet());
	}

	@Override
	public Libro get(Long id) throws Exception {
		if (id == null) {
			throw new Exception("Problema valore in input");
		}
		TypedQuery<Libro> query = entityManager.createQuery(
				"FROM Libro l JOIN FETCH l.autore a where l.id = ?1", Libro.class);
		query.setParameter(1, id);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public boolean update(Libro libro) throws Exception {
		boolean result = false;
		if (libro == null) {
			throw new Exception("Problema valore in input");
		} else {
		libro = entityManager.merge(libro);
		result = true;
		}
		return result;
	}

	@Override
	public boolean insert(Libro libro) throws Exception {
		boolean result = false;
		if (libro == null) {
			throw new Exception("Problema valore in input");
		} else {
		entityManager.persist(libro);
		result = true;
		}
		return result;
	}

	@Override
	public boolean delete(Libro libro) throws Exception {
		boolean result = false;
		if (libro == null) {
			throw new Exception("Problema valore in input");
		} else {
		entityManager.remove(entityManager.merge(libro));
		result = true;
		}
		return result;
	}

	@Override
	public Set<Libro> findLibro(Libro libro) throws Exception{
		String query1 = "FROM Libro l JOIN FETCH l.autore WHERE 1=1 ";
		if(libro.getTitolo() != null) {
			query1 = query1 + " AND l.titolo like :titolo ";
		}
		if(libro.getTrama() != null) {
			query1 = query1 + " AND l.trama like :trama ";
		}
		if(libro.getGenere() != null) {
			query1 = query1 + " AND l.genere = :genere";
		}
		if(libro.getAutore() != null) {
			query1 = query1 + " AND l.autore = :autore";
		}
		
		TypedQuery<Libro> query2 = entityManager.createQuery(query1, Libro.class);
		if(libro.getTitolo() != null) {
			query2.setParameter("titolo", '%'+libro.getTitolo()+'%');
		}
		if(libro.getTrama() != null) {
			query2.setParameter("trama", '%'+libro.getTrama()+'%');
		}
		if(libro.getGenere() != null) {
			query2.setParameter("genere", libro.getGenere());
		}
		if(libro.getAutore() != null) {
			query2.setParameter("autore", libro.getAutore());
		} 
		if(libro.equals(null)) {
			this.set().toString();
		}
		return query2.getResultList().stream().collect(Collectors.toSet());
	}

}
