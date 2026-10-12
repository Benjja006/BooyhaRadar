package com.example.Player_Service.controller;

import com.example.Player_Service.dto.JugadorRequest;
import com.example.Player_Service.model.Jugador;
import com.example.Player_Service.service.JugadorService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<Jugador> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                jugadorService.buscarPorId(id)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        jugadorService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}