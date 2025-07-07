package com.literalura.challenge.by.alura.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.literalura.challenge.by.alura.models.Libro;



public interface libroRepsitory extends JpaRepository <Libro, Long>{


    Libro findByTituloIgnoreCase(String titulo);

    List<Libro> findByIdiomaIgnoreCase(String idioma);
}
