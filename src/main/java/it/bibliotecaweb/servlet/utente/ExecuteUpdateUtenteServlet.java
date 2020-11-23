package it.bibliotecaweb.servlet.utente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
		String statoInputParam = request.getParameter("stato");
		String[] ruoliInputParam = request.getParameterValues("ruolo");
		if(ruoliInputParam == null) {
			ruoliInputParam = new String[0];
		}
		
		UtenteService serviceUtente = MyServiceFactory.getUtenteServiceInstance();
		Set<Ruolo> listaRuoli = new HashSet<Ruolo>();
		
		for(String r:ruoliInputParam){
			Long idRuolo = Long.parseLong(r);
			Ruolo ruoloNew = new Ruolo();
			ruoloNew.setId(idRuolo);
			listaRuoli.add(ruoloNew);
		}
		
		try {
			
			//controllo backend sulla presenza di dati inseriti 
			if (nomeInputParam.isEmpty() || cognomeInputParam.isEmpty() 
					|| usernameInputParam.isEmpty() || statoInputParam == "EMPTY"
					|| ruoliInputParam.length == 0) {

				List<String> errorMessage = new ArrayList<>();
				if(nomeInputParam.isEmpty()){
					errorMessage.add("Attenzione il campo nome è vuoto!");
				}
				if(cognomeInputParam.isEmpty()) {
					errorMessage.add("Attenzione il campo cognome è vuoto!");
				}
				if(usernameInputParam.isEmpty()) {
					errorMessage.add("Attenzione il campo username è vuoto!");
				}
				if(statoInputParam.isEmpty() || statoInputParam == null) {
					String errore4 = "Attenzione non risulta selezionato alcuno stato!";
					errorMessage.add(errore4);
				}
				if(ruoliInputParam.length == 0) {
					String errore5 = "Attenzione non risulta selezionato alcun ruolo!";
					errorMessage.add(errore5);
				} 
				
				Utente utenteErrato = new Utente(nomeInputParam, cognomeInputParam, usernameInputParam, listaRuoli);
				if (!statoInputParam.isEmpty() && statoInputParam != null) {
					utenteErrato.setStato(StatoUtente.valueOf(statoInputParam));
				}
				request.setAttribute("utentePerUpdate", utenteErrato);
				request.setAttribute("errorMessage", errorMessage);
				//parametri da passare alla jsp updateUtenteErrato per effettuare l'update dello stesso utente 
				utenteErrato.setId(Long.parseLong(idInputParam)); //assegno all'utente temporaneo l'id dell'utente che volevo inizialmente modificare
				//lista dei ruoli
				request.setAttribute("listRuoliAttribute", MyServiceFactory.getRuoloServiceInstance().setAll());
				//lista di enum per lo stato dell'utente 
				List<String> listaStati = Stream.of(StatoUtente.values()).map(Enum::name).collect(Collectors.toList());
				request.setAttribute("listaStati", listaStati);
				request.getRequestDispatcher("updateUtente.jsp").forward(request, response);
				return;
			}
				// --- fine controllo back end --- 

			//richiamo l'utente da DB e imposto eventuali parametri passati dalla pagina per l'update 
			Utente utenteDaDb  =  serviceUtente.trova(Long.parseLong(idInputParam));
			utenteDaDb.setNome(nomeInputParam);
			utenteDaDb.setCognome(cognomeInputParam);
			utenteDaDb.setUsername(usernameInputParam);
			utenteDaDb.setRuoli(listaRuoli);
		
		if (!statoInputParam.isEmpty() && statoInputParam != null) {
			utenteDaDb.setStato(StatoUtente.valueOf(statoInputParam));
		}
	
			//aggiorno l'utente con i nuovi parametri 
			serviceUtente.aggiorna(utenteDaDb);
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
			
		//passo i parametri per tornare alla ricerca effettuata alla jsp successiva 
				request.setAttribute("nomePerTornareAllaRicercaEffettuata", nomeInputParamPerRicerca);
				request.setAttribute("cognomePerTornareAllaRicercaEffettuata", nomeInputParamPerRicerca);
				request.setAttribute("usernamePerTornareAllaRicercaEffettuata", nomeInputParamPerRicerca);
				request.setAttribute("statoPerTornareAllaRicercaEffettuata", nomeInputParamPerRicerca);
//				request.setAttribute("ruoliPerTornareAllaRicercaEffettuata", ruoliInputParamPerRicerca);
		
		//valorizzo l'utente d'appoggio con i parametri passati nella ricerca 
			Utente utenteNew = new Utente(nomeRicerca, cognomeRicerca, usernameRicerca);
		
				if (!statoInputParamPerRicerca.isEmpty() && statoInputParamPerRicerca != null) {
					utenteNew.setStato(StatoUtente.valueOf(statoInputParamPerRicerca));
				}

		//ricerco tutti gli utenti che corrispondono ai parametri passati e li rimando alla jsp dei risultati 
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
