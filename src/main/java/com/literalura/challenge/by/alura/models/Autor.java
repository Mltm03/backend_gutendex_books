package com.literalura.challenge.by.alura.models;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer birthYear;
    private Integer deathYear;
    
    @OneToMany(mappedBy = "autor")
    private List<Libro> libros = new ArrayList<>();
    
    public Autor(String nombre,Integer deathyear,Integer birthyear) {
            this.name = nombre;
            this.birthYear=birthyear;
            this.deathYear=deathyear;
        }
    
       
    public Autor() {
        }

     public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }


    @Override
    public String toString() {
        return "Autor [id=" + id + ", name=" + name + ", birthYearh=" + birthYear + ", deathYearh=" + deathYear + "]";
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public List<Libro> getLibros() {
        return libros;
    }


    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }



    


    
}
