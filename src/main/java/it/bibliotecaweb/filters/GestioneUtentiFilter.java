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

@WebFilter("/utente/*")
public class GestioneUtentiFilter implements Filter {

	private String contesto;
	
    public GestioneUtentiFilter() {
       
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

		for(Ruolo r:utente.getRuoli()) {
			if(Codice.ADMIN_ROLE == r.getCodice()) { //se è un admin, entra e continua
				chain.doFilter(request, response);
			} else {
//			httpServletRequest.setAttribute("errorMessage", "Attenzione non hai i permessi per accedere a questa pagina!");
//			httpServletRequest.getRequestDispatcher(contesto);
//				 response.sendRedirect("Admin_And_User_Login_Form.jsp?invalid=true");
			httpServletResponse.sendRedirect(contesto); //altrimenti ritorna al contesto 
			}
		}	
	}
}
