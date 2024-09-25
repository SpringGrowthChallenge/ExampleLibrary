package com.joshua.accenture.gatewayexample.services;

import com.joshua.accenture.gatewayexample.entities.Libros;
import com.joshua.accenture.gatewayexample.interfaces.IGestionLibros;
import com.joshua.accenture.gatewayexample.repositories.CatalogoLibrosDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class IGestionLibrosServiceImpl implements IGestionLibros {
    private final CatalogoLibrosDao catalogoLibrosDao;

    public IGestionLibrosServiceImpl(CatalogoLibrosDao catalogoLibrosDao) {
        this.catalogoLibrosDao = catalogoLibrosDao;
    }

    @Override
    public List<Libros> findAll() {
        return catalogoLibrosDao.findAll();
    }

    @Override
    public Optional<Libros> findById(Long id) {
        return catalogoLibrosDao.findById(id);
    }

    @Override
    public Libros save(Libros libro) {
        return catalogoLibrosDao.save(libro);
    }

    @Override
    public void deleteById(Long id) {
        catalogoLibrosDao.deleteById(id);
    }

    @Override
    public List<Libros> findByTitulo(String titulo) {
        return catalogoLibrosDao.findByTitulo(titulo);
    }

    @Override
    public List<Libros> findByFormato(String formato) {
        return catalogoLibrosDao.findByFormato(formato);
    }

    @Override
    public List<Libros> findByFechaPublicacionAfter(LocalDate fechaPublicacion) {
        return catalogoLibrosDao.findByFechaPublicacionAfter(fechaPublicacion);
    }

    @Override
    public Optional<Libros> findByIsbn(String isbn) {
        return Optional.ofNullable(catalogoLibrosDao.findByIsbn(isbn));
    }
}
