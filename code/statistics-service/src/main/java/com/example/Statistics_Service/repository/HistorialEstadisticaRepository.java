
package com.example.Statistics_Service.repository;

import com.example.Statistics_Service.model.HistorialEstadistica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistorialEstadisticaRepository
        extends JpaRepository<HistorialEstadistica, Long> {

    List<HistorialEstadistica> findByJugadorIdOrderByFechaActualizacionDesc(
            Long jugadorId);
}
