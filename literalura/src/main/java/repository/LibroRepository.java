package com.alura.literalura.repository;

import com.alura.literalura.model.Libro;
import com.alura.literalura.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro,Long> {
    @Query("SELECT a FROM Persona a WHERE a.nacimiento <= :fechaVivo AND a.muerte >= :fechaVivo")
    List<Persona> buscarPorFecha(String fechaVivo);

    @Query("SELECT l FROM Libro l WHERE l.idioma = :idioma ")
    List<Libro> buscarPorIdioma(List<String> idioma);
}