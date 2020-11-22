package it.bibliotecaweb.servlet.autore;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.model.autore.Autore;
import it.bibliotecaweb.service.MyServiceFactory;
import it.bibliotecaweb.service.autore.AutoreService;

@WebServlet("/autore/PrepareUpdateAutoreServlet")
public class PrepareUpdateAutoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PrepareUpdateAutoreServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idAutoreDaModificareInput = request.getParameter("idDaInviareAExecuteUpdate");

		try {
		// Valido eventuale parametro passato da url
		if (idAutoreDaModificareInput.isEmpty() || idAutoreDaModificareInput == null) {
			request.setAttribute("errorMessage", "Attenzione il valore inserito non è valido!");
			request.setAttribute("autoriPerResultsList", MyServiceFactory.getAutoreServiceInstance().setAll());
			request.getRequestDispatcher("resultsListAutori.jsp").forward(request, response);
			return;

		}
		// --fine validazione parametro da url--

			AutoreService serviceAutore = MyServiceFactory.getAutoreServiceInstance();

			// parametri da passare alla jsp per effettuare l'update dell'autore
			Autore autoreInstance = serviceAutore.trova(Long.parseLong(idAutoreDaModificareInput));

			request.setAttribute("autorePerUpdate", autoreInstance);

			// Verifico reale esistenza del parametro nel DB
			if (autoreInstance == null) {
				request.setAttribute("errorMessage", "Attenzione il valore inserito non esiste!");
				request.setAttribute("autoriPerResultsList", MyServiceFactory.getAutoreServiceInstance().setAll());
				request.getRequestDispatcher("resultsListAutori.jsp").forward(request, response);
				return;
			}
			// --fine verifica parametro DB

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("updateAutore.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
