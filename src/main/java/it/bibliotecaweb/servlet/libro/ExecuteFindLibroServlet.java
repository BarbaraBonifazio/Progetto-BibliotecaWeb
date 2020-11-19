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

@WebServlet("/libro/ExecuteFindLibroServlet")
public class ExecuteFindLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteFindLibroServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String titoloInputParam = request.getParameter("titolo");
		String tramaInputParam = request.getParameter("trama");
		String genereInputParam = request.getParameter("genere");
		String autoreInputParam = request.getParameter("autore");

		Libro libroNew = new Libro(titoloInputParam, tramaInputParam);
		
		try {
			
//			if(genereInputParam != "EMPTY") {
//				libroNew.setGenere(Genere.valueOf(genereInputParam));
//			}
			
			if (!autoreInputParam.isEmpty()) {
				AutoreService serviceAutore = MyServiceFactory.getAutoreServiceInstance();
				libroNew.setAutore(serviceAutore.trova(Long.parseLong(autoreInputParam)));
			}
			
			Set<Libro> listaLibri = new HashSet<>();
			LibroService serviceLibro = MyServiceFactory.getLibroServiceInstance();
			listaLibri = serviceLibro.trovaLibro(libroNew);
			
			request.setAttribute("libriPerResultsListLibri", listaLibri);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// andiamo ai risultati
		request.getRequestDispatcher("resultsListLibri.jsp").forward(request, response); //(la jsp "showLibro" è per il dettaglio del libro in Visualizza)
	}

}
