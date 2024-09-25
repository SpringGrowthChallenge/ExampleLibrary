package com.joshua.accenture.gatewayexample.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Libros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 13)
    private String isbn;

    @Column(nullable = false, length = 255)
    private String titulo;

    @Column(length = 1000)
    private String descripcion;

    @Column(name = "portada_url")
    private String portadaUrl;

    @Column(nullable = false)
    private String formato; // "físico" o "digital"

    @Column(name = "fecha_publicacion")
    private LocalDate fechaPublicacion;

    // Relación inversa con Usuario (opcional, depende de la lógica del negocio)
    @ManyToMany(mappedBy = "libros")
    private Set<Usuarios> usuarios;

    // Constructor adicional
    public Libros(String isbn, String titulo, String descripcion, String portadaUrl, String formato, LocalDate fechaPublicacion) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.portadaUrl = portadaUrl;
        this.formato = formato;
        this.fechaPublicacion = fechaPublicacion;
    }

}
