package com.joshua.accenture.gatewayexample.repositories;

import com.joshua.accenture.gatewayexample.entities.Libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CatalogoLibrosDao extends JpaRepository<Libros, Long> {

    // Método para encontrar libros por título
    List<Libros> findByTitulo(String titulo);

    // Método para encontrar libros por formato
    List<Libros> findByFormato(String formato);

    // Método para encontrar libros publicados después de una fecha específica
    List<Libros> findByFechaPublicacionAfter(LocalDate fechaPublicacion);

    // Método para encontrar libros por ISBN
    Libros findByIsbn(String isbn);
}
