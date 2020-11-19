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


	public void destroy() {
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		contesto = fConfig.getServletContext().getContextPath();
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request; 
		HttpServletResponse httpServletResponse = (HttpServletResponse) response; 
		
		Utente utente = (Utente)httpServletRequest.getSession().getAttribute("utente");
		
		String percorso = httpServletRequest.getRequestURI(); //per i filtri di verifica sia sulle servlet di libro che di autore
		
//VERIFICA RUOLO UTENTE PER VISUALIZZARE LE ALTRE SERVLET DI LIBRO
		if(percorso.contains("PrepareFindLibriServlet") || percorso.contains("ExecuteFindLibroServlet")){ //|| percorso.contains("FindByIdLibroServlet")){ 
		//entri dentro e cicli i ruoli per verifica es. PrepareFindLibriServlet & ExecuteFindLibroServlet & FindByIdLibroServlet
		chain.doFilter(request, response); //continua
		} else {
		 
		for(Ruolo r:utente.getRuoli()) {
			if(Codice.GUEST_ROLE == r.getCodice()) {
			httpServletResponse.sendRedirect(contesto); //altrimenti ritorna
			}
		}
//		
		}
	}
}

	
	

