package com.literalura.challenge.by.alura.principal;

import com.literalura.challenge.by.alura.models.Autor;
import com.literalura.challenge.by.alura.models.DatosAutor;
import com.literalura.challenge.by.alura.models.DatosLibro;
import com.literalura.challenge.by.alura.models.Libro;
import com.literalura.challenge.by.alura.models.RespuestaLibro;
import com.literalura.challenge.by.alura.service.ConsumoAPI;
import com.literalura.challenge.by.alura.service.autorService;
import com.literalura.challenge.by.alura.service.convierteDatos;
import com.literalura.challenge.by.alura.service.libroService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private convierteDatos conversor = new convierteDatos();
    private libroService libroService;
    private autorService autorService;
    private List <Libro> librosEncontrados = new ArrayList<>();
    private List <Autor> autores = new ArrayList<>();


    public Principal(libroService libroService, autorService autorService) {
        this.libroService = libroService;
        this.autorService=autorService;
    }


    public void getMain(){

        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libros y añadirlos a la biblioteca 
                    2 - Consultar libro 
                    3 - Mostrar listado de libros
                    4 - Mostrar lista de autores   
                    5 - Autores vivos en un rango de años   
                    6 - Listar libros por idioma (EN(english)/ ES(español))
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibros();
                    break;
                case 2:
                    buscarLibroPorTitulo();
                    break;
                case 3:
                    obtenerTodosLibros();
                    break;
                case 4:
                    obtenerAutores();
                    break;
                case 5:
                    autoresvivos();
                    break;
                case 6:
                    librosPorIdioma();
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }


    }


    private void librosPorIdioma() {
        System.out.println("Ingrese el idioma (EN/ES):");
        String idioma = teclado.nextLine().toUpperCase();
        List<Libro> libros= libroService.buscarPorIdioma(idioma);


        libros.stream().map(libro->"Titulo: "+libro.getTitulo() +
            "\nAutor: "+libro.getAutor()+
            "\nIdioma: "+libro.getIdioma()+
            "\nTota de descargas: "+libro.getTotalDescargas()+
            "\n-----------------").forEach(System.out::println);;


    }


    private void autoresvivos() {
        System.out.println("Ingrese el año para buscar autores: ");
        int año = teclado.nextInt();
        List<Autor> autores=autorService.findByYear(año);
        System.out.println("LOS AUTORES VIVOS EN ESE AÑO SON: ");
        autores.stream()
        .map(a->"Nombre: " + a.getName()
        +"\nFecha de nacimiento: "+a.getBirthYear() + "\nFecha de defunción: "+ a.getDeathYear()+"\n-----------------------------------------------------").forEach(System.out::println);
    }


    private void obtenerAutores() {
        List <Autor> autores=autorService.findAll();
        System.out.println("------------------------------Autores registrados------------------------------------");
        autores.stream().map(a->"Nombre: " + a.getName()
        +"\nFecha de nacimiento: "+a.getBirthYear() + "\nFecha de defunción: "+ a.getDeathYear()+"\n-----------------------------------------------------").forEach(System.out::println);

    }


    private void obtenerTodosLibros() {
        
        List<Libro> libros= libroService.todosLibros();
        System.out.println("------------------------------------------------");
        libros.forEach(System.out::println);
    }


    private void buscarLibroPorTitulo() {
        System.out.println("Ingrese el título del libro que desea buscar: ");
        var titulo = teclado.nextLine();
        Libro libro=libroService.libroPorNombre(titulo);

        System.out.println("Lista de libros encontrados para: "+titulo);
        System.out.println("____________________________________________________________________________________");
        System.out.println("Titulo: "+libro.getTitulo() +
            "\nAutor: "+libro.getAutor()+
            "\nIdioma: "+libro.getIdioma()+
            "\nTota de descargas: "+libro.getTotalDescargas()+
            "\n-----------------");
        System.out.println("______________________________________________________________________________________");

    }


    private RespuestaLibro obtenerLibroApi(){
        System.out.println("Escribe el nombre del libro que deseas buscar:");
        var bookName=teclado.nextLine();
        var json=consumoApi.obtenerDatos(URL_BASE+bookName.replace(" ","%20"));
        System.out.println("JSON:");
        System.out.println(json);
        System.out.println("____________________________");
        var datos=conversor.obtenerDatos(json, RespuestaLibro.class);
        return datos;
    }

    private void buscarLibros() {
        RespuestaLibro respuesta=obtenerLibroApi();

        for(DatosLibro datosLibro : respuesta.results()){
            Libro libro=new Libro(datosLibro);
            librosEncontrados.add(libro);
            DatosAutor datosAutor = datosLibro.autores().stream().findFirst().orElse(null);
            Autor autor=new Autor(datosAutor.name(),datosAutor.deathYear(),datosAutor.birthYear());
            autores.add(autor);
        }

        Autor autorToSave= autores.stream()
        .findFirst()
        .orElse(null);
        Libro libroToSave=librosEncontrados.stream()
        .findFirst()
        .orElse(null);

        System.out.println(autorToSave);
        System.out.println(libroToSave);

        Autor autorGenerado= autorService.save(autorToSave);
        libroService.save(libroToSave, autorGenerado);





    }


}
