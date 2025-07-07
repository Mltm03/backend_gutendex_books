package com.literalura.challenge.by.alura.models;


import jakarta.persistence.*;

@Entity
@Table(name="libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;
    private String idioma;
    private Double totalDescargas;

    

    public Libro() {
    }


    public Libro(DatosLibro datos) {

        this.titulo = datos.titulo();
        this.autor = datos.autores().stream().findFirst().map(DatosAutor::name).orElse(null);
        this.idioma = datos.idioma().stream().findFirst().orElse(null);
        this.totalDescargas = datos.totalDescargas();

    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Double getTotalDescargas() {
        return totalDescargas;
    }

    public void setTotalDescargas(Double totalDescargas) {
        this.totalDescargas = totalDescargas;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", idioma='" + idioma + '\'' +
                ", totalDescargas=" + totalDescargas +
                '}';
    }
}
