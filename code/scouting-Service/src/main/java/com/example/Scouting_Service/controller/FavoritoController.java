package com.example.Scouting_Service.controller;

import com.example.Scouting_Service.model.Favorito;
import com.example.Scouting_Service.service.FavoritoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/favoritos")
public class FavoritoController {

    private final FavoritoService favoritoService;

    public FavoritoController(FavoritoService favoritoService) {
        this.favoritoService = favoritoService;
    }

    @PostMapping("/{usuarioId}/{jugadorId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Favorito agregar(
            @PathVariable Long usuarioId,
            @PathVariable Long jugadorId) {
        return favoritoService.agregar(usuarioId, jugadorId);
    }

    @GetMapping("/{usuarioId}")
    public List<Favorito> listarPorUsuario(
            @PathVariable Long usuarioId) {
        return favoritoService.listarPorUsuario(usuarioId);
    }

    @DeleteMapping("/{usuarioId}/{jugadorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(
            @PathVariable Long usuarioId,
            @PathVariable Long jugadorId) {
        favoritoService.eliminar(usuarioId, jugadorId);
    }

    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> manejarSolicitudInvalida(
            IllegalArgumentException excepcion) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(excepcion.getMessage());
    }

}
