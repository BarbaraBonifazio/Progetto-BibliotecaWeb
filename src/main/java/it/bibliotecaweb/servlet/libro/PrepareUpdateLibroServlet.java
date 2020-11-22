package it.bibliotecaweb.servlet.libro;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.model.libro.Genere;
import it.bibliotecaweb.model.libro.Libro;
import it.bibliotecaweb.service.MyServiceFactory;
import it.bibliotecaweb.service.libro.LibroService;


@WebServlet("/libro/PrepareUpdateLibroServlet")
public class PrepareUpdateLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public PrepareUpdateLibroServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idLibroDaModificareInput = request.getParameter("idDaInviareAExecuteUpdate");
		
		// Valido eventuale parametro passato da url
		if (idLibroDaModificareInput.isEmpty() || idLibroDaModificareInput == null) {
			request.setAttribute("errorMessage", "Attenzione il valore inserito non è valido!");
			request.getRequestDispatcher("searchLibri.jsp").forward(request, response);
			return;
		
		}
		// --fine validazione parametro da url--
			
		try {
			
			LibroService serviceLibro = MyServiceFactory.getLibroServiceInstance();
			
			//parametri da passare alla jsp per effettuare l'update dell'utente 
			Libro libroInstance = serviceLibro.trova(Long.parseLong(idLibroDaModificareInput));

			
			// Verifico reale esistenza del parametro nel DB
			if (libroInstance == null) {
				request.setAttribute("errorMessage", "Attenzione il valore inserito non esiste!");
				request.getRequestDispatcher("searchLibri.jsp").forward(request, response);
				return;
			}
			// --fine verifica parametro DB
			
			request.setAttribute("libroPerUpdate", libroInstance);
			request.setAttribute("listAutoriAttribute", MyServiceFactory.getAutoreServiceInstance().setAll());
			// lista di enum per i generi del libro
			List<String> generi = Stream.of(Genere.values()).map(Enum::name).collect(Collectors.toList());
			request.setAttribute("listaGeneri", generi);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("updateLibro.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
