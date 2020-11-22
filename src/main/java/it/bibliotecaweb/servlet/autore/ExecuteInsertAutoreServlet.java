package it.bibliotecaweb.servlet.autore;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import it.bibliotecaweb.service.MyServiceFactory;
import it.bibliotecaweb.service.autore.AutoreService;


@WebServlet("/autore/ExecuteInsertAutoreServlet")
public class ExecuteInsertAutoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ExecuteInsertAutoreServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String nomeInputParam = request.getParameter("nome");
		String cognomeInputParam = request.getParameter("cognome");
		String dataInputParam = request.getParameter("dataNascita");
		
		try {
			
			//controllo backend sulla presenza di dati inseriti 
			if (nomeInputParam.isEmpty() || cognomeInputParam.isEmpty() 
					|| dataInputParam == null)  {

				List<String> errorMessage = new ArrayList<>();
				if(nomeInputParam.isEmpty()){
					String errore1 = "Attenzione il campo nome è vuoto!";
					errorMessage.add(errore1);
				}
				if(cognomeInputParam.isEmpty()) {
					String errore2 = "Attenzione il campo cognome è vuoto!";
					errorMessage.add(errore2);
				}
				if(dataInputParam.isEmpty() || dataInputParam == null) {
					String errore3 = "Attenzione non risulta selezionata alcuna data!";
					errorMessage.add(errore3);
				}
				
				Autore autorePerInsertErrato = new Autore(nomeInputParam, cognomeInputParam);
				if(!dataInputParam.isEmpty() && dataInputParam != null) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate date = LocalDate.parse(dataInputParam, formatter);
					autorePerInsertErrato.setDataNascita(date);
					}

				request.setAttribute("autorePerInsertErrato", autorePerInsertErrato);
				request.setAttribute("errorMessage", errorMessage);				
				request.getRequestDispatcher("insertAutore.jsp").forward(request, response);
				return;
			}
			//---fine controllo backend 
			
			AutoreService serviceAutore = MyServiceFactory.getAutoreServiceInstance();
			Autore autoreNew = new Autore(nomeInputParam, cognomeInputParam);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date = LocalDate.parse(dataInputParam, formatter);
			autoreNew.setDataNascita(date);

			serviceAutore.inserisciNuovo(autoreNew);
			request.setAttribute("autoriPerResultsList", MyServiceFactory.getAutoreServiceInstance().setAll());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// andiamo ai risultati
		request.getRequestDispatcher("resultsListAutori.jsp").forward(request, response);
	}

}
