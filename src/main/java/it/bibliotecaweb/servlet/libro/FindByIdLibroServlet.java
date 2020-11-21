package it.bibliotecaweb.servlet.libro;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.service.MyServiceFactory;


@WebServlet("/FindByIdLibroServlet")
public class FindByIdLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FindByIdLibroServlet() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idDelLibroPerDettaglio = request.getParameter("idParamPerDettaglioLibro");
		
		try {
		// Valido eventuale parametro passato da url
		if (idDelLibroPerDettaglio == null) {
			request.setAttribute("errorMessage", "Attenzione il valore inserito non è valido!");
			
				request.setAttribute("libriPerResultsListLibri", MyServiceFactory.getLibroServiceInstance().setAll());
			
			request.getRequestDispatcher("resultsListUtenti.jsp").forward(request, response);
			return;
		}
		// --fine validazione parametro da url--
		
		
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
