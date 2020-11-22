package it.bibliotecaweb.servlet.autore;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/autore/PrepareInsertAutoreServlet")
public class PrepareInsertAutoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public PrepareInsertAutoreServlet() {
        super();
      
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.getRequestDispatcher("insertAutore.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
