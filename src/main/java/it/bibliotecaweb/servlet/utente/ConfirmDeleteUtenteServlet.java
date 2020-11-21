package it.bibliotecaweb.servlet.utente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.model.utente.Utente;
import it.bibliotecaweb.service.MyServiceFactory;
import it.bibliotecaweb.service.utente.UtenteService;



@WebServlet("/utente/ConfirmDeleteUtenteServlet")
public class ConfirmDeleteUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ConfirmDeleteUtenteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String parametroIdDellUtenteCheVoglioEliminare = request.getParameter("idDaInviareAExecuteDelete");
		
		
		try {
				// Valido eventuale parametro passato da URL 
				if (parametroIdDellUtenteCheVoglioEliminare.isEmpty()) {
					request.setAttribute("errorMessage", "Attenzione non è stato inserito alcun valore!");
					request.setAttribute("utentiPerResultsList", MyServiceFactory.getUtenteServiceInstance().setAll());
					request.getRequestDispatcher("resultsListUtenti.jsp").forward(request, response);
					return;
				}
				// --fine validazione parametro da url--
		
		UtenteService service = MyServiceFactory.getUtenteServiceInstance();		
				
			Utente utenteInstance = service.trova(Long.parseLong(parametroIdDellUtenteCheVoglioEliminare));
			//passo l'utente da DB alla jsp
			request.setAttribute("utenteDaEliminare", utenteInstance);
			
			// Verifico reale esistenza del parametro passato da URL nel DB
			if (utenteInstance == null) {
				request.setAttribute("errorMessage", "Attenzione il valore inserito non esiste!");
				request.getRequestDispatcher("listUtenti.jsp").forward(request, response);
				return;
			}
			// --fine verifica parametro DB
			
		
			//palleggio i parametri passati per tornare alla lista dei Risultati della ricerca iniziale
			String nomePerTornareAllaRicercaEffettuata = request.getParameter("nomePerTornareAllaRicercaEffettuata");
			String cognomePerTornareAllaRicercaEffettuata = request.getParameter("cognomePerTornareAllaRicercaEffettuata");
			String usernamePerTornareAllaRicercaEffettuata = request.getParameter("usernamePerTornareAllaRicercaEffettuata");
			String statoPerTornareAllaRicercaEffettuata = request.getParameter("statoPerTornareAllaRicercaEffettuata");
			String ruoliPerTornareAllaRicercaEffettuata = request.getParameter("ruoliPerTornareAllaRicercaEffettuata");
			
			//passo i parametri alla jsp successiva, per tornare eventualmente alla ricerca effettuata
			request.setAttribute("nomePerTornareAllaRicercaEffettuata", nomePerTornareAllaRicercaEffettuata);
			request.setAttribute("cognomePerTornareAllaRicercaEffettuata", cognomePerTornareAllaRicercaEffettuata);
			request.setAttribute("usernamePerTornareAllaRicercaEffettuata", usernamePerTornareAllaRicercaEffettuata);
			request.setAttribute("statoPerTornareAllaRicercaEffettuata", statoPerTornareAllaRicercaEffettuata);
			request.setAttribute("ruoliPerTornareAllaRicercaEffettuata", ruoliPerTornareAllaRicercaEffettuata);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
				request.getRequestDispatcher("confirmDeleteUtente.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
