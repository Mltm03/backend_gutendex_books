package com.literalura.challenge.by.alura.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.literalura.challenge.by.alura.models.Autor;
import com.literalura.challenge.by.alura.repository.autorRepository;

@Service
public class autorService {
    
    @Autowired
    private autorRepository repo;


    public Autor findByNameAndBirthYearAndDeathYear(String name,Integer birthYear, Integer deathYear){
       return  repo.findByNameAndBirthYearAndDeathYear(name, birthYear, deathYear);
    }

    public Autor save (Autor autor){

        Autor autorExiste=findByNameAndBirthYearAndDeathYear(autor.getName(), autor.getBirthYear(), autor.getDeathYear());

        if(autorExiste!=null){
            return autorExiste;
        }
        return repo.save(autor);

    }

    public List<Autor> findAll(){
        return repo.findAll();
    }

    public List<Autor> findByYear(int año) {
      return repo.findByYear(año);
    }


}
