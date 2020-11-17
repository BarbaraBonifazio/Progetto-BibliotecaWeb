package it.bibliotecaweb.test;

import java.time.LocalDate;

import it.bibliotecaweb.EntityManagerUtil;
import it.bibliotecaweb.model.autore.Autore;
import it.bibliotecaweb.model.libro.Genere;
import it.bibliotecaweb.model.libro.Libro;
import it.bibliotecaweb.model.ruolo.Codice;
import it.bibliotecaweb.model.ruolo.Ruolo;
import it.bibliotecaweb.model.utente.Utente;
import it.bibliotecaweb.service.MyServiceFactory;
import it.bibliotecaweb.service.autore.AutoreService;
import it.bibliotecaweb.service.libro.LibroService;
import it.bibliotecaweb.service.ruolo.RuoloService;
import it.bibliotecaweb.service.utente.UtenteService;

public class BibliotecaWebTest {

	public static void main(String[] args) {
		
		try {
			
//			LocalDate locald = LocalDate.of(1904, 07, 12);
//			Date date = Date.valueOf(locald); // Magic happens here!
//			r.setDateOfBirth(date);
			
			AutoreService autoreServiceInstance = MyServiceFactory.getAutoreServiceInstance();
			LibroService libroServiceInstance = MyServiceFactory.getLibroServiceInstance();
			UtenteService utenteServiceInstance = MyServiceFactory.getUtenteServiceInstance();
			RuoloService ruoloServiceInstance = MyServiceFactory.getRuoloServiceInstance();
						
			LocalDate data;
			
			//Pablo NERUDA = 12 luglio 1904
			Autore autore = new Autore("Federico", "Garcia Lorca", LocalDate.of(1936, 8, 18+1));

			Libro libro1 = new Libro("Aspettiamo cinque anni", "Il Giovane protagonista ama la Fidanzata, " 
					+ " ma vuole sposarla dopo cinque anni, senza motivare la sua scelta.", Genere.OPERA_TEATRALE);
			
			//verifico l'insert con i relativi controlli di AUTORE --- VERIFICA OK 
//			Set<Libro> listaLibri = new HashSet<>();
//			listaLibri.add(libro1);
//			autore.setLibri(listaLibri); //se non setto prima i libri all'autore, non permette l'inserimento! 
//			autoreServiceInstance.inserisciNuovo(autore);
			
			//verifico l'insert con i relativi controlli di LIBRO --- VERIFICA OK 
			Autore autoreDaDb1 = autoreServiceInstance.trova(1L);
				
			//verifico l'UPDATE di LIBRO 
//			libro1.setAutore(autoreDaDb1);
//			libroServiceInstance.inserisciNuovo(libro1);
			
			//verifico l'update di Autore 
			Libro libroDaDb1 = libroServiceInstance.trova(2L);

//			libroServiceInstance.aggiungiAutore(libroDaDb1, autoreDaDb1);
//			libro1.setGenere(Genere.BIOGRAFIA);
//			libroServiceInstance.aggiorna(libro1);
			
			//controllo la stampa del libro comprensiva della colonna autore 
//			Set<Libro> l = libroServiceInstance.setAll();
//			System.out.println(l);
//			for(Libro l1:l)
//			System.out.println(l1);
			
			Utente utente = new Utente("Barbara", "Bonifazio", "Barbara92", "111");
			Ruolo ruolo1 = new Ruolo("Amministratore", Codice.ADMIN_ROLE);
			Ruolo ruolo2 = new Ruolo("Utente", Codice.CLASSIC_ROLE);
			Ruolo ruolo3 = new Ruolo("Ospite", Codice.GUEST_ROLE);
			
//			Ruolo ruoloDaDb1 = ruoloServiceInstance.trovaDaDescrizione(ruolo1);
//			Set<Ruolo> listaRuoli1 = new HashSet<>();
//			listaRuoli1.add(ruoloDaDb1);
//			utente.setRuoli(listaRuoli1); //setto il ruolo 
//			utenteServiceInstance.inserisciNuovo(utente);
		
//			Utente utenteDaDB1 = utenteServiceInstance.trovaDaUsernameEPassword(utente);
//			System.out.println(utenteDaDB1);
			
			//test metodo "trovaLibro" (findByExample)
			Libro l1 = new Libro(null, null, Genere.OPERA_TEATRALE); //ricerca libro per genere > OK
			Libro l2 = new Libro("Aspettiamo", null, null); //ricerca libro per titolo - OK
			Libro l3 = new Libro(null, null, null, autoreDaDb1); //ricerca libro per autore > OK
			Libro l4 = new Libro(null, "Il Giovane protagonista ama la fidanzata", null); //ricerca libro per trama - OK
			Libro l5 = new Libro(); //ricerca libro vuoto - restituisce tutta la lista di libri
//			Set<Libro> l6= libroServiceInstance.trovaLibro(l2);
//			System.out.println(l6);
			
			//test metodo "trovaAutore"(findByExample)
			Autore a1 = new Autore(); //ricerca autore vuoto - ok (Restituisce tutti gli autori)
			Autore a2 =  new Autore("Federi", null, null); //ricerca autore per nome parziale - ok
			Autore a3 = new Autore(null, "rc", null ); //ricerca autore per cognome parziale - ok
			Autore a4 = new Autore(null, null, LocalDate.of(1936, 8, 18+1)); //ricerca autore per data di nascita - ok
//			Autore a5 = autoreServiceInstance.trovaAutore(a3);
//			System.out.println(a5);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// questa è necessaria per chiudere tutte le connessioni quindi rilasciare il
			// main
			EntityManagerUtil.shutdown();
		}
	}

}


