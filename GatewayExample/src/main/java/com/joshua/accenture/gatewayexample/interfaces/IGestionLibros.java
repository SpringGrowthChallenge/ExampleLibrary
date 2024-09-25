package com.joshua.accenture.gatewayexample.interfaces;

import com.joshua.accenture.gatewayexample.entities.Libros;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IGestionLibros {
    List<Libros> findAll();
    Optional<Libros> findById(Long id);
    Libros save(Libros libro);
    void deleteById(Long id);
    List<Libros> findByTitulo(String titulo);
    List<Libros> findByFormato(String formato);
    List<Libros> findByFechaPublicacionAfter(LocalDate fechaPublicacion);
    Optional<Libros> findByIsbn(String isbn);

}
