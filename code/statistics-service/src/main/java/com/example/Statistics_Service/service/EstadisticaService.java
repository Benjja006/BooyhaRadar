
package com.example.Statistics_Service.service;

import com.example.Statistics_Service.dto.ActualizarEstadisticaRequest;
import com.example.Statistics_Service.model.Estadistica;
import com.example.Statistics_Service.model.HistorialEstadistica;
import com.example.Statistics_Service.repository.EstadisticaRepository;
import com.example.Statistics_Service.repository.HistorialEstadisticaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class EstadisticaService {

    private final EstadisticaRepository estadisticaRepository;
    private final HistorialEstadisticaRepository historialRepository;

    public EstadisticaService(
            EstadisticaRepository estadisticaRepository,
            HistorialEstadisticaRepository historialRepository) {
        this.estadisticaRepository = estadisticaRepository;
        this.historialRepository = historialRepository;
    }

    public Estadistica crear(Estadistica estadistica) {
        estadistica.setId(null);
        estadistica.setFechaActualizacion(LocalDateTime.now());

        return estadisticaRepository.save(estadistica);
    }

    public List<Estadistica> listarTodas() {
        return estadisticaRepository.findAll();
    }

    public List<Estadistica> buscarPorJugador(Long jugadorId) {
        return estadisticaRepository.findByJugadorId(jugadorId);
    }

    public Estadistica buscarPorId(Long id) {
        return estadisticaRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "No se encontraron estadísticas con ID: " + id));
    }

    public void eliminar(Long id) {
        if (!estadisticaRepository.existsById(id)) {
            throw new IllegalArgumentException(
                    "No se encontraron estadísticas con ID: " + id);
        }

        estadisticaRepository.deleteById(id);
    }

    @Transactional
    public Estadistica actualizar(
            Long jugadorId,
            ActualizarEstadisticaRequest nuevasEstadisticas) {

        Estadistica actual = estadisticaRepository
                .findFirstByJugadorIdOrderByFechaActualizacionDesc(jugadorId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "El jugador no tiene estadísticas registradas"));

        LocalDateTime ahora = LocalDateTime.now();

        if (actual.getFechaActualizacion()
                .plusDays(7).isAfter(ahora)) {
            throw new IllegalStateException(
                    "Las estadísticas solo se pueden actualizar cada 7 días");
        }

        // Guardar una copia de las estadísticas anteriores.
        HistorialEstadistica historial = new HistorialEstadistica();

        historial.setJugadorId(actual.getJugadorId());
        historial.setEstadisticaId(actual.getId());
        historial.setKd(actual.getKd());
        historial.setDanoPromedio(actual.getDanoPromedio());
        historial.setPartidasJugadas(actual.getPartidasJugadas());
        historial.setKills(actual.getKills());
        historial.setFechaActualizacion(actual.getFechaActualizacion());

        historialRepository.save(historial);

        // Aplicar los nuevos valores.
        actual.setKd(nuevasEstadisticas.getKd());
        actual.setDanoPromedio(nuevasEstadisticas.getDanoPromedio());
        actual.setPartidasJugadas(
                nuevasEstadisticas.getPartidasJugadas());
        actual.setKills(nuevasEstadisticas.getKills());
        actual.setFechaActualizacion(ahora);

        return estadisticaRepository.save(actual);
    }
}
