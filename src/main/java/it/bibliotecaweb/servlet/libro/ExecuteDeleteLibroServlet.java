package it.bibliotecaweb.servlet.libro;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.model.autore.Autore;
import it.bibliotecaweb.model.libro.Genere;
import it.bibliotecaweb.model.libro.Libro;
import it.bibliotecaweb.service.MyServiceFactory;
import it.bibliotecaweb.service.libro.LibroService;


@WebServlet("/libro/ExecuteDeleteLibroServlet")
public class ExecuteDeleteLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ExecuteDeleteLibroServlet() {
        super();
      
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idInputParam = request.getParameter("idDaInviareAExecuteDelete");

		LibroService service = MyServiceFactory.getLibroServiceInstance();

		try {
			
			Libro libro = service.trova(Long.parseLong(idInputParam));
			service.rimuovi(libro);

			request.setAttribute("successMessage", "Operazione effettuata con successo");
		
			
			//palleggio i parametri passati per tornare alla lista dei Risultati della ricerca iniziale
			String titoloInputParamPerRicerca = request.getParameter("titoloLibroPerRicerca");
			String tramaInputParamPerRicerca = request.getParameter("tramaLibroPerRicerca");
			String genereInputParamPerRicerca = request.getParameter("genereLibroPerRicerca");
			String autoreInputParamPerRicerca = request.getParameter("autoreLibroPerRicerca");

			
			//passo i parametri per tornare alla ricerca effettuata alla jsp successiva 
			request.setAttribute("nomePerTornareAllaRicercaEffettuata", titoloInputParamPerRicerca);
			request.setAttribute("cognomePerTornareAllaRicercaEffettuata", tramaInputParamPerRicerca);
			request.setAttribute("usernamePerTornareAllaRicercaEffettuata", genereInputParamPerRicerca);
			request.setAttribute("statoPerTornareAllaRicercaEffettuata", autoreInputParamPerRicerca);

		String titoloRicerca = (titoloInputParamPerRicerca.isEmpty()) ? null : titoloInputParamPerRicerca;
		String tramaRicerca = (tramaInputParamPerRicerca.isEmpty()) ? null : tramaInputParamPerRicerca;
	 
			Libro libroNew = new Libro(titoloRicerca, tramaRicerca);
		
				if (!genereInputParamPerRicerca.isEmpty() && genereInputParamPerRicerca != null) {
					libroNew.setGenere(Genere.valueOf(genereInputParamPerRicerca));
				}
				
				if(!autoreInputParamPerRicerca.isEmpty() && autoreInputParamPerRicerca != null) {
					Long idAutore = Long.parseLong(autoreInputParamPerRicerca);
					Autore autoreLibro = MyServiceFactory.getAutoreServiceInstance().trova(idAutore);
					libroNew.setAutore(autoreLibro);
					}	
		//ricerco tutti i libri che corrispondono ai parametri passati e li passo alla jsp dei risultati 
		//questo mi permetterà di tornare alla lista filtrata 
			Set<Libro> listaLibri = service.trovaLibro(libroNew);
			request.setAttribute("libriPerResultsListLibri", listaLibri);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		// andiamo ai risultati
		request.getRequestDispatcher("resultsListLibri.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
