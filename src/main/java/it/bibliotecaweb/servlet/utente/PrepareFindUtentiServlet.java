package it.bibliotecaweb.servlet.utente;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.model.utente.StatoUtente;
import it.bibliotecaweb.service.MyServiceFactory;


@WebServlet("/utente/PrepareFindUtentiServlet")
public class PrepareFindUtentiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public PrepareFindUtentiServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {

			request.setAttribute("listRuoliAttribute", MyServiceFactory.getRuoloServiceInstance().setAll());
			//lista di enum per lo stato dell'utente 
			List<String> listaStati = Stream.of(StatoUtente.values()).map(Enum::name).collect(Collectors.toList());
			request.setAttribute("listaStati", listaStati);
		} catch (Exception e) {

			e.printStackTrace();
		}

		request.getRequestDispatcher("searchUtenti.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
