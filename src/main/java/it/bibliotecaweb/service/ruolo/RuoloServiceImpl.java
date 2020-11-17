package it.bibliotecaweb.service.ruolo;

import java.util.Set;

import javax.persistence.EntityManager;

import it.bibliotecaweb.EntityManagerUtil;
import it.bibliotecaweb.dao.ruolo.RuoloDAO;
import it.bibliotecaweb.model.ruolo.Ruolo;

public class RuoloServiceImpl implements RuoloService {

private RuoloDAO ruoloDAO;	

	@Override
	public void setRuoloDAO(RuoloDAO ruoloDAO) {
		this.ruoloDAO = ruoloDAO;
	}

	@Override
	public Ruolo trova(Long id) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			ruoloDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return ruoloDAO.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public Set<Ruolo> setAll() throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			ruoloDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return ruoloDAO.set();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}	
	}
	
	@Override
	public Ruolo trovaDaDescrizione(Ruolo ruolo) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			ruoloDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return ruoloDAO.findByDescrizione(ruolo);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}	
	}

	@Override
	public boolean aggiorna(Ruolo ruoloInstance) throws Exception {
		throw new Exception("Non puoi aggiornare un ruolo!");
	}

	@Override
	public boolean inserisciNuovo(Ruolo ruoloInstance) throws Exception {
		throw new Exception("Non puoi inserire un nuovo ruolo!");
	}

	@Override
	public boolean rimuovi(Ruolo ruoloInstance) throws Exception {
		throw new Exception("Non puoi rimuovere un ruolo!");
	}

	
	
	
}
