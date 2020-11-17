package it.bibliotecaweb.service.libro;

import java.util.Set;

import it.bibliotecaweb.dao.libro.LibroDAO;
import it.bibliotecaweb.model.autore.Autore;
import it.bibliotecaweb.model.libro.Libro;

public interface LibroService {
	//per injection
	void setLibroDAO(LibroDAO libroDAO);
	
	public Set<Libro> setAll() throws Exception;

	public Libro trova(Long id) throws Exception;

	public boolean aggiorna(Libro libroInstance) throws Exception;

	public boolean inserisciNuovo(Libro libroInstance) throws Exception;

	public boolean rimuovi(Libro libroInstance) throws Exception;

	boolean aggiungiAutore(Libro libroEsistente, Autore autoreEsistente) throws Exception;

	Set<Libro> trovaLibro(Libro libroInstance) throws Exception;


}
