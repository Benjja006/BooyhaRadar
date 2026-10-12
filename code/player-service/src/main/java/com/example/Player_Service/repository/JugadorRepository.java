package com.example.Player_Service.repository;

import com.example.Player_Service.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JugadorRepository extends JpaRepository<Jugador, Long> {

    Optional<Jugador> findByNickname(String nickname);

    Optional<Jugador> findByUid(String uid);

    boolean existsByNickname(String nickname);

    boolean existsByUid(String uid);
}