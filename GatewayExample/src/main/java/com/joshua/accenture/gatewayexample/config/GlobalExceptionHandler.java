package com.joshua.accenture.gatewayexample.config;

import org.apache.coyote.BadRequestException;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import static org.springframework.http.HttpStatus.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Clase global para manejar excepciones personalizadas.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja excepciones de tipo ResourceNotFoundException.
     *
     * @param ex la excepción lanzada
     * @param request la solicitud web
     * @return una respuesta con el mensaje de error y el estado HTTP 404
     */
    @ExceptionHandler(ConfigDataResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ConfigDataResourceNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), NOT_FOUND);
    }

    /**
     * Maneja excepciones de tipo BadRequestException.
     *
     * @param ex la excepción lanzada
     * @param request la solicitud web
     * @return una respuesta con el mensaje de error y el estado HTTP 400
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(BadRequestException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), BAD_REQUEST);
    }

    /**
     * Maneja todas las demás excepciones.
     *
     * @param ex la excepción lanzada
     * @param request la solicitud web
     * @return una respuesta con el mensaje de error y el estado HTTP 500
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), INTERNAL_SERVER_ERROR);
    }
}