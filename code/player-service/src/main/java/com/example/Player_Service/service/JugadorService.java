package com.example.Player_Service.service;

import com.example.Player_Service.dto.JugadorRequest;
import com.example.Player_Service.model.Jugador;
import com.example.Player_Service.repository.JugadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import com.example.Player_Service.dto.JugadorRequest;
@Service
public class JugadorService {

    private final JugadorRepository jugadorRepository;

    public JugadorService(JugadorRepository jugadorRepository) {
        this.jugadorRepository = jugadorRepository;
    }

    public Jugador guardar(JugadorRequest request) {

    if (jugadorRepository.existsByUsuarioId(request.getUsuarioId())) {
        throw new IllegalArgumentException("La cuenta ya tiene un perfil de jugador");
    }

    if (jugadorRepository.existsByNickname(request.getNickname())) {
        throw new IllegalArgumentException("El nickname ya está registrado");
    }

    if (jugadorRepository.existsByUid(request.getUid())) {
        throw new IllegalArgumentException("El UID ya está registrado");
    }

    Jugador jugador = new Jugador();

    jugador.setUsuarioId(request.getUsuarioId());
    jugador.setNickname(request.getNickname());
    jugador.setUid(request.getUid());
    jugador.setPais(request.getPais());
    jugador.setFechaNacimiento(request.getFechaNacimiento());
    jugador.setRolPrincipal(request.getRolPrincipal());
    jugador.setRolSecundario(request.getRolSecundario());
    jugador.setBiografia(request.getBiografia());
    jugador.setExperienciaCompetitiva(request.getExperienciaCompetitiva());
    jugador.setLogros(request.getLogros());
    jugador.setContacto(request.getContacto());
    jugador.setEstadoCompetitivo(request.getEstadoCompetitivo());

    return jugadorRepository.save(jugador);
}

    public List<Jugador> listar() {
        return jugadorRepository.findAll();
    }

    public Jugador actualizar(Long id, JugadorRequest request) {
        Jugador jugador = buscarPorId(id);

        if (jugador.getUsuarioId() != null
                && !jugador.getUsuarioId().equals(request.getUsuarioId())) {
            throw new IllegalArgumentException(
                    "No se puede cambiar la cuenta asociada al perfil");
        }

        if (jugador.getUsuarioId() == null
                && jugadorRepository.existsByUsuarioId(request.getUsuarioId())) {
            throw new IllegalArgumentException(
                    "La cuenta ya tiene un perfil de jugador");
        }

        jugador.setUsuarioId(request.getUsuarioId());

        if (jugadorRepository.existsByNicknameAndIdNot(
                request.getNickname(), id)) {
            throw new IllegalArgumentException("El nickname ya está registrado");
        }

        if (jugadorRepository.existsByUidAndIdNot(request.getUid(), id)) {
            throw new IllegalArgumentException("El UID ya está registrado");
        }

        jugador.setNickname(request.getNickname());
        jugador.setUid(request.getUid());
        jugador.setPais(request.getPais());
        jugador.setFechaNacimiento(request.getFechaNacimiento());
        jugador.setRolPrincipal(request.getRolPrincipal());
        jugador.setRolSecundario(request.getRolSecundario());
        jugador.setBiografia(request.getBiografia());
        jugador.setExperienciaCompetitiva(request.getExperienciaCompetitiva());
        jugador.setLogros(request.getLogros());
        jugador.setContacto(request.getContacto());
        jugador.setEstadoCompetitivo(request.getEstadoCompetitivo());

        return jugadorRepository.save(jugador);
    }

    public Jugador buscarPorId(Long id) {
        return jugadorRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Jugador no encontrado")
                );
    }

    public void eliminar(Long id) {
        buscarPorId(id);
        jugadorRepository.deleteById(id);
    }
}
