package com.literalura.challenge.by.alura.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RespuestaLibro(@JsonAlias("results") List<DatosLibro> results) {
    
}
