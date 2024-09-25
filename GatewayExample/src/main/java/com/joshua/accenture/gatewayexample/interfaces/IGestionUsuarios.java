package com.joshua.accenture.gatewayexample.interfaces;

import com.joshua.accenture.gatewayexample.entities.Usuarios;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IGestionUsuarios {
    List<Usuarios> findAll();
    Optional<Usuarios> findById(Long id);
    Usuarios save(Usuarios usuario);
    void deleteById(Long id);
    Optional<List<Usuarios>> findByNombre(String name);
    Optional<Usuarios> findByEmail(String email);
    Optional<Usuarios> findByNombreAndEmail(String name, String email);
    List<Usuarios> findByFechaCreacion(LocalDateTime createdDate);
}
