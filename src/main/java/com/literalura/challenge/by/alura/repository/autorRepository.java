package com.literalura.challenge.by.alura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.literalura.challenge.by.alura.models.Autor;


public interface autorRepository extends JpaRepository <Autor, Long> {

    Autor findByNameAndBirthYearAndDeathYear(String nombreAutor, Integer birthYear,   Integer deathYear);

    @Query("""
    SELECT a FROM Autor a
    WHERE a.birthYear <= :year
    AND (a.deathYear IS NULL OR a.deathYear >= :year)
""")
    List<Autor> findByYear(Integer year);
    
} 
