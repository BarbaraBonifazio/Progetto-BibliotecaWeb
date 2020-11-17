package it.bibliotecaweb.dao.libro;

import java.util.Set;

import it.bibliotecaweb.dao.IBaseDAO;
import it.bibliotecaweb.model.libro.Libro;

public interface LibroDAO extends IBaseDAO<Libro> {

	Set<Libro> findLibro(Libro libro) throws Exception;

}
