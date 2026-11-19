package com.example.Player_Service.repository;

import com.example.Player_Service.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JugadorRepository extends JpaRepository<Jugador, Long> {

    Optional<Jugador> findByNickname(String nickname);

    Optional<Jugador> findByUid(String uid);

    boolean existsByNickname(String nickname);

    boolean existsByNicknameAndIdNot(String nickname, Long id);

    boolean existsByUid(String uid);

    boolean existsByUidAndIdNot(String uid, Long id);

    boolean existsByUsuarioId(Long usuarioId);

    boolean existsByUsuarioIdAndIdNot(Long usuarioId, Long id);
}
