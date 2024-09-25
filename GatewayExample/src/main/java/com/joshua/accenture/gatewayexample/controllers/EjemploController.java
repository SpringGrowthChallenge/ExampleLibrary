package com.joshua.accenture.gatewayexample.controllers;

import org.springframework.web.bind.annotation.*;

/**
 * Clase controlador de ejemplo
 * la ruta se define desde el gateway pero aqui
 * la pongo en base de ejemplificar
 * */
@RestController
@RequestMapping("/api/libros")
public class EjemploController {

    /**
     * Metodo que nos permite usar los datos de nuestro filtro

     * */
    @GetMapping
    public String listar(@RequestParam(name = "nombreAtributoParametro", required = false) String name
            , @RequestHeader(name = "nombreAtributoHeader", required = false) String header){
        System.out.println("name = " + name);
        System.out.println("header ="+ header);
        return "Listado de libros";
    }

}
