package com.example.Player_Service.service;

import com.example.Player_Service.dto.JugadorRequest;
import com.example.Player_Service.model.Jugador;
import com.example.Player_Service.repository.JugadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import com.example.Player_Service.dto.JugadorRequest;
@Service
public class JugadorService {

    private final JugadorRepository jugadorRepository;

    public JugadorService(JugadorRepository jugadorRepository) {
        this.jugadorRepository = jugadorRepository;
    }

    public Jugador guardar(JugadorRequest request) {

    if (jugadorRepository.existsByNickname(request.getNickname())) {
        throw new IllegalArgumentException("El nickname ya está registrado");
    }

    if (jugadorRepository.existsByUid(request.getUid())) {
        throw new IllegalArgumentException("El UID ya está registrado");
    }

    Jugador jugador = new Jugador();

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

    public Jugador buscarPorId(Long id) {
        return jugadorRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Jugador no encontrado")
                );
    }

    public void eliminar(Long id) {
        jugadorRepository.deleteById(id);
    }
}