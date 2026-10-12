
package com.example.Statistics_Service.repository;

import com.example.Statistics_Service.model.Estadistica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EstadisticaRepository
        extends JpaRepository<Estadistica, Long> {

    List<Estadistica> findByJugadorId(Long jugadorId);

    Optional<Estadistica> findFirstByJugadorIdOrderByFechaActualizacionDesc(
            Long jugadorId);
}
