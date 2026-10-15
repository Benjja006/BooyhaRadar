package com.example.Scouting_Service.repository;

import com.example.Scouting_Service.model.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoritoRepository extends JpaRepository<Favorito, Long> {

    List<Favorito> findByUsuarioId(Long usuarioId);

    Optional<Favorito> findByUsuarioIdAndJugadorId(
            Long usuarioId,
            Long jugadorId
    );

    boolean existsByUsuarioIdAndJugadorId(
            Long usuarioId,
            Long jugadorId
    );
}
