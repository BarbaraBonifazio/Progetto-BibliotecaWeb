package it.bibliotecaweb.service.utente;

import java.util.Set;

import javax.persistence.EntityManager;

import it.bibliotecaweb.EntityManagerUtil;
import it.bibliotecaweb.dao.utente.UtenteDAO;
import it.bibliotecaweb.model.ruolo.Ruolo;
import it.bibliotecaweb.model.utente.StatoUtente;
import it.bibliotecaweb.model.utente.Utente;

public class UtenteServiceImpl implements UtenteService {

	private UtenteDAO utenteDAO;

	@Override
	public void setUtenteDAO(UtenteDAO utenteDAO) {
		this.utenteDAO = utenteDAO;
	}

	@Override
	public Set<Utente> setAll() throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);
			// eseguo quello che realmente devo fare
			return utenteDAO.set();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public Utente trova(Long id) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);
			
			// eseguo quello che realmente devo fare
			return utenteDAO.get(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public boolean aggiorna(Utente utenteInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		boolean result = false;
		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);
			if (utenteInstance.getRuoli().size() > 0) {
				// eseguo quello che realmente devo fare
				result = utenteDAO.update(utenteInstance);
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
	public boolean inserisciNuovo(Utente utenteInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		boolean result = false;
		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);
			utenteInstance.setStato(StatoUtente.ATTIVO);
			if (utenteInstance.getRuoli().size() > 0) {
				
				// eseguo quello che realmente devo fare
				result = utenteDAO.insert(utenteInstance);
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
	public boolean rimuovi(Utente utenteInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		boolean result = false;
		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);
			
			// eseguo quello che realmente devo fare
			utenteDAO.delete(utenteInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public boolean aggiungiRuolo(Utente utenteEsistente, Ruolo ruoloEsistente) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		boolean result = false;
		try {

			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();
			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);
			boolean utenteAggiornato = this.aggiorna(utenteEsistente); // verifico che l'utente sia aggiornato
																			// con il relativo controllo di update
			if (utenteAggiornato) {
				utenteEsistente = entityManager.merge(utenteEsistente);
				ruoloEsistente = entityManager.merge(ruoloEsistente);
				utenteEsistente.getRuoli().add(ruoloEsistente);
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
	public boolean rimuoviRuolo(Utente utenteEsistente, Ruolo ruoloEsistente) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		boolean result = false;
		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);

			boolean utenteAggiornato = this.aggiorna(utenteEsistente); // verifico che il l'utente sia aggiornato
																		// con il relativo controllo di update
			if (utenteAggiornato) {
			utenteEsistente = entityManager.merge(utenteEsistente);
			ruoloEsistente = entityManager.merge(ruoloEsistente);
			utenteEsistente.getRuoli().remove(ruoloEsistente);
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
	public Utente trovaDaUsernameEPassword(Utente utente) throws Exception{
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);
			// eseguo quello che realmente devo fare
			return utenteDAO.findByUsernameAndPassword(utente);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}
	
	@Override
	public Set<Utente> trovaUtente(Utente utente) throws Exception{
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);
			// eseguo quello che realmente devo fare
			return utenteDAO.findUtente(utente);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}
}
