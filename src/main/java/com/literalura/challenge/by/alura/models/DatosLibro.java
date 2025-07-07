package com.literalura.challenge.by.alura.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
    @JsonAlias("title")  String titulo,
    @JsonAlias("authors") List<DatosAutor> autores,
    @JsonAlias("languages") List <String> idioma,
    @JsonAlias("download_count") Double totalDescargas



) {



}
