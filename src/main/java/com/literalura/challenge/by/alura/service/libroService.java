package com.literalura.challenge.by.alura.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.literalura.challenge.by.alura.models.Libro;
import com.literalura.challenge.by.alura.repository.libroRepsitory;

@Service
public class libroService{
    
    @Autowired
    private libroRepsitory repo;

    public Libro save(Libro libro){
        return repo.save(libro);
    }

    public List<Libro> todosLibros(){
        return repo.findAll();
    }

    public List<Libro> libroPorNombre(String nombre){
        return repo.findByTituloIgnoreCase(nombre);
    }



}
