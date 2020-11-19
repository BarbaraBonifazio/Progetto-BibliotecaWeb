package it.bibliotecaweb.servlet.utente;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.model.ruolo.Ruolo;
import it.bibliotecaweb.model.utente.StatoUtente;
import it.bibliotecaweb.model.utente.Utente;
import it.bibliotecaweb.service.MyServiceFactory;
import it.bibliotecaweb.service.utente.UtenteService;

@WebServlet("/utente/ExecuteFindUtenteServlet")
public class ExecuteFindUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteFindUtenteServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nome = request.getParameter("nomeUtente");
		String cognome = request.getParameter("cognomeUtente");
		String username = request.getParameter("unameUtente");
		String stato = request.getParameter("stato");
//		String[] ruoliInput = request.getParameterValues("ruolo");
//		String[] ruoli = (ruoliInput == null) ? new String[0] : ruoliInput; 

//		boolean listaRuoli = request.getParameter("ruolo") != null;

		Utente utente = new Utente(nome, cognome, username);
		UtenteService service = MyServiceFactory.getUtenteServiceInstance();

		try {
//			if (ruoli.length>0) {
//				Set<Ruolo> listaRuoliUtente = new HashSet<>();
//				for (String s : ruoli) {
//					Long idRuolo = Long.parseLong(s);
//					Ruolo ruoloDaDB = MyServiceFactory.getRuoloServiceInstance().trova(idRuolo);
//					listaRuoliUtente.add(ruoloDaDB);
//					utente.setRuoli(listaRuoliUtente);
//				}
//			}

			if (stato != "EMPTY" ) {
				utente.setStato(StatoUtente.valueOf(stato));
			}

			Set<Utente> listaUtenti = new HashSet<>();
			listaUtenti = service.trovaUtente(utente);
			request.setAttribute("utentiPerResultsList", listaUtenti);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// andiamo ai risultati
		request.getRequestDispatcher("resultsListUtenti.jsp").forward(request, response); //crea jsp!! (la jsp "ShowUtente" è per il dettaglio dell'utente in Visualizza)

	}
}
