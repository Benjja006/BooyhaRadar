package com.example.Auth_Service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> manejarValidaciones(
            MethodArgumentNotValidException exception) {

        Map<String, String> errores = new HashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errores.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errores);
    }

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Map<String, String>> manejarNoEncontrado(
            RecursoNoEncontradoException exception) {

        Map<String, String> respuesta = new HashMap<>();

        respuesta.put("error", exception.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(respuesta);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> manejarConflicto(
            IllegalArgumentException exception) {

        Map<String, String> respuesta = new HashMap<>();

        respuesta.put("error", exception.getMessage());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(respuesta);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> manejarErrorGeneral(
            Exception exception) {

        Map<String, String> respuesta = new HashMap<>();

        respuesta.put("error", "Error interno del servidor");

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(respuesta);
    }
}