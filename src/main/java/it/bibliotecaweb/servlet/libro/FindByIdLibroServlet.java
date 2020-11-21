package it.bibliotecaweb.servlet.libro;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.model.libro.Libro;
import it.bibliotecaweb.service.MyServiceFactory;
import it.bibliotecaweb.service.autore.AutoreService;
import it.bibliotecaweb.service.libro.LibroService;


@WebServlet("/libro/FindByIdLibroServlet")
public class FindByIdLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FindByIdLibroServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idDelLibroPerDettaglio = request.getParameter("idParamPerDettaglioLibro");
		String idAutorePerDettaglio = request.getParameter("idAutore");
		
		try {
		// Valido eventuale parametro passato da url
		if (idDelLibroPerDettaglio.isEmpty()) {
			request.setAttribute("errorMessage", "Attenzione non è stato inserito alcun valore di ricerca!");
			request.setAttribute("libriPerResultsListLibri", MyServiceFactory.getLibroServiceInstance().setAll());
			request.getRequestDispatcher("resultsListLibri.jsp").forward(request, response);
			return;
		}
		// --fine validazione parametro da url--
		
		LibroService service = MyServiceFactory.getLibroServiceInstance();
		Libro libroDaDB = service.trova(Long.parseLong(idDelLibroPerDettaglio));
			
		// Verifico reale esistenza del parametro passato da URL nel DB
		if (libroDaDB == null) {
			request.setAttribute("errorMessage", "Attenzione il valore inserito non esiste!");
			request.setAttribute("libriPerResultsListLibri", MyServiceFactory.getLibroServiceInstance().setAll());
			request.getRequestDispatcher("resultsListLibri.jsp").forward(request, response);
			return;
		}
		// --fine verifica parametro DB

		AutoreService serviceAutore = MyServiceFactory.getAutoreServiceInstance();
		libroDaDB.setAutore(serviceAutore.trova(Long.parseLong(idAutorePerDettaglio)));
		
		//passo il libro da DB popolato con l'autore alla jsp
		request.setAttribute("libroPerShow", libroDaDB);
		
		
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//procedo con la richiesta del dettaglio libro
		request.getRequestDispatcher("showLibro.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
