package it.bibliotecaweb.servlet.utente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.service.MyServiceFactory;


@WebServlet("/PrepareGestioneUtentiServlet")
public class PrepareGestioneUtentiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public PrepareGestioneUtentiServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			request.setAttribute("listaUtentiAttribute", MyServiceFactory.getUtenteServiceInstance().setAll());
		} catch (Exception e) {

			e.printStackTrace();
		}

		request.getRequestDispatcher("listUtenti.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
