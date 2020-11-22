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


@WebServlet("/autore/FindByIdAutoreServlet")
public class FindByIdAutoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FindByIdAutoreServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idAutorePerDettaglio = request.getParameter("idParamPerDettaglioAutore");
		
		try {
		// Valido eventuale parametro passato da url
		if (idAutorePerDettaglio.isEmpty()) {
			request.setAttribute("errorMessage", "Attenzione non è stato inserito alcun valore di ricerca!");
			request.setAttribute("autoriPerResultsList", MyServiceFactory.getAutoreServiceInstance().setAll());
			request.getRequestDispatcher("resultsListAutori.jsp").forward(request, response);
			return;
		}
		// --fine validazione parametro da url--
		
		AutoreService service = MyServiceFactory.getAutoreServiceInstance();
		Autore autoreDaDB = service.trova(Long.parseLong(idAutorePerDettaglio));
			
		// Verifico reale esistenza del parametro passato da URL nel DB
		if (autoreDaDB == null) {
			request.setAttribute("errorMessage", "Attenzione il valore inserito non esiste!");
			request.setAttribute("libriPerResultsListLibri", MyServiceFactory.getAutoreServiceInstance().setAll());
			request.getRequestDispatcher("resultsListAutori.jsp").forward(request, response);
			return;
		}
		// --fine verifica parametro DB
		
		//passo l'autore da DB alla jsp
		request.setAttribute("autorePerShow", autoreDaDB);

				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//procedo con la richiesta del dettaglio libro
		request.getRequestDispatcher("showAutore.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
