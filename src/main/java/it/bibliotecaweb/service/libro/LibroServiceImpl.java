package it.bibliotecaweb.service.libro;

import java.util.Set;

import javax.persistence.EntityManager;

import it.bibliotecaweb.EntityManagerUtil;
import it.bibliotecaweb.dao.libro.LibroDAO;
import it.bibliotecaweb.model.autore.Autore;
import it.bibliotecaweb.model.libro.Libro;

public class LibroServiceImpl implements LibroService {

	private LibroDAO libroDAO;

	@Override
	public void setLibroDAO(LibroDAO libroDAO) {
		this.libroDAO = libroDAO;
	}

	@Override
	public Set<Libro> setAll() throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			libroDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return libroDAO.set();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public Libro trova(Long id) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			libroDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return libroDAO.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public boolean aggiorna(Libro libroInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		boolean result = false;
		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			libroDAO.setEntityManager(entityManager);
			if (libroInstance.getAutore().getId() != null) { // se il libro ha un autore associato allora posso eseguire
																// l'update
				// eseguo quello che realmente devo fare
				result = libroDAO.update(libroInstance);
				entityManager.getTransaction().commit();
			}
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
		return result; // altrimenti entra in eccezione, fa il rollback e ritorna false
	}

	@Override
	public boolean inserisciNuovo(Libro libroInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		boolean result = false;
		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			libroDAO.setEntityManager(entityManager);
			if (libroInstance.getAutore().getId()!= null) { // se il libro ha un autore associato allora posso eseguire
																// l'insert
				// eseguo quello che realmente devo fare
				libroDAO.insert(libroInstance);
				entityManager.getTransaction().commit();
				result = true;
				System.out.println("\n\nLibro correttamente inserito!\n\n");
			}
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			System.out.println("\n\nNON PUOI INSERIRE QUESTO LIBRO! NON E' STATO ASSOCIATO AD ALCUN AUTORE!\n\n");
			throw e;
		}
		return result; // altrimenti entra in eccezione, fa il rollback e ritorna false
	}

	@Override
	public boolean rimuovi(Libro libroInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		boolean result = false;
		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			libroDAO.setEntityManager(entityManager);
			// eseguo quello che realmente devo fare
			result = libroDAO.delete(libroInstance);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public boolean aggiungiAutore(Libro libroEsistente, Autore autoreEsistente) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		boolean result = false;
		try {

			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();
			// uso l'injection per il dao
			libroDAO.setEntityManager(entityManager);
			boolean libroAggiornato = this.aggiorna(libroEsistente); // verifico che il libro sia prima aggiornato (con
																		// il relativo controllo di update)
			if (libroAggiornato) { // se il controllo mi ritorna true, faccio il merge di entrambi gli oggetti
				libroEsistente = entityManager.merge(libroEsistente);
				autoreEsistente = entityManager.merge(autoreEsistente);
				autoreEsistente.getLibri().add(libroEsistente); // aggiungo l'autore al libro
				entityManager.getTransaction().commit();
				result = true;
			}
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
	@Override
	public Set<Libro> trovaLibro(Libro libroInstance) throws Exception{
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			libroDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return libroDAO.findLibro(libroInstance);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

}
