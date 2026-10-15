package com.example.Scouting_Service.service;

import com.example.Scouting_Service.model.Favorito;
import com.example.Scouting_Service.repository.FavoritoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoritoService {

    private final FavoritoRepository favoritoRepository;

    public FavoritoService(FavoritoRepository favoritoRepository) {
        this.favoritoRepository = favoritoRepository;
    }

    public Favorito agregar(Long usuarioId, Long jugadorId) {
        if (usuarioId == null || jugadorId == null) {
            throw new IllegalArgumentException(
                    "El ID del usuario y del jugador son obligatorios");
        }

        if (favoritoRepository.existsByUsuarioIdAndJugadorId(
                usuarioId, jugadorId)) {
            throw new IllegalArgumentException(
                    "El jugador ya está en los favoritos del usuario");
        }

        Favorito favorito = new Favorito();
        favorito.setUsuarioId(usuarioId);
        favorito.setJugadorId(jugadorId);

        return favoritoRepository.save(favorito);
    }

    public List<Favorito> listarPorUsuario(Long usuarioId) {
        return favoritoRepository.findByUsuarioId(usuarioId);
    }

    public void eliminar(Long usuarioId, Long jugadorId) {
        Favorito favorito = favoritoRepository
                .findByUsuarioIdAndJugadorId(usuarioId, jugadorId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No se encontró ese jugador entre los favoritos"));

        favoritoRepository.delete(favorito);
    }
}
