package it.bibliotecaweb.servlet.autore;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.model.autore.Autore;
import it.bibliotecaweb.service.MyServiceFactory;
import it.bibliotecaweb.service.autore.AutoreService;

@WebServlet("/autore/ExecuteFindAutoriServlet")
public class ExecuteFindAutoriServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ExecuteFindAutoriServlet() {
        super(); 
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nomeInputParam = request.getParameter("nome");
		String cognomeInputParam = request.getParameter("cognome");
		String dataNascitaInputParam = request.getParameter("dataNascita");
	
		String nome = (nomeInputParam.isEmpty()) ? null : nomeInputParam;
		String cognome = (cognomeInputParam.isEmpty()) ? null : cognomeInputParam;
		AutoreService serviceAutore = MyServiceFactory.getAutoreServiceInstance();
		
		Autore autoreNew = new Autore(nome, cognome);
		
		if (!dataNascitaInputParam.isEmpty() && dataNascitaInputParam != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date = LocalDate.parse(dataNascitaInputParam, formatter);
			autoreNew.setDataNascita(date);
		}
		
		try {
			Set<Autore> listaAutoriDaDb = serviceAutore.trovaAutori(autoreNew);
			request.setAttribute("autoriPerResultsList", listaAutoriDaDb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// andiamo ai risultati
				request.getRequestDispatcher("resultsListAutori.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
