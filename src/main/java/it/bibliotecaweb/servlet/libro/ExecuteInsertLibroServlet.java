package it.bibliotecaweb.servlet.libro;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.dao.autore.AutoreDAO;
import it.bibliotecaweb.model.autore.Autore;
import it.bibliotecaweb.model.libro.Genere;
import it.bibliotecaweb.model.libro.Libro;
import it.bibliotecaweb.model.utente.StatoUtente;
import it.bibliotecaweb.service.MyServiceFactory;
import it.bibliotecaweb.service.autore.AutoreService;
import it.bibliotecaweb.service.libro.LibroService;
import it.bibliotecaweb.service.utente.UtenteService;


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
		
		AutoreService serviceAutore = MyServiceFactory.getAutoreServiceInstance();
		
		try {
			Autore autoreDaDb  =  serviceAutore.trova(Long.parseLong(idAutoreInputParam));
			
		} catch (Exception e) {
			//se entra in eccezione, rimanda alla jsp reinserendo i campi già popolati dall'utente
			request.setAttribute("errorMessage", "Attenzione! L'autore inserito non è valido!");
			request.setAttribute("titoloPerInsertLibroErrore", titoloInputParam);
			request.setAttribute("tramaPerInsertLibroErrore", tramaInputParam);
			request.setAttribute("generePerInsertLibroErrore", genereInputParam);
			request.setAttribute("autorePerInsertLibroErrore", idAutoreInputParam);
			
			request.getRequestDispatcher("insertLibroErrore.jsp").forward(request, response);
			return;
		}
		LibroService serviceLibro = MyServiceFactory.getLibroServiceInstance();
		
		Libro libroNew = new Libro();
		
		
		
		if (!genereInputParam.isEmpty() && genereInputParam != null) {
			libroNew.setGenere(Genere.valueOf(genereInputParam));
		}
		
		
		try {
			serviceLibro.inserisciNuovo(libroNew);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
