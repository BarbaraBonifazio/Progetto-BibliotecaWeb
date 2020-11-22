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

import it.bibliotecaweb.model.ruolo.Codice;
import it.bibliotecaweb.model.ruolo.Ruolo;
import it.bibliotecaweb.model.utente.Utente;

@WebFilter("/libro/*")
public class LibroFilter implements Filter {

	private String contesto;

	public LibroFilter() {

	}

	public void init(FilterConfig fConfig) throws ServletException {
		contesto = fConfig.getServletContext().getContextPath();
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		

		String percorso = httpServletRequest.getRequestURI();
		
		
			Utente utente = (Utente) httpServletRequest.getSession().getAttribute("utente");
			// VERIFICA RUOLO UTENTE PER IMPEDIRE LA VISUALIZZAZIONE DELLE ALTRE SERVLET DI LIBRO
			if (percorso.contains("PrepareFindLibriServlet") || percorso.contains("ExecuteFindLibriServlet")
				 || percorso.contains("FindByIdLibroServlet")) {
				// se il tentativo di accesso risponde a queste servlet
				chain.doFilter(request, response); // accedi
			} else //VERIFICA SESSIONE ATTIVA E LOGIN UTENTE
				if (httpServletRequest.getSession() == null || httpServletRequest.getSession().getAttribute("utente") == null) { 
					httpServletResponse.sendRedirect(contesto); // se sessione e utente sono null, rimanda chi tenta di accedere alla login
				} else {
			
//			{
//				Boolean isAdmin = (Boolean) httpServletRequest.getSession().getAttribute("isAdmin");
//				Boolean isClassicUser = (Boolean) httpServletRequest.getSession().getAttribute("isClassicUser");
//				
//				if(isAdmin || isClassicUser) {
//					chain.doFilter(request, response); // accedi
//				} else {
//					httpServletResponse.sendRedirect(contesto); // altrimenti ritorna
//				}
//			}
			
			
			{
				
					boolean isAdminOrClassicUser = false;
					
					for (Ruolo r : utente.getRuoli()) {
						if(Codice.ADMIN_ROLE == r.getCodice() || Codice.GUEST_ROLE == r.getCodice()) {
							isAdminOrClassicUser = true;
						}
							if(isAdminOrClassicUser) {
								chain.doFilter(request, response); // accedi
							}else {
								httpServletResponse.sendRedirect(contesto); // altrimenti ritorna
							}
					}
			   }
		 	}
	}
}
