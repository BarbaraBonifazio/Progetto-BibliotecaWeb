package it.bibliotecaweb.servlet.utente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.service.MyServiceFactory;

@WebServlet("/utente/PrepareInsertUtenteServlet")
public class PrepareInsertUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public PrepareInsertUtenteServlet() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//prendo i parametri per tornare alla ricerca effettuata 
		String nomePerTornareAllaRicercaEffettuata = request.getParameter("nomePerTornareAllaRicercaEffettuata");
		String cognomePerTornareAllaRicercaEffettuata = request.getParameter("cognomePerTornareAllaRicercaEffettuata");
		String usernamePerTornareAllaRicercaEffettuata = request.getParameter("usernamePerTornareAllaRicercaEffettuata");
		String statoPerTornareAllaRicercaEffettuata = request.getParameter("statoPerTornareAllaRicercaEffettuata");
		String ruoliPerTornareAllaRicercaEffettuata = request.getParameter("ruoliPerTornareAllaRicercaEffettuata");
		
		
		//passo i parametri alla jsp successiva 
		request.setAttribute("nomePerTornareAllaRicercaEffettuata", nomePerTornareAllaRicercaEffettuata);
		request.setAttribute("cognomePerTornareAllaRicercaEffettuata", cognomePerTornareAllaRicercaEffettuata);
		request.setAttribute("usernamePerTornareAllaRicercaEffettuata", usernamePerTornareAllaRicercaEffettuata);
		request.setAttribute("statoPerTornareAllaRicercaEffettuata", statoPerTornareAllaRicercaEffettuata);
		request.setAttribute("ruoliPerTornareAllaRicercaEffettuata", ruoliPerTornareAllaRicercaEffettuata);
		
		try {
			request.setAttribute("listRuoliAttribute", MyServiceFactory.getRuoloServiceInstance().setAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("insertUtente.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
