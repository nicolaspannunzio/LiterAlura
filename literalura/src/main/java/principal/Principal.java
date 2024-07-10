package com.alura.literalura.principal;

import com.alura.literalura.model.*;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;
import org.hibernate.exception.ConstraintViolationException;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "http://gutendex.com/books/?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private List<Libro> libros;
    private List<Persona> autores;
    private LibroRepository repositorio;
    Map<String, List<AutorYLibros>> librosPorAutor;

    public Principal(LibroRepository repository) {
        this.repositorio = repository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    -----------------------------
                    Elíja la opción a través de su número
                    1 - Buscar libro por título 
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma  
                    0 - Salir
                    -------------------------------
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroWeb();
                    break;
                case 2:
                    mostrarLibrosRegistrados();
                    break;
                case 3:
                    mostrarAutoresRegistrados();
                    break;
                case 4:
                    autoresVivosPorFecha();
                    break;
                case 5:
                    buscarPorIdioma();
                    break;

                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private DatosLibro getDatosLibro() {
        System.out.println("Ingrese el nombre del libro que deseas buscar");
        var nombreLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE+nombreLibro.replace(" ","+"));
        DatosResultados datos = conversor.obtenerDatos(json, DatosResultados.class);
        return datos.resultados().get(0);
    }

    private void buscarLibroWeb() {
        try {
            DatosLibro datos = getDatosLibro();
            System.out.println(datos);
            Libro libro = new Libro(datos);
            List<DatosPersona> autores = new ArrayList<>();
            datos.autor().forEach(a -> autores.add(a));

            List<Persona> personas = autores.stream()
                    .map(a -> new Persona(a))
                    .collect(Collectors.toList());
            System.out.println(personas);
            libro.setAutores(personas);

            repositorio.save(libro);
            System.out.println(libro);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\n Libro no encontrado");
        } catch (Exception e) {
            System.out.println("El libro con el título ya existe en la base de datos.");
        }
    }

    private void mostrarLibrosRegistrados() {
        libros = repositorio.findAll();

        libros.stream()
                .sorted(Comparator.comparing(Libro::getTitulo))
                .forEach(System.out::println);
    }

    public void mostrarLibrosYAutor(Map<String, List<AutorYLibros>> librosPorAutor){
        librosPorAutor.forEach((na,lal) -> {
            System.out.println(lal.get(0).getAutor());
            System.out.println("Libros: ");
            lal.forEach(autorYLibros -> {
                autorYLibros.getLibros().forEach(l -> System.out.println(" - "+l.getTitulo()));
            });
            System.out.println();
        });
    }

    private void mostrarAutoresRegistrados(){
        libros = repositorio.findAll();
        List<Persona> autor = new ArrayList<>();
        libros.forEach(l -> autor.add(l.getAutor().get(0)));

        autores = autor.stream()
                .collect(Collectors.toMap(
                        Persona::getNombre,
                        persona -> persona,
                        (existing, replacement) -> existing
                ))
                .values()
                .stream()
                .collect(Collectors.toList());

        // Agrupar libros por el autor
        librosPorAutor = libros.stream()
                .flatMap(l -> l.getAutor().stream()
                        .map(a -> new AutorYLibros(a, l)))
                .collect(Collectors.groupingBy(
                        autorYLibros -> autorYLibros.getAutor().getNombre(),
                        Collectors.toList()
                ));

        //Imprimir los datos y los titulos
        mostrarLibrosYAutor(librosPorAutor);
    }

    public void autoresVivosPorFecha(){
        System.out.println("Ingrese el año vivo de autor(es) que desea buscar");
        var fechaVivo = teclado.nextLine();
        List<Persona> autoresVivos = repositorio.buscarPorFecha(fechaVivo);

        List<String> nombresAutoresVivos = autoresVivos.stream()
                .map(Persona::getNombre)
                .collect(Collectors.toList());

        Map<String,List<AutorYLibros>> librosFiltradosPorAutor = librosPorAutor.entrySet().stream()
                .filter(entry -> nombresAutoresVivos.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        mostrarLibrosYAutor(librosFiltradosPorAutor);
    }

    private void buscarPorIdioma(){
        System.out.println("Ingrese el idioma para buscar los libros:");
        System.out.println("es - Español");
        System.out.println("en - Inglés");
        System.out.println("fr - Francés");
        System.out.println("pt - Portugués");
        var idioma = teclado.nextLine();
        List<String> idiomas = new ArrayList<>();
        idiomas.add(idioma);
        List<Libro> librosPorIdioma = repositorio.buscarPorIdioma(idiomas);

        librosPorIdioma.forEach(System.out::println);
    }
}