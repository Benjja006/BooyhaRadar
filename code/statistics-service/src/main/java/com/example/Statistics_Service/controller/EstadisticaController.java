
package com.example.Statistics_Service.controller;

import com.example.Statistics_Service.dto.ActualizarEstadisticaRequest;
import com.example.Statistics_Service.model.Estadistica;
import com.example.Statistics_Service.model.HistorialEstadistica;
import com.example.Statistics_Service.repository.HistorialEstadisticaRepository;
import com.example.Statistics_Service.service.EstadisticaService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estadisticas")
public class EstadisticaController {

    private final EstadisticaService estadisticaService;
    private final HistorialEstadisticaRepository historialRepository;

    public EstadisticaController(
            EstadisticaService estadisticaService,
            HistorialEstadisticaRepository historialRepository) {
        this.estadisticaService = estadisticaService;
        this.historialRepository = historialRepository;
    }

    // Crear estadísticas
    @PostMapping
    public ResponseEntity<Estadistica> crear(
            @Valid @RequestBody Estadistica estadistica) {

        Estadistica creada = estadisticaService.crear(estadistica);

        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    // Listar todas las estadísticas
    @GetMapping
    public ResponseEntity<List<Estadistica>> listarTodas() {
        return ResponseEntity.ok(estadisticaService.listarTodas());
    }

    // Buscar estadísticas por jugador
    @GetMapping("/jugador/{jugadorId}")
    public ResponseEntity<List<Estadistica>> buscarPorJugador(
            @PathVariable Long jugadorId) {

        return ResponseEntity.ok(
                estadisticaService.buscarPorJugador(jugadorId));
    }

    // Consultar historial de estadísticas por jugador
    @GetMapping("/jugador/{jugadorId}/historial")
    public ResponseEntity<List<HistorialEstadistica>> historialPorJugador(
            @PathVariable Long jugadorId) {

        return ResponseEntity.ok(
                historialRepository
                        .findByJugadorIdOrderByFechaActualizacionDesc(
                                jugadorId));
    }

    // Buscar estadísticas por ID
    @GetMapping("/{id}")
    public ResponseEntity<Estadistica> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                estadisticaService.buscarPorId(id));
    }

    // Actualizar estadísticas de un jugador
    @PutMapping("/jugador/{jugadorId}")
    public ResponseEntity<Estadistica> actualizar(
            @PathVariable Long jugadorId,
            @Valid @RequestBody ActualizarEstadisticaRequest nuevasEstadisticas) {

        return ResponseEntity.ok(
                estadisticaService.actualizar(
                        jugadorId, nuevasEstadisticas));
    }

    // Eliminar estadísticas
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        estadisticaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // Manejar actualizaciones rechazadas por la regla de los 7 días
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> manejarActualizacionRechazada(
            IllegalStateException excepcion) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(excepcion.getMessage());
    }
}
