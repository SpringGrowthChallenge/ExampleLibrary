package com.joshua.accenture.gatewayexample.repositories;

import com.joshua.accenture.gatewayexample.entities.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface IUsuariosDao extends JpaRepository<Usuarios, Long> {


    // Find users by name
    List<Usuarios> findByNombre(String name);
    // Find a user by email
    Optional<Usuarios> findByEmail(String email);

    Optional<Usuarios> findByNombreAndEmail(String name, String email);
    // Find users created after a specific date
    List<Usuarios> findByFechaCreacion(LocalDateTime createdDate);
}
