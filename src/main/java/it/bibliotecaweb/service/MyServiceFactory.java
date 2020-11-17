package it.bibliotecaweb.service;

import it.bibliotecaweb.dao.MyDAOFactory;
import it.bibliotecaweb.service.autore.AutoreService;
import it.bibliotecaweb.service.autore.AutoreServiceImpl;
import it.bibliotecaweb.service.libro.LibroService;
import it.bibliotecaweb.service.libro.LibroServiceImpl;
import it.bibliotecaweb.service.ruolo.RuoloService;
import it.bibliotecaweb.service.ruolo.RuoloServiceImpl;
import it.bibliotecaweb.service.utente.UtenteService;
import it.bibliotecaweb.service.utente.UtenteServiceImpl;

public class MyServiceFactory {

	// rendiamo questo factory SINGLETON
	private static UtenteService UTENTE_SERVICE_INSTANCE;
	private static RuoloService RUOLO_SERVICE_INSTANCE;
	private static AutoreService AUTORE_SERVICE_INSTANCE;
	private static LibroService LIBRO_SERVICE_INSTANCE;

	public static RuoloService getRuoloServiceInstance() {
		if (RUOLO_SERVICE_INSTANCE == null)
			RUOLO_SERVICE_INSTANCE = new RuoloServiceImpl();

		RUOLO_SERVICE_INSTANCE.setRuoloDAO(MyDAOFactory.getRuoloDAOInstance());
		return RUOLO_SERVICE_INSTANCE;
	}
	
	public static UtenteService getUtenteServiceInstance() {
		if (UTENTE_SERVICE_INSTANCE == null)
			UTENTE_SERVICE_INSTANCE = new UtenteServiceImpl();

		UTENTE_SERVICE_INSTANCE.setUtenteDAO(MyDAOFactory.getUtenteDAOInstance());
		return UTENTE_SERVICE_INSTANCE;
	}
	
	public static AutoreService getAutoreServiceInstance() {
		if (AUTORE_SERVICE_INSTANCE == null)
			AUTORE_SERVICE_INSTANCE = new AutoreServiceImpl();

		AUTORE_SERVICE_INSTANCE.setAutoreDAO(MyDAOFactory.getAutoreDAOInstance());
		return AUTORE_SERVICE_INSTANCE;
	}
	
	public static LibroService getLibroServiceInstance() {
		if (LIBRO_SERVICE_INSTANCE == null)
			LIBRO_SERVICE_INSTANCE = new LibroServiceImpl();

		LIBRO_SERVICE_INSTANCE.setLibroDAO(MyDAOFactory.getLibroDAOInstance());
		return LIBRO_SERVICE_INSTANCE;
	}

}
