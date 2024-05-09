package cl.ucn.felix.biblioteca.api.impl;

import java.util.HashMap;
import java.util.Map;

public class InventarioImpl {

	public static final String CATEGORIA_DEFECTO = "default";
	private Map<String, LibroMutable> libroByIsbn = new HashMap<String, LibroMutable>();
	private Map<String, Integer> categorias =  new HashMap<String, Integer>();
	
	public LibroMutable crearLibro(String isbn) {
		
		return new LibroMutableImpl(isbn);
	}
	
	
	public Set<String> buscarLibros(Map<CriterioBusqueda,String> criterio) {
		
		List<Libro> libros = new LinkedList<Libro>();
		libros.addAll(this.libroByIsbn.values());
		
		for (Map.Entry<CriterioBusqueda, String> criterios : criterio.entrySet()) 
		{	
			Iterator<Libro> it = libros.iterator();
			while (it.hasNext()) {
				Libro libro = it.next();
				switch (criterios.getKey()) {
					case AUTOR_LIKE: 
					if (!checkStringMatch(libro.getAutor(), 
							criterios.getValue())) {
						it.remove();
						continue;
					}
					
					break;
					
					case ISBN_LIKE:
					if (!checkStringMatch(libro.getIsbn(), 
							criterios.getValue())) {
						it.remove();
						continue;
					}
					
					break;
					case CATEGORIA_LIKE:
					if (!checkStringMatch(libro.getCategoria(), 
							criterios.getValue())) {
						it.remove();
						continue;
					}
					
					break;
					case TITULO_LIKE:
					if (!checkStringMatch(libro.getTitulo(), 
							criterios.getValue())) {
						it.remove();
						continue;
					}
					
					break;
					default:
					break;
				}
			}
		}
		
		HashSet<String> isbns = new HashSet<String>();
		for (Libro libro : libros) {
			isbns.add(libro.getIsbn());
		}
		
		return isbns;
		
	}
	
	private boolean checkStringMatch(String attr, String critVal)
	{
		if (attr == null) {
			return false;
		}
		attr = attr.toLowerCase();
		critVal = critVal.toLowerCase();
		boolean startsWith = critVal.startsWith("%");
		boolean endsWith = critVal.endsWith("%");
		if (startsWith && endsWith) {
			if (critVal.length()==1) {
				return true;
			}
			else {
				return attr.contains(
				critVal.substring(1, critVal.length() - 1));
			}
		}
		else if (startsWith) { 
			return attr.endsWith(critVal.substring(1));
		}
		else if (endsWith) {
			return attr.startsWith(
			critVal.substring(0, critVal.length() - 1));
		}
		else {
			return attr.equals(critVal);
		}
	}
	
}
