package it.bibliotecaweb.servlet.libro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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


@WebServlet("/libro/ExecuteUpdateLibroServlet")
public class ExecuteUpdateLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ExecuteUpdateLibroServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idInputParam = request.getParameter("idLibroPerUpdate");
		String titoloInputParam = request.getParameter("titolo");
		String tramaInputParam = request.getParameter("trama");
		String genereInputParam = request.getParameter("genere");
		String idAutoreInputParam = request.getParameter("idAutore");
		
		LibroService serviceLibro = MyServiceFactory.getLibroServiceInstance();
		
		try {
			
			//controllo backend sulla presenza di dati inseriti 
			if (titoloInputParam.isEmpty() || tramaInputParam.isEmpty() 
					|| genereInputParam == null 
					|| idAutoreInputParam == null) {

				List<String> errorMessage = new ArrayList<>();
				if(titoloInputParam.isEmpty()){
					String errore1 = "Attenzione il campo titolo è vuoto!";
					errorMessage.add(errore1);
				}
				if(tramaInputParam.isEmpty()) {
					String errore2 = "Attenzione il campo trama è vuoto!";
					errorMessage.add(errore2);
				}
				if(genereInputParam.isEmpty() || genereInputParam == null) {
					String errore3 = "Attenzione non risulta selezionato alcun genere!";
					errorMessage.add(errore3);
				}
				if(idAutoreInputParam.isEmpty() || idAutoreInputParam == null) {
					String errore4 = "Attenzione non risulta selezionato alcun autore!";
					errorMessage.add(errore4);
				}
				

				Libro libroPerInsertErrato = new Libro(titoloInputParam, tramaInputParam);
				if(!idAutoreInputParam.isEmpty() && idAutoreInputParam != null) {
					Long idAutore = Long.parseLong(idAutoreInputParam);
					Autore autoreLibro = MyServiceFactory.getAutoreServiceInstance().trova(idAutore);
					libroPerInsertErrato.setAutore(autoreLibro);
					}
				if (!genereInputParam.isEmpty() && genereInputParam != null) {
					libroPerInsertErrato.setGenere(Genere.valueOf(genereInputParam));
				}
				request.setAttribute("libroPerInsertErrato", libroPerInsertErrato);
				request.setAttribute("errorMessage", errorMessage);
				request.setAttribute("listaAutori", MyServiceFactory.getAutoreServiceInstance().setAll());
				List<String> generi = Stream.of(Genere.values()).map(Enum::name).collect(Collectors.toList());
				request.setAttribute("listaGeneri", generi);
				request.getRequestDispatcher("insertLibro.jsp").forward(request, response);
				return;
			}
			//---fine controllo backend 

			//richiamo il libro da DB e imposto eventuali parametri passati dalla pagina per l'update 
			Libro libroDaDb  =  serviceLibro.trova(Long.parseLong(idInputParam));
			libroDaDb.setTitolo(titoloInputParam);
			libroDaDb.setTrama(tramaInputParam);			
			libroDaDb.setAutore(MyServiceFactory.getAutoreServiceInstance().trova(Long.parseLong(idAutoreInputParam)));
			libroDaDb.setGenere(Genere.valueOf(genereInputParam));
		
	
			//aggiorno l'utente con i nuovi parametri 
			serviceLibro.aggiorna(libroDaDb);
			request.setAttribute("successMessage", "Operazione effettuata con successo");
			request.setAttribute("libriPerResultsListLibri", MyServiceFactory.getLibroServiceInstance().setAll());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// andiamo ai risultati
		request.getRequestDispatcher("resultsListLibri.jsp").forward(request, response);
	}

}
