package com.joshua.accenture.gatewayexample.controllers;

import com.joshua.accenture.gatewayexample.entities.Libros;
import com.joshua.accenture.gatewayexample.interfaces.IGestionLibros;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para gestionar los libros.
 */
@RestController
@RequestMapping("/api/libros")
public class LibrosController {

    private final IGestionLibros gestionLibros;

    /**
     * Constructor para inyectar la dependencia de IGestionLibros.
     *
     * @param gestionLibros la implementación de IGestionLibros
     */
    public LibrosController(IGestionLibros gestionLibros) {
        this.gestionLibros = gestionLibros;
    }

    /**
     * Obtiene todos los libros.
     *
     * @return una lista de todos los libros
     */
    @GetMapping("/all")
    @CircuitBreaker(name = "default", fallbackMethod = "fallback")
    public List<Libros> getAllLibros() {
        return gestionLibros.findAll();
    }

    /**
     * Obtiene un libro por su ID.
     *
     * @param id el ID del libro
     * @return el libro correspondiente al ID, o 404 si no se encuentra
     */
    @GetMapping("/{id}")
    @CircuitBreaker(name = "default", fallbackMethod = "fallback")
    public ResponseEntity<Libros> getLibroById(@PathVariable Long id) {
        Optional<Libros> libro = gestionLibros.findById(id);
        return libro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Crea un nuevo libro.
     *
     * @param libro el libro a crear
     * @return el libro creado
     */
    @PostMapping
    @CircuitBreaker(name = "default", fallbackMethod = "fallback")
    public Libros createLibro(@RequestBody Libros libro) {
        return gestionLibros.save(libro);
    }

    /**
     * Actualiza un libro existente.
     *
     * @param id el ID del libro a actualizar
     * @param libroDetails los detalles del libro a actualizar
     * @return el libro actualizado, o 404 si no se encuentra
     */
    @PutMapping("/{id}")
    @CircuitBreaker(name = "default", fallbackMethod = "fallback")
    public ResponseEntity<Libros> updateLibro(@PathVariable Long id, @RequestBody Libros libroDetails) {
        Optional<Libros> libro = gestionLibros.findById(id);
        if (libro.isPresent()) {
            Libros updatedLibro = libro.get();
            updatedLibro.setTitulo(libroDetails.getTitulo());
            updatedLibro.setDescripcion(libroDetails.getDescripcion());
            updatedLibro.setPortadaUrl(libroDetails.getPortadaUrl());
            updatedLibro.setFormato(libroDetails.getFormato());
            updatedLibro.setFechaPublicacion(libroDetails.getFechaPublicacion());
            gestionLibros.save(updatedLibro);
            return ResponseEntity.ok(updatedLibro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Elimina un libro por su ID.
     *
     * @param id el ID del libro a eliminar
     * @return 204 si se elimina correctamente, o 404 si no se encuentra
     */
    @DeleteMapping("/{id}")
    @CircuitBreaker(name = "default", fallbackMethod = "fallback")
    public ResponseEntity<Void> deleteLibro(@PathVariable Long id) {
        if (gestionLibros.findById(id).isPresent()) {
            gestionLibros.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Busca libros por título.
     *
     * @param titulo el título del libro
     * @return una lista de libros que coinciden con el título
     */
    @GetMapping("/searchByTitulo")
    @CircuitBreaker(name = "default", fallbackMethod = "fallback")
    public ResponseEntity<List<Libros>> findByTitulo(@RequestParam String titulo) {
        List<Libros> libros = gestionLibros.findByTitulo(titulo);
        return ResponseEntity.ok(libros);
    }

    /**
     * Busca libros por formato.
     *
     * @param formato el formato del libro
     * @return una lista de libros que coinciden con el formato
     */
    @GetMapping("/searchByFormato")
    @CircuitBreaker(name = "default", fallbackMethod = "fallback")
    public ResponseEntity<List<Libros>> findByFormato(@RequestParam String formato) {
        List<Libros> libros = gestionLibros.findByFormato(formato);
        return ResponseEntity.ok(libros);
    }

    /**
     * Busca libros publicados después de una fecha específica.
     *
     * @param fechaPublicacion la fecha de publicación
     * @return una lista de libros publicados después de la fecha especificada
     */
    @GetMapping("/searchByFechaPublicacion")
    @CircuitBreaker(name = "default", fallbackMethod = "fallback")
    public ResponseEntity<List<Libros>> findByFechaPublicacionAfter(@RequestParam LocalDate fechaPublicacion) {
        List<Libros> libros = gestionLibros.findByFechaPublicacionAfter(fechaPublicacion);
        return ResponseEntity.ok(libros);
    }

    /**
     * Busca un libro por su ISBN.
     *
     * @param isbn el ISBN del libro
     * @return el libro correspondiente al ISBN, o 404 si no se encuentra
     */
    @GetMapping("/searchByIsbn")
    @CircuitBreaker(name = "default", fallbackMethod = "fallback")
    public ResponseEntity<Libros> findByIsbn(@RequestParam String isbn) {
        Optional<Libros> libro = gestionLibros.findByIsbn(isbn);
        return libro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Método de fallback para manejar errores.
     *
     * @param t la excepción lanzada
     * @return una respuesta de error 500
     */
    public ResponseEntity<String> fallback(Throwable t) {
        return ResponseEntity.status(500).body("Service is currently unavailable. Please try again later.");
    }
}