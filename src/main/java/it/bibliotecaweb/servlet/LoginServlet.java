package it.bibliotecaweb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.bibliotecaweb.model.ruolo.Codice;
import it.bibliotecaweb.model.ruolo.Ruolo;
import it.bibliotecaweb.model.utente.StatoUtente;
import it.bibliotecaweb.model.utente.Utente;
import it.bibliotecaweb.service.MyServiceFactory;
import it.bibliotecaweb.service.utente.UtenteService;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	private boolean isAdmin;
	private boolean isClassicUser;
	private boolean isGuest;
	
	public LoginServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String usernameInputParam = request.getParameter("username");
		String passwordInputParam = request.getParameter("password");
		// fai controllo sui parametri

		// se la validazione fallisce torno in pagina

		UtenteService utenteService = MyServiceFactory.getUtenteServiceInstance();
		try {
			Utente utente = new Utente(usernameInputParam, passwordInputParam);
			utente = utenteService.trovaDaUsernameEPassword(utente);


					if (utente != null && utente.getStato() == StatoUtente.ATTIVO) {
						for(Ruolo r: utente.getRuoli()) {
						
							if(Codice.ADMIN_ROLE == r.getCodice()) {
								isAdmin = true;

							}
							if(Codice.CLASSIC_ROLE == r.getCodice()){	
								isClassicUser = true;

							}
							if(Codice.GUEST_ROLE == r.getCodice()){
								isGuest = true;

							}
						}
					} else {
						request.setAttribute("errorMessage", "Attenzione! Le tue credenziali non sono valide!");
						request.getRequestDispatcher("index.jsp").forward(request, response);
					}
					
				HttpSession session = request.getSession();
				session.setAttribute("utente", utente);
				session.setAttribute("isAdmin", isAdmin);
				session.setAttribute("isClassicUser", isClassicUser);
				session.setAttribute("isGuest", isGuest);
				
				request.getRequestDispatcher("home.jsp").forward(request, response);
				return;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


