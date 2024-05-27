package cl.ucn.felix.biblioteca.api.impl;

import cl.ucn.felix.biblioteca.api.LibroMutable;

public class LibroMutableImpl implements LibroMutable{
    private String titulo;
    private String autor;
    private String categoria;
    private String isbn;

    public LibroMutableImpl(String isbn2) {
        this.isbn = isbn2;
    }
    // Getters y Setters
    public String getTitulo() { 
        return titulo;
     }
    public void setTitulo(String titulo) { 
        this.titulo = titulo; 
    }

    public String getAutor() { 
        return autor; 
    }
    public void setAutor(String autor) {
         this.autor = autor; 
    }

    public String getCategoria() {
         return categoria;
    }
    public void setCategoria(String categoria) {
         this.categoria = categoria; 
    }

    public String getIsbn() {
         return isbn; 
    }
    public void setIsbn(String isbn) {
         this.isbn = isbn; 
    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append(this.getTitulo()).append(" de ").append(this.getAutor());
        buf.append(" (").append(this.getCategoria()).append(")");
        return buf.toString();
    }
}
