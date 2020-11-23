package it.bibliotecaweb.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class ValidateSessionFilter implements Filter {

	private String contesto;

	public ValidateSessionFilter() {
	}

	
	public void destroy() {
	}


	public void init(FilterConfig fConfig) throws ServletException {
		contesto = fConfig.getServletContext().getContextPath(); // configuro il path che dovrà seguire la catena di
																	// filtri
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request; // cast della ServletRequest a HttpServletRequest
																				
		HttpServletResponse httpServletResponse = (HttpServletResponse) response; // cast della ServletResponse a HttpServletResponse

		String percorso = httpServletRequest.getRequestURI(); // assegno alla stringa "percorso" il valore dell'URL  passato dal protocollo
																
		// Mi ritorna una stringa contenente la parte dell'URL dal procollo fino alla stringa
		
		if (percorso.equals("/bibliotecaweb/") || percorso.equals("/bibliotecaweb/LoginServlet")
				|| percorso.equals("/bibliotecaweb/LogoutServlet")
				|| percorso.contains("assets")) { // entra qui se il filtro atterra su queste servlet
																		
			chain.doFilter(request, response); // ignora queste servlet e non filtrarle
		} else {
			if (httpServletRequest.getSession().getAttribute("utente") == null // prendi l'attributo di sessione "utente" e dimmi se è presente
					|| httpServletRequest.getSession() == null) { //prendi l'attributo "sessione" e dimmi se è presente
				httpServletResponse.sendRedirect(contesto); // se sono null, rimandalo chi tenta di accedere alla login
			} else {
				chain.doFilter(request, response);
			  }
		  }
		}
}
