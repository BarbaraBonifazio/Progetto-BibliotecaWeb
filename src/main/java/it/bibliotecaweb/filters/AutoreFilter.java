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


@WebFilter("/autore/*")
public class AutoreFilter implements Filter {

	private String contesto;
	
    public AutoreFilter() {
        
    }

	public void init(FilterConfig fConfig) throws ServletException {
		contesto = fConfig.getServletContext().getContextPath();
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		Utente utente = (Utente) httpServletRequest.getSession().getAttribute("utente");

		String percorso = httpServletRequest.getRequestURI(); 

		//VERIFICA RUOLO UTENTE PER IMPEDIRE LA VISUALIZZAZIONE DELLE ALTRE SERVLET DI AUTORE
		if (percorso.contains("PrepareFindAutoriServlet") || percorso.contains("ExecuteFindAutoriServlet") || percorso.contains("FindByIdAutoriServlet")) {
			//se il tentativo di accesso risponde a queste servlet
			chain.doFilter(request, response); //accedi
		} else {

			for (Ruolo r : utente.getRuoli()) {
				if (Codice.GUEST_ROLE == r.getCodice()) {
					httpServletResponse.sendRedirect(contesto); //altrimenti ritorna
				}
			}
		}
	}

	public void destroy() {
		
	}
	

}
