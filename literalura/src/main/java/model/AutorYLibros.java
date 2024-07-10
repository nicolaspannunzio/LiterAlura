package com.alura.literalura.model;

import java.util.ArrayList;
import java.util.List;

public class AutorYLibros {
    private Persona autor;
    private List<Libro> libros;

    public AutorYLibros(Persona autor, Libro libro){
        this.autor = autor;
        this.libros = new ArrayList<>();
        this.libros.add(libro);
    }

    public Persona getAutor(){
        return autor;
    }

    public List<Libro> getLibros(){
        return libros;
    }

    @Override
    public String toString() {
        return "AutorYLibros{" +
                "autor=" + autor +
                ", libros=" + libros +
                '}';
    }
}