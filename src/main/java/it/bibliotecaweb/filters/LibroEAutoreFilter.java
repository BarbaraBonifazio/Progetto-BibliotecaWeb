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

@WebFilter(urlPatterns = { "/libro/*", "/autore/*" })
public class LibroEAutoreFilter implements Filter {

	private String contesto;

	public LibroEAutoreFilter() {

	}

	public void init(FilterConfig fConfig) throws ServletException {
		contesto = fConfig.getServletContext().getContextPath();
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		String percorso = httpServletRequest.getRequestURI();
		// VERIFICA SESSIONE ATTIVA E LOGIN UTENTE
		if (httpServletRequest.getSession() == null || httpServletRequest.getSession().getAttribute("utente") == null) {
			httpServletResponse.sendRedirect(contesto); // se sessione e utente sono null, rimanda chi tenta di accedere
														// alla login
		} else {

			boolean isAdminOrClassicUser = false;
			Utente utente = (Utente) httpServletRequest.getSession().getAttribute("utente");
			// VERIFICA RUOLO UTENTE PER IMPEDIRE LA VISUALIZZAZIONE DELLE ALTRE SERVLET DI
			// LIBRO
			if (percorso.contains("PrepareFindLibriServlet") || percorso.contains("ExecuteFindLibriServlet")
					|| percorso.contains("FindByIdLibroServlet") || percorso.contains("PrepareFindAutoriServlet")
					|| percorso.contains("ExecuteFindAutoriServlet") || percorso.contains("FindByIdAutoreServlet")) {
				// se il tentativo di accesso risponde a queste servlet
				chain.doFilter(request, response); // accedi
				return;
			} else {

				for (Ruolo r : utente.getRuoli()) {
					if (Codice.ADMIN_ROLE == r.getCodice() || Codice.CLASSIC_ROLE == r.getCodice()) {
						isAdminOrClassicUser = true;
					}
					if (isAdminOrClassicUser) {
						chain.doFilter(request, response); // accedi
						return;
					} else {
						httpServletResponse.sendRedirect(contesto); // altrimenti ritorna
					}
				}
			}
		}
	}
}
