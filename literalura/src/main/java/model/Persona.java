package com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "autores")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generacion del Id
    private Long Id;
    private String nombre;
    private Integer nacimiento;
    private Integer muerte;
    @ManyToOne
    private Libro libro;

    public Persona(){}

    public Persona(DatosPersona d) {
        this.nombre = d.nombre();
        this.nacimiento = d.fechaDeNacimiento();
        this.muerte = d.fechaDeMuerte();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Integer nacimiento) {
        this.nacimiento = nacimiento;
    }

    public Integer getMuerte() {
        return muerte;
    }

    public void setMuerte(Integer muerte) {
        this.muerte = muerte;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    @Override
    public String toString() {
        return
                "Autor: "+nombre+'\n'+
                        "Fecha de nacimiento: "+nacimiento+'\n'+
                        "Fecha de fallecimiento: "+muerte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(nombre, persona.nombre) && Objects.equals(nacimiento, persona.nacimiento) && Objects.equals(muerte, persona.muerte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, nacimiento, muerte);
    }


}