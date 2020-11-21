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
import it.bibliotecaweb.model.utente.Utente;
import it.bibliotecaweb.service.MyServiceFactory;
import it.bibliotecaweb.service.autore.AutoreService;
import it.bibliotecaweb.service.libro.LibroService;


@WebServlet("/libro/ExecuteInsertLibroServlet")
public class ExecuteInsertLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public ExecuteInsertLibroServlet() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String titoloInputParam = request.getParameter("titolo");
		String tramaInputParam = request.getParameter("trama");
		String genereInputParam = request.getParameter("genere");
		String idAutoreInputParam = request.getParameter("idAutore");
		
		try {
			
			//controllo backend sulla presenza di dati inseriti 
			if (titoloInputParam.isEmpty() || tramaInputParam.isEmpty() 
					|| genereInputParam.isEmpty() 
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
				if(genereInputParam.isEmpty()) {
					String errore3 = "Attenzione non risulta selezionato alcun genere!";
					errorMessage.add(errore3);
				}
				if( idAutoreInputParam == null) {
					String errore4 = "Attenzione non risulta selezionato alcun autore!";
					errorMessage.add(errore4);
				}
				
				
				
				Libro libroPerInsertErrato = new Libro(titoloInputParam, tramaInputParam);
				if(idAutoreInputParam != null) {
					Long idAutore = Long.parseLong(idAutoreInputParam);
					Autore autoreLibro = new Autore();
					autoreLibro.setId(idAutore);
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
			
			LibroService serviceLibro = MyServiceFactory.getLibroServiceInstance();
			Libro libroNew = new Libro();
			
			if (!genereInputParam.isEmpty() && genereInputParam != null) {
				libroNew.setGenere(Genere.valueOf(genereInputParam));
			}
			
			if(idAutoreInputParam != null) {
				Long idAutore = Long.parseLong(idAutoreInputParam);
				Autore autoreLibro = new Autore();
				autoreLibro.setId(idAutore);
				}
			
			serviceLibro.inserisciNuovo(libroNew);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// andiamo ai risultati
		request.getRequestDispatcher("resultsListLibri.jsp").forward(request, response);
	}

}
