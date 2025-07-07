package com.literalura.challenge.by.alura.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String birthYearh;
    private String deathYearh;
    @Override
    public String toString() {
        return "Autor [id=" + id + ", name=" + name + ", birthYearh=" + birthYearh + ", deathYearh=" + deathYearh + "]";
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
    public String getBirthYearh() {
        return birthYearh;
    }
    public void setBirthYearh(String birthYearh) {
        this.birthYearh = birthYearh;
    }
    public String getDeathYearh() {
        return deathYearh;
    }
    public void setDeathYearh(String deathYearh) {
        this.deathYearh = deathYearh;
    }


    


    
}
