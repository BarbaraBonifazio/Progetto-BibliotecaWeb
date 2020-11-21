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
import it.bibliotecaweb.service.ruolo.RuoloService;
import it.bibliotecaweb.service.utente.UtenteService;

@WebServlet("/utente/ExecuteFindUtentiServlet")
public class ExecuteFindUtentiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteFindUtentiServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nomeInputParam = request.getParameter("nome");
		String cognomeInputParam = request.getParameter("cognome");
		String usernameInputParam = request.getParameter("username");
		String statoInputParam = request.getParameter("stato");
		String[] ruoliInput = request.getParameterValues("ruolo");
		if(ruoliInput == null) {
			ruoliInput = new String[0];
		}
		
		String nome = (nomeInputParam.isEmpty()) ? null : nomeInputParam;
		String cognome = (cognomeInputParam.isEmpty()) ? null : cognomeInputParam;
		String username = (usernameInputParam.isEmpty()) ? null : usernameInputParam;
		UtenteService serviceUtente = MyServiceFactory.getUtenteServiceInstance();
		
		try {
			
			Set<Ruolo> listaRuoli = new HashSet<>();
			for(String s : ruoliInput) { //for(int i = 0; i < ruoliInput.length; i ++) >>> ruoliInput[i]
				Long idRuolo = Long.parseLong(s);
				RuoloService serviceRuolo = MyServiceFactory.getRuoloServiceInstance();
				Ruolo ruoloNew = serviceRuolo.trova(idRuolo);
				listaRuoli.add(ruoloNew);
			}
			
			Utente utenteNew = new Utente(nome, cognome, username, listaRuoli);
			
				if (!statoInputParam.isEmpty() && statoInputParam != null) {
					utenteNew.setStato(StatoUtente.valueOf(statoInputParam));
				}
			
			Set<Utente> listaUtenti = serviceUtente.trovaUtente(utenteNew);
			
			request.setAttribute("nomePerTornareAllaRicercaEffettuata", nomeInputParam);
			request.setAttribute("cognomePerTornareAllaRicercaEffettuata", cognomeInputParam);
			request.setAttribute("usernamePerTornareAllaRicercaEffettuata", usernameInputParam);
			request.setAttribute("statoPerTornareAllaRicercaEffettuata", statoInputParam);
//			request.setAttribute("ruoliPerTornareAllaRicercaEffettuata", utenteNew.getRuoli()); //Da aggiustare. Non mi passa questi parametri in pagina
			
			request.setAttribute("utentiPerResultsList", listaUtenti);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// andiamo ai risultati
		request.getRequestDispatcher("resultsListUtenti.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		

	}
}
