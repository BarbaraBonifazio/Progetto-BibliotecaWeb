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

@WebServlet("/autore/ExecuteDeleteAutoreServlet")
public class ExecuteDeleteAutoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ExecuteDeleteAutoreServlet() {
        super();     
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idInputParam = request.getParameter("idDaInviareAExecuteDelete");

		AutoreService service = MyServiceFactory.getAutoreServiceInstance();

		try {
			Autore autore = service.trova(Long.parseLong(idInputParam));
			service.rimuovi(autore);

			request.setAttribute("successMessage", "Operazione effettuata con successo");
		
		//palleggio i parametri passati per tornare alla lista dei Risultati della ricerca iniziale
		String nomeInputParamPerRicerca = request.getParameter("nomeAutorePerRicerca");
		String cognomeInputParamPerRicerca = request.getParameter("cognomeAutorePerRicerca");
		String dataNascitaInputParamPerRicerca = request.getParameter("dataNascitaAutorePerRicerca");
		
		
		//passo i parametri per tornare alla ricerca effettuata alla jsp successiva 
		request.setAttribute("nomePerTornareAllaRicercaEffettuata", nomeInputParamPerRicerca);
		request.setAttribute("cognomePerTornareAllaRicercaEffettuata", cognomeInputParamPerRicerca);
		request.setAttribute("dataNascitaPerTornareAllaRicercaEffettuata", dataNascitaInputParamPerRicerca);


		String nomeRicerca = (nomeInputParamPerRicerca.isEmpty()) ? null : nomeInputParamPerRicerca;
		String cognomeRicerca = (cognomeInputParamPerRicerca.isEmpty()) ? null : cognomeInputParamPerRicerca;
	
			Autore autoreNew = new Autore(nomeRicerca, cognomeRicerca);
		
				if (!dataNascitaInputParamPerRicerca.isEmpty() && dataNascitaInputParamPerRicerca != null) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate date = LocalDate.parse(dataNascitaInputParamPerRicerca, formatter);
					autoreNew.setDataNascita(date);
				}
		//ricerco tutti gli autori che corrispondono ai parametri passati e li passo alla jsp dei risultati 
		//questo mi permetterà di tornare alla lista filtrata 
			Set<Autore> listaAutori = service.trovaAutori(autoreNew);
			request.setAttribute("autoriPerResultsList", listaAutori);
			
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
