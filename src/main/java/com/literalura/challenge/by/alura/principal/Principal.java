package com.literalura.challenge.by.alura.principal;

import com.literalura.challenge.by.alura.models.DatosLibro;
import com.literalura.challenge.by.alura.models.Libro;
import com.literalura.challenge.by.alura.models.RespuestaLibro;
import com.literalura.challenge.by.alura.service.ConsumoAPI;
import com.literalura.challenge.by.alura.service.convierteDatos;
import com.literalura.challenge.by.alura.service.libroService;

import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private convierteDatos conversor = new convierteDatos();
    private libroService service;


    public Principal(libroService service) {
        this.service = service;
    }


    public void getMain(){

        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libros y añadirlos a la biblioteca 
                    2 - Consultar libro 
                    3 - Mostrar listado de libros
                    4 - Buscar series por título   
                    5 - Listar top 5   
                    6 - Buscar serie por categoria   
                    7 - Series con mas de 3 temporadas
                    8 - Series con evaluación mayor a 7.8     
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
                default:
                    System.out.println("Opción inválida");
            }
        }


    }


    private void obtenerTodosLibros() {
        
        List<Libro> libros= service.todosLibros();
        System.out.println("------------------------------------------------");
        libros.forEach(System.out::println);
    }


    private void buscarLibroPorTitulo() {
        System.out.println("Ingrese el título del libro que desea buscar: ");
        var titulo = teclado.nextLine();
        List<Libro> libros=service.libroPorNombre(titulo);

        System.out.println("Lista de libros encontrados para: "+titulo);
        System.out.println("____________________________________________________________________________________");
        libros.stream()
            .map(l->"Titulo: "+l.getTitulo() +
            "\nAutor: "+l.getAutor()+
            "\nIdioma: "+l.getIdioma()+
            "\nTotal de descargas: "+l.getTotalDescargas()+
            "\n-----------------")
            .forEach(System.out::println);
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
            service.save(libro);
            System.out.println(libro);
        }

  

    }


}
