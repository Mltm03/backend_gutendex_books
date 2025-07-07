package com.literalura.challenge.by.alura.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.literalura.challenge.by.alura.models.Autor;
import com.literalura.challenge.by.alura.models.Libro;

import com.literalura.challenge.by.alura.repository.libroRepsitory;

import jakarta.transaction.Transactional;

@Service
public class libroService{
    
    @Autowired
    private libroRepsitory repo;

    @Transactional
    public Libro save(Libro libro, Autor autor){

        libro.setAutor(autor);
        return repo.save(libro);
    }

    public List<Libro> todosLibros(){
        return repo.findAll();
    }

    public Libro libroPorNombre(String nombre){
        return repo.findByTituloIgnoreCase(nombre);
    }

    public List<Libro> buscarPorIdioma(String idioma) {
        return repo.findByIdiomaIgnoreCase(idioma);
    }



}
