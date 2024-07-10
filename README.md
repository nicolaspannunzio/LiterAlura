# **Alura Latam & ONE - Oracle Next Education**
# Challenge: LiterAlura 📖

## Descripción

En este emocionante desafío de programación, consistió en construir un catálogo propio de libros: LiterAlura. Se realizaron solicitudes a una API de libros, se manipular datos JSON, se guardarlos en una base de datos y, finalmente, se filtraron y se mostraron los libros y autores de interés.

## Objetivo

Desarrollar un catálogo de libros que ofrezca interacción textual (vía consola) con los usuarios, proporcionando al menos 5 opciones de interacción.

## Características del Proyecto

-   **Interacción con el Usuario:** Menú interactivo con al menos 5 opciones.
-   **Consumo de API:** Uso de la API Gutendex para buscar libros.
-   **Manipulación de Datos:** Análisis de la respuesta JSON usando la biblioteca Jackson.
-   **Base de Datos:** Inserción y consulta de datos usando Spring Data JPA y PostgreSQL.
-   **Exhibición de Resultados:** Filtrado y presentación de datos en la consola.

## Requisitos del proyecto para su desarrollo

### Programas y Versiones

-   **Java JDK:** 17
-   **Maven:** 4
-   **Spring Boot:** 3.2.3
-   **PostgreSQL:** 16
-   **IDE:** IntelliJ IDEA

### Configuración del Proyecto

-   **Spring Initializr:**
    -   Java 17
    -   Maven
    -   Spring Boot 3.2.3
    -   Proyecto en JAR

### Dependencias

-   **Spring Data JPA**
-   **Postgres Driver**
-   **Jackson (versión 2.16)**

## Pasos para Completar el Desafío

1.  **Configuración del Ambiente Java**
2.  **Creación del Proyecto**
3.  **Consumo de la API**
4.  **Análisis de la Respuesta JSON**
5.  **Inserción y Consulta en la Base de Datos**
6.  **Exhibición de Resultados a los Usuarios**

## Uso de la API Gutendex

La API Gutendex es un catálogo de información de más de 70,000 libros del Project Gutenberg. No se requiere clave de acceso para realizar consultas.

### Clases y Métodos Clave

-   **HttpClient:** Para realizar solicitudes a la API.
-   **HttpRequest:** Para configurar y personalizar solicitudes.
-   **HttpResponse:** Para gestionar respuestas recibidas de la API.
-   **Jackson:** Para el mapeo de datos JSON a objetos Java.

### Funcionalidades del proyecto

1.  **Búsqueda de libros por título**
2.  **Listado de todos los libros**
3.  **Lista de autores**
4.  **Listar autores vivos en un determinado año**
5.  **Estadísticas de cantidad de libros por idioma**

## Ejemplo de Consulta

Se realizó la consulta de un libro por título en la API Gutendex usando la URL base `https://gutendex.com/books/`. 
Un libro debía tener las siguientes características:

-   Título
-   Autor
-   Idiomas
-   Número de Descargas

Los autores tendrán:

-   Nombre
-   Año de nacimiento
-   Año de fallecimiento
----
***Contacto***

Nicolás A. Pannunzio - Desarrollador de este proyecto.

*contacto: nicolas.a.pannunzio@gmail.com*
