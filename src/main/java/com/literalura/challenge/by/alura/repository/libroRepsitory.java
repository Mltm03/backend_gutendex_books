package com.literalura.challenge.by.alura.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.literalura.challenge.by.alura.models.Libro;
import java.util.List;


public interface libroRepsitory extends JpaRepository <Libro, Long>{


    List<Libro> findByTituloIgnoreCase(String titulo);
}
