package com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")

public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Gneracion del Id
    private Long Id;
    @Column(unique = true)
    private String titulo;
    private List<String> idioma;
    private Double numeroDeDescargas;
    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Persona> autor;

    public Libro(){}

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        this.idioma = datosLibro.idioma();
        this.numeroDeDescargas = datosLibro.numeroDeDescargas();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getIdioma() {
        return idioma;
    }

    public void setIdioma(List<String> idioma) {
        this.idioma = idioma;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public List<Persona> getAutor() {
        return autor;
    }

    public void setAutores(List<Persona> personas) {
        personas.forEach(e -> e.setLibro(this));
        this.autor = personas;
    }

    @Override
    public String toString() {
        String autores = autor.stream()
                .map(Persona::getNombre)
                .collect(Collectors.joining(", "));
        String idiomas = idioma.stream()
                .collect(Collectors.joining(", "));

        return "----- LIBRO -----\n" +
                "Titulo: "+titulo +'\n'+
                "Autor: " +autores+'\n'+
                "Idioma: "+idiomas+'\n'+
                "Número de descargas: "+numeroDeDescargas+'\n';
    }
}