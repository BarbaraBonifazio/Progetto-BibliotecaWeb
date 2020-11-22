package it.bibliotecaweb.servlet.utente;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.model.utente.StatoUtente;
import it.bibliotecaweb.model.utente.Utente;
import it.bibliotecaweb.service.MyServiceFactory;
import it.bibliotecaweb.service.utente.UtenteService;


@WebServlet("/utente/PrepareUpdateUtenteServlet")
public class PrepareUpdateUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PrepareUpdateUtenteServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idUtenteDaModificareInput = request.getParameter("idDaInviareAExecuteUpdate");

		// Valido eventuale parametro passato da url
		if (idUtenteDaModificareInput.isEmpty() || idUtenteDaModificareInput == null) {
			request.setAttribute("errorMessage", "Attenzione il valore inserito non è valido!");
			request.getRequestDispatcher("home.jsp").forward(request, response);
			return;
		
		}
		// --fine validazione parametro da url--
		
		try {
			request.setAttribute("listRuoliAttribute", MyServiceFactory.getRuoloServiceInstance().setAll());
			//lista di enum per lo stato dell'utente 
			List<String> listaStati = Stream.of(StatoUtente.values()).map(Enum::name).collect(Collectors.toList());
			request.setAttribute("listaStati", listaStati);
			
		
			UtenteService serviceUtente = MyServiceFactory.getUtenteServiceInstance();

			//parametri da passare alla jsp per effettuare l'update dell'utente 
			Utente utenteInstance = serviceUtente.trova(Long.parseLong(idUtenteDaModificareInput));
			

			request.setAttribute("utentePerUpdate", utenteInstance);
			request.setAttribute("listRuoliAttribute", MyServiceFactory.getRuoloServiceInstance().setAll());
			
			// Verifico reale esistenza del parametro nel DB
			if (utenteInstance == null) {
				request.setAttribute("errorMessage", "Attenzione il valore inserito non esiste!");
				request.getRequestDispatcher("home.jsp").forward(request, response);
				return;
			}
			// --fine verifica parametro DB
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//prendo i parametri per tornare alla ricerca effettuata 
		String nomePerTornareAllaRicercaEffettuata = request.getParameter("nomePerTornareAllaRicercaEffettuata");
		String cognomePerTornareAllaRicercaEffettuata = request.getParameter("cognomePerTornareAllaRicercaEffettuata");
		String usernamePerTornareAllaRicercaEffettuata = request.getParameter("usernamePerTornareAllaRicercaEffettuata");
		String statoPerTornareAllaRicercaEffettuata = request.getParameter("statoPerTornareAllaRicercaEffettuata");
//		String ruoliPerTornareAllaRicercaEffettuata = request.getParameter("ruoliPerTornareAllaRicercaEffettuata");
		
		//passo i parametri per tornare alla ricerca effettuata alla jsp successiva 
		request.setAttribute("nomePerTornareAllaRicercaEffettuata", nomePerTornareAllaRicercaEffettuata);
		request.setAttribute("cognomePerTornareAllaRicercaEffettuata", cognomePerTornareAllaRicercaEffettuata);
		request.setAttribute("usernamePerTornareAllaRicercaEffettuata", usernamePerTornareAllaRicercaEffettuata);
		request.setAttribute("statoPerTornareAllaRicercaEffettuata", statoPerTornareAllaRicercaEffettuata);
//		request.setAttribute("ruoliPerTornareAllaRicercaEffettuata", ruoliPerTornareAllaRicercaEffettuata);
		
		request.getRequestDispatcher("updateUtente.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
