package it.bibliotecaweb.service.autore;

import java.util.Set;

import javax.persistence.EntityManager;

import it.bibliotecaweb.EntityManagerUtil;
import it.bibliotecaweb.dao.autore.AutoreDAO;
import it.bibliotecaweb.model.autore.Autore;

public class AutoreServiceImpl implements AutoreService {

	private AutoreDAO autoreDAO;

	@Override
	public void setAutoreDAO(AutoreDAO autoreDAO) {
		this.autoreDAO = autoreDAO;
	}

	@Override
	public Set<Autore> setAll() throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			autoreDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return autoreDAO.set();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public Autore trova(Long id) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			autoreDAO.setEntityManager(entityManager);
			// eseguo quello che realmente devo fare
			return autoreDAO.get(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public boolean aggiorna(Autore autoreInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		boolean result = false;
		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();
			// uso l'injection per il dao
			autoreDAO.setEntityManager(entityManager);
			// eseguo quello che realmente devo fare
			result = autoreDAO.update(autoreInstance);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public boolean inserisciNuovo(Autore autoreInstance) throws Exception {

		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		boolean result = false;
		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();
			// uso l'injection per il dao
			autoreDAO.setEntityManager(entityManager);
				// eseguo quello che realmente devo fare
				result = autoreDAO.insert(autoreInstance);
				entityManager.getTransaction().commit();

		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
		return result;
		
	}

	@Override
	public boolean rimuovi(Autore autoreInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		boolean result = false;
		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			autoreDAO.setEntityManager(entityManager);
			Autore autore = autoreDAO.get(autoreInstance.getId());
			if (autore.getLibri().size() == 0) {
				// eseguo quello che realmente devo fare
				result = autoreDAO.delete(autore);
				entityManager.getTransaction().commit();
			}
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
	@Override
	public Set<Autore> trovaAutori(Autore autoreInstance) throws Exception{
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			autoreDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return autoreDAO.findAutori(autoreInstance);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

}
