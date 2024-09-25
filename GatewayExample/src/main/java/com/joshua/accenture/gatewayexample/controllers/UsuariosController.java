package com.joshua.accenture.gatewayexample.controllers;

import com.joshua.accenture.gatewayexample.entities.Usuarios;
import com.joshua.accenture.gatewayexample.interfaces.IGestionUsuarios;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para gestionar los usuarios.
 */
@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController {

    private IGestionUsuarios gestionUsuarios;

    /**
     * Constructor para inyectar la dependencia de IGestionUsuarios.
     *
     * @param gestionUsuarios la implementación de IGestionUsuarios
     */
    public UsuariosController(IGestionUsuarios gestionUsuarios) {
        this.gestionUsuarios = gestionUsuarios;
    }

    /**
     * Obtiene todos los usuarios.
     *
     * @return una lista de todos los usuarios
     */
    @GetMapping
    @CircuitBreaker(name = "default", fallbackMethod = "fallback")
    public List<Usuarios> getAllUsuarios() {
        return gestionUsuarios.findAll();
    }

    /**
     * Obtiene un usuario por su ID.
     *
     * @param id el ID del usuario
     * @return el usuario correspondiente al ID, o 404 si no se encuentra
     */
    @GetMapping("/{id}")
    @CircuitBreaker(name = "default", fallbackMethod = "fallback")
    public ResponseEntity<Usuarios> getUsuarioById(@PathVariable Long id) {
        Optional<Usuarios> usuario = gestionUsuarios.findById(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Crea un nuevo usuario.
     *
     * @param usuario el usuario a crear
     * @return el usuario creado
     */
    @PostMapping
    @CircuitBreaker(name = "default", fallbackMethod = "fallback")
    public Usuarios createUsuario(@RequestBody Usuarios usuario) {
        return gestionUsuarios.save(usuario);
    }

    /**
     * Actualiza un usuario existente.
     *
     * @param id el ID del usuario a actualizar
     * @param usuarioDetails los detalles del usuario a actualizar
     * @return el usuario actualizado, o 404 si no se encuentra
     */
    @PutMapping("/{id}")
    @CircuitBreaker(name = "default", fallbackMethod = "fallback")
    public ResponseEntity<Usuarios> updateUsuario(@PathVariable Long id, @RequestBody Usuarios usuarioDetails) {
        Optional<Usuarios> usuario = gestionUsuarios.findById(id);
        if (usuario.isPresent()) {
            Usuarios updatedUsuario = usuario.get();
            updatedUsuario.setNombre(usuarioDetails.getNombre());
            updatedUsuario.setEmail(usuarioDetails.getEmail());
            updatedUsuario.setPassword(usuarioDetails.getPassword());
            updatedUsuario.setEnabled(usuarioDetails.isEnabled());
            gestionUsuarios.save(updatedUsuario);
            return ResponseEntity.ok(updatedUsuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Elimina un usuario por su ID.
     *
     * @param id el ID del usuario a eliminar
     * @return 204 si se elimina correctamente, o 404 si no se encuentra
     */
    @DeleteMapping("/{id}")
    @CircuitBreaker(name = "default", fallbackMethod = "fallback")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        if (gestionUsuarios.findById(id).isPresent()) {
            gestionUsuarios.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Busca usuarios por nombre.
     *
     * @param name el nombre del usuario
     * @return una lista de usuarios que coinciden con el nombre
     */
    @GetMapping("/search")
    @CircuitBreaker(name = "default", fallbackMethod = "fallback")
    public ResponseEntity<List<Usuarios>> findByName(@RequestParam String name) {
        Optional<List<Usuarios>> usuarios = gestionUsuarios.findByNombre(name);
        return usuarios.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Busca un usuario por email.
     *
     * @param email el email del usuario
     * @return el usuario correspondiente al email, o 404 si no se encuentra
     */
    @GetMapping("/searchByEmail")
    @CircuitBreaker(name = "default", fallbackMethod = "fallback")
    public ResponseEntity<Usuarios> findByEmail(@RequestParam String email) {
        Optional<Usuarios> usuario = gestionUsuarios.findByEmail(email);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Busca un usuario por nombre y email.
     *
     * @param name el nombre del usuario
     * @param email el email del usuario
     * @return el usuario correspondiente al nombre y email, o 404 si no se encuentra
     */
    @GetMapping("/searchByNameAndEmail")
    @CircuitBreaker(name = "default", fallbackMethod = "fallback")
    public ResponseEntity<Usuarios> findByNameAndEmail(@RequestParam String name, @RequestParam String email) {
        Optional<Usuarios> usuario = gestionUsuarios.findByNombreAndEmail(name, email);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Busca usuarios por fecha de creación.
     *
     * @param fechaCreacion la fecha de creación del usuario
     * @return una lista de usuarios que coinciden con la fecha de creación
     */
    @GetMapping("/searchByFechaCreacion")
    @CircuitBreaker(name = "default", fallbackMethod = "fallback")
    public ResponseEntity<List<Usuarios>> findByFechaCreacion(@RequestParam LocalDateTime fechaCreacion) {
        List<Usuarios> usuarios = gestionUsuarios.findByFechaCreacion(fechaCreacion);
        return ResponseEntity.ok(usuarios);
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