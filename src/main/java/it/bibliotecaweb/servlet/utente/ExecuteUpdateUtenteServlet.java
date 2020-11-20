package it.bibliotecaweb.servlet.utente;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.model.ruolo.Ruolo;
import it.bibliotecaweb.model.utente.StatoUtente;
import it.bibliotecaweb.model.utente.Utente;
import it.bibliotecaweb.service.MyServiceFactory;
import it.bibliotecaweb.service.utente.UtenteService;


@WebServlet("/utente/ExecuteUpdateUtenteServlet")
public class ExecuteUpdateUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ExecuteUpdateUtenteServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String idInputParam = request.getParameter("idUtentePerUpdate");
		String nomeInputParam = request.getParameter("nome");
		String cognomeInputParam = request.getParameter("cognome");
		String usernameInputParam = request.getParameter("username");
		String passwordInputParam = request.getParameter("password");
		String[] ruoliInputParam = request.getParameterValues("ruolo");
		
		
		if (nomeInputParam.isEmpty() || cognomeInputParam.isEmpty() || usernameInputParam.isEmpty()
				 || passwordInputParam.isEmpty() || ruoliInputParam == null) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione!");
			request.getRequestDispatcher("updateUtente.jsp").forward(request, response);
			return;
		}

		UtenteService serviceUtente = MyServiceFactory.getUtenteServiceInstance();
		Set<Ruolo> listaRuoli = new HashSet<Ruolo>();
		
		for(String r:ruoliInputParam){
			Long idRuolo = Long.parseLong(r);
			Ruolo ruoloNew = new Ruolo();
			ruoloNew.setId(idRuolo);
			listaRuoli.add(ruoloNew);
		}
		
		Utente utente = new Utente(nomeInputParam, cognomeInputParam, usernameInputParam, passwordInputParam, listaRuoli);
		
		try {
			utente.setId(Long.parseLong(idInputParam));
			serviceUtente.aggiorna(utente);
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		
		//palleggio i parametri passati per tornare alla lista dei Risultati della ricerca iniziale
		String nomeInputParamPerRicerca = request.getParameter("nomeUtentePerRicerca");
		String cognomeInputParamPerRicerca = request.getParameter("cognomeUtentePerRicerca");
		String usernameInputParamPerRicerca = request.getParameter("usernameUtentePerRicerca");
		String statoInputParamPerRicerca = request.getParameter("statoUtentePerRicerca");
//		String[] ruoliInputParamPerRicerca = request.getParameterValues("ruoliUtentePerRicerca");
//		
//		if(ruoliInputParamPerRicerca == null) {
//			ruoliInputParamPerRicerca = new String[0];
//		}

		String nomeRicerca = (nomeInputParamPerRicerca.isEmpty()) ? null : nomeInputParamPerRicerca;
		String cognomeRicerca = (cognomeInputParamPerRicerca.isEmpty()) ? null : cognomeInputParamPerRicerca;
		String usernameRicerca = (usernameInputParamPerRicerca.isEmpty()) ? null : usernameInputParamPerRicerca;
	
//			Set<Ruolo> listaRuoliRicerca = new HashSet<>();
//			for(String s : ruoliInputParamPerRicerca) { 
//				Long idRuolo = Long.parseLong(s);
//				RuoloService serviceRuolo = MyServiceFactory.getRuoloServiceInstance();
//				Ruolo ruoloNew = serviceRuolo.trova(idRuolo);
//				listaRuoliRicerca.add(ruoloNew);
//			}
			
//		Utente utenteNew = new Utente(nomeRicerca, cognomeRicerca, usernameRicerca, listaRuoliRicerca);
		
		//valorizzo l'utente con i parametri passati 
			Utente utenteNew = new Utente(nomeRicerca, cognomeRicerca, usernameRicerca);
		
				if (!statoInputParamPerRicerca.isEmpty() && statoInputParamPerRicerca != null) {
					utenteNew.setStato(StatoUtente.valueOf(statoInputParamPerRicerca));
				}
				
		//ricerco tutti gli utenti che corrispondono ai parametri passati e li passo alla jsp dei risultati 
		//questo mi permetterà di tornare alla lista filtrata 
			Set<Utente> listaUtenti = serviceUtente.trovaUtente(utenteNew);
			request.setAttribute("utentiPerResultsList", listaUtenti);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// andiamo ai risultati
		request.getRequestDispatcher("resultsListUtenti.jsp").forward(request, response);
	}

}
