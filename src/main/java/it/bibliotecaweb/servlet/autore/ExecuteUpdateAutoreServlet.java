package it.bibliotecaweb.servlet.autore;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.model.autore.Autore;
import it.bibliotecaweb.service.MyServiceFactory;
import it.bibliotecaweb.service.autore.AutoreService;

@WebServlet("/autore/ExecuteUpdateAutoreServlet")
public class ExecuteUpdateAutoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ExecuteUpdateAutoreServlet() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idInputParam = request.getParameter("idAutorePerUpdate");
		String nomeInputParam = request.getParameter("nome");
		String cognomeInputParam = request.getParameter("cognome");
		String dataNascitaInputParam = request.getParameter("dataNascita");
		
		
		AutoreService serviceAutore = MyServiceFactory.getAutoreServiceInstance();
		
		try {
			
			//controllo backend sulla presenza di dati inseriti 
			if (nomeInputParam.isEmpty() || cognomeInputParam.isEmpty() 
					|| dataNascitaInputParam.isEmpty() && dataNascitaInputParam != null)  {

				List<String> errorMessage = new ArrayList<>();
				if(nomeInputParam.isEmpty()){
					String errore1 = "Attenzione il campo nome è vuoto!";
					errorMessage.add(errore1);
				}
				if(cognomeInputParam.isEmpty()) {
					String errore2 = "Attenzione il campo cognome è vuoto!";
					errorMessage.add(errore2);
				}
				if(dataNascitaInputParam.isEmpty() && dataNascitaInputParam != null) {
					String errore3 = "Attenzione non risulta selezionata alcuna data!";
					errorMessage.add(errore3);
				}
				
				Autore autorePerUpdateErrato = new Autore(nomeInputParam, cognomeInputParam);
				if(!dataNascitaInputParam.isEmpty() && dataNascitaInputParam != null) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate date = LocalDate.parse(dataNascitaInputParam, formatter);
					autorePerUpdateErrato.setDataNascita(date);
					}

				request.setAttribute("autorePerUpdate", autorePerUpdateErrato);
				//parametri da passare alla jsp updateAutore per effettuare l'update dello stesso autore 
				autorePerUpdateErrato.setId(Long.parseLong(idInputParam)); //assegno all'autore temporaneo l'id dell'autore che volevo inizialmente modificare
				request.setAttribute("errorMessage", errorMessage);				
				request.getRequestDispatcher("updateAutore.jsp").forward(request, response);
				return;
			}
			//---fine controllo backend 

			//richiamo l'autore da DB e imposto eventuali parametri passati dalla pagina per l'update 
			Autore autoreDaDb  =  serviceAutore.trova(Long.parseLong(idInputParam));
			autoreDaDb.setNome(nomeInputParam);
			autoreDaDb.setCognome(cognomeInputParam);
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date = LocalDate.parse(dataNascitaInputParam, formatter);
			autoreDaDb.setDataNascita(date);
	
			//aggiorno l'autore con i nuovi parametri 
			serviceAutore.aggiorna(autoreDaDb);
			request.setAttribute("successMessage", "Operazione effettuata con successo");
			request.setAttribute("autoriPerResultsList", MyServiceFactory.getAutoreServiceInstance().setAll());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
			// andiamo ai risultati
			request.getRequestDispatcher("resultsListAutori.jsp").forward(request, response);
	}
}
