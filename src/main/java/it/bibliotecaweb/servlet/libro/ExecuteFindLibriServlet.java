package it.bibliotecaweb.servlet.libro;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.model.libro.Genere;
import it.bibliotecaweb.model.libro.Libro;
import it.bibliotecaweb.service.MyServiceFactory;
import it.bibliotecaweb.service.autore.AutoreService;
import it.bibliotecaweb.service.libro.LibroService;

@WebServlet("/libro/ExecuteFindLibriServlet")
public class ExecuteFindLibriServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteFindLibriServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String titoloInputParam = request.getParameter("titolo");
		String tramaInputParam = request.getParameter("trama");
		String genereInputParam = request.getParameter("genere");
		String autoreInputParam = request.getParameter("autore");

		String titolo = (titoloInputParam.isEmpty()) ? null : titoloInputParam;
		String trama = (tramaInputParam.isEmpty()) ? null : tramaInputParam;
		
		Libro libroNew = new Libro(titolo, trama);
		
		try {
			
			if (!autoreInputParam.isEmpty() && genereInputParam != null) {
				AutoreService serviceAutore = MyServiceFactory.getAutoreServiceInstance();
				libroNew.setAutore(serviceAutore.trova(Long.parseLong(autoreInputParam)));
			}
			
			if (!genereInputParam.isEmpty() && genereInputParam != null) {
				libroNew.setGenere(Genere.valueOf(genereInputParam));
			}
			
			Set<Libro> listaLibri = new HashSet<>();
			LibroService serviceLibro = MyServiceFactory.getLibroServiceInstance();
			listaLibri = serviceLibro.trovaLibro(libroNew);
			
			request.setAttribute("titoloPerTornareAllaRicercaEffettuata", titoloInputParam);
			request.setAttribute("tramaPerTornareAllaRicercaEffettuata", tramaInputParam);
			request.setAttribute("generePerTornareAllaRicercaEffettuata", genereInputParam);
			request.setAttribute("autorePerTornareAllaRicercaEffettuata", autoreInputParam);

			request.setAttribute("libriPerResultsListLibri", listaLibri);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// andiamo ai risultati
		request.getRequestDispatcher("resultsListLibri.jsp").forward(request, response); //(la jsp "showLibro" è per il dettaglio del libro in Visualizza)
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
