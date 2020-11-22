package it.bibliotecaweb.servlet.libro;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.model.libro.Libro;
import it.bibliotecaweb.service.MyServiceFactory;
import it.bibliotecaweb.service.libro.LibroService;

@WebServlet("/libro/ConfirmDeleteLibroServlet")
public class ConfirmDeleteLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ConfirmDeleteLibroServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String parametroIdLibroCheVoglioEliminare = request.getParameter("idDaInviareAExecuteDelete");

		try {
			// Valido eventuale parametro passato da URL
			if (parametroIdLibroCheVoglioEliminare.isEmpty()) {
				request.setAttribute("errorMessage", "Attenzione non è stato inserito alcun valore!");
				request.setAttribute("libriPerResultsListLibri", MyServiceFactory.getLibroServiceInstance().setAll());
				request.getRequestDispatcher("resultsListLibri.jsp").forward(request, response);
				return;
			}
			// --fine validazione parametro da url--

			LibroService service = MyServiceFactory.getLibroServiceInstance();

			Libro libroInstance = service.trova(Long.parseLong(parametroIdLibroCheVoglioEliminare));
			// passo l'utente da DB alla jsp
			request.setAttribute("libroDaEliminare", libroInstance);

			// Verifico reale esistenza del parametro passato da URL nel DB
			if (libroInstance == null) {
				request.setAttribute("errorMessage", "Attenzione il valore inserito non esiste!");
				request.setAttribute("libriPerResultsListLibri", MyServiceFactory.getLibroServiceInstance().setAll());
				request.getRequestDispatcher("resultsListLibri.jsp").forward(request, response);
				return;
			}
			// --fine verifica parametro DB

			
			//palleggio i parametri passati per tornare alla lista dei Risultati della ricerca iniziale
			String titoloPerTornareAllaRicercaEffettuata = request.getParameter("titoloPerTornareAllaRicercaEffettuata");
			String tramaPerTornareAllaRicercaEffettuata = request.getParameter("tramaPerTornareAllaRicercaEffettuata");
			String generePerTornareAllaRicercaEffettuata = request.getParameter("generePerTornareAllaRicercaEffettuata");
			String autorePerTornareAllaRicercaEffettuata = request.getParameter("autorePerTornareAllaRicercaEffettuata");
			
			
			//passo i parametri alla jsp successiva, per tornare eventualmente alla ricerca effettuata
			request.setAttribute("titoloPerTornareAllaRicercaEffettuata", titoloPerTornareAllaRicercaEffettuata);
			request.setAttribute("tramaPerTornareAllaRicercaEffettuata", tramaPerTornareAllaRicercaEffettuata);
			request.setAttribute("generePerTornareAllaRicercaEffettuata", generePerTornareAllaRicercaEffettuata);
			request.setAttribute("autorePerTornareAllaRicercaEffettuata", autorePerTornareAllaRicercaEffettuata);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("confirmDeleteLibro.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
