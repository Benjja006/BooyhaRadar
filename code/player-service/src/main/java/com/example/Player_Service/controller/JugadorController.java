package com.example.Player_Service.controller;

import com.example.Player_Service.dto.JugadorRequest;
import com.example.Player_Service.model.Jugador;
import com.example.Player_Service.service.JugadorService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import com.example.Player_Service.dto.JugadorRequest;
import jakarta.validation.Valid;


    @RestController
    @RequestMapping("/jugadores")
    public class JugadorController {

        private final JugadorService jugadorService;

        public JugadorController(JugadorService jugadorService) {
            this.jugadorService = jugadorService;
        }

    @PostMapping
    public ResponseEntity<Jugador> guardar(
            @Valid @RequestBody JugadorRequest request) {

        Jugador nuevoJugador = jugadorService.guardar(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevoJugador);
    }
    @GetMapping
    public ResponseEntity<List<Jugador>> listar() {
        return ResponseEntity.ok(jugadorService.listar());
    }

    @GetMapping ("/holaMundo")
    public String HolaMundo(){
        return "Hola Mundo";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jugador> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                jugadorService.buscarPorId(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jugador> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody JugadorRequest request) {

        return ResponseEntity.ok(jugadorService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        jugadorService.eliminar(id);

        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> manejarSolicitudInvalida(
            IllegalArgumentException excepcion) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(excepcion.getMessage());
    }
}
