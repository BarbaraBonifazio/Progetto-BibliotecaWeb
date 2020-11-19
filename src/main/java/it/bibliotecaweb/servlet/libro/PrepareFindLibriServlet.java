package it.bibliotecaweb.servlet.libro;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.model.libro.Genere;
import it.bibliotecaweb.service.MyServiceFactory;

@WebServlet("/libro/PrepareFindLibriServlet")
public class PrepareFindLibriServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PrepareFindLibriServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			
			request.setAttribute("listaAutori", MyServiceFactory.getAutoreServiceInstance().setAll());
			// lista di enum per lo stato dell'utente
			List<String> generi = Stream.of(Genere.values()).map(Enum::name).collect(Collectors.toList());
			request.setAttribute("listaGeneri", generi);
		} catch (Exception e) {

			e.printStackTrace();
		}
		request.getRequestDispatcher("searchLibri.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
