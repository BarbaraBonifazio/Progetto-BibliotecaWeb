package it.bibliotecaweb.servlet.autore;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.service.MyServiceFactory;


@WebServlet("/autore/PrepareFindAutoriServlet")
public class PrepareFindAutoriServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public PrepareFindAutoriServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			request.setAttribute("listaLibri", MyServiceFactory.getLibroServiceInstance().setAll());
		} catch (Exception e) {

			e.printStackTrace();
		}
		request.getRequestDispatcher("searchAutori.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
