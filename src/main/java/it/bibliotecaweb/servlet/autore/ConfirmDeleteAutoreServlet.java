package it.bibliotecaweb.servlet.autore;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.model.autore.Autore;
import it.bibliotecaweb.service.MyServiceFactory;
import it.bibliotecaweb.service.autore.AutoreService;

@WebServlet("/autore/ConfirmDeleteAutoreServlet")
public class ConfirmDeleteAutoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ConfirmDeleteAutoreServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String parametroIdDellAutoreCheVoglioEliminare = request.getParameter("idDaInviareAExecuteDelete");
		
		
		try {
				// Valido eventuale parametro passato da URL 
				if (parametroIdDellAutoreCheVoglioEliminare.isEmpty()) {
					request.setAttribute("errorMessage", "Attenzione non è stato inserito alcun valore!");
					request.setAttribute("autoriPerResultsList", MyServiceFactory.getAutoreServiceInstance().setAll());
					request.getRequestDispatcher("resultsListAutori.jsp").forward(request, response);
					return;
				}
				// --fine validazione parametro da url--
		
		AutoreService service = MyServiceFactory.getAutoreServiceInstance();		
				
			Autore autoreInstance = service.trova(Long.parseLong(parametroIdDellAutoreCheVoglioEliminare));
			//passo l'utente da DB alla jsp
			request.setAttribute("autoreDaEliminare", autoreInstance);
			
			// Verifico reale esistenza del parametro passato da URL nel DB
			if (autoreInstance == null) {
				request.setAttribute("errorMessage", "Attenzione il valore inserito non esiste!");
				request.setAttribute("autoriPerResultsList", MyServiceFactory.getAutoreServiceInstance().setAll());
				request.getRequestDispatcher("resultsListAutori.jsp").forward(request, response);
				return;
			}
			// --fine verifica parametro DB
			
		
			//palleggio i parametri passati per tornare alla lista dei Risultati della ricerca iniziale
			String nomePerTornareAllaRicercaEffettuata = request.getParameter("nomePerTornareAllaRicercaEffettuata");
			String cognomePerTornareAllaRicercaEffettuata = request.getParameter("cognomePerTornareAllaRicercaEffettuata");
			String dataNascitaPerTornareAllaRicercaEffettuata = request.getParameter("dataNascitaPerTornareAllaRicercaEffettuata");
			
			//passo i parametri alla jsp successiva, per tornare eventualmente alla ricerca effettuata
			request.setAttribute("nomePerTornareAllaRicercaEffettuata", nomePerTornareAllaRicercaEffettuata);
			request.setAttribute("cognomePerTornareAllaRicercaEffettuata", cognomePerTornareAllaRicercaEffettuata);
			request.setAttribute("dataNascitaPerTornareAllaRicercaEffettuata", dataNascitaPerTornareAllaRicercaEffettuata);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
				request.getRequestDispatcher("confirmDeleteAutore.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
