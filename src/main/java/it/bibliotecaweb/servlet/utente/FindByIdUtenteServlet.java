package it.bibliotecaweb.servlet.utente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.model.utente.Utente;
import it.bibliotecaweb.service.MyServiceFactory;
import it.bibliotecaweb.service.utente.UtenteService;

@WebServlet("/utente/FindByIdUtenteServlet")
public class FindByIdUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public FindByIdUtenteServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idDellUtentePerDettaglio = request.getParameter("idParamPerDettaglioUtente");

		// Valido eventuale parametro passato da url
		if (idDellUtentePerDettaglio.isEmpty() || idDellUtentePerDettaglio == null) {
			request.setAttribute("errorMessage", "Attenzione il valore inserito non è valido!");
			request.getRequestDispatcher("listUtenti.jsp").forward(request, response);
			return;
		}
		// --fine validazione parametro da url--

		UtenteService service = MyServiceFactory.getUtenteServiceInstance();

		Utente result;
		try {
			result = service.trova(Long.parseLong(idDellUtentePerDettaglio));
			request.setAttribute("utentePerShow", result);

			// Verifico reale esistenza del parametro nel DB
			if (result == null) {
				request.setAttribute("errorMessage", "Attenzione il valore inserito non esiste!");
				request.getRequestDispatcher("listUtenti.jsp").forward(request, response);
				return;
			}
			// --fine verifica parametro DB

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("showUtente.jsp").forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
