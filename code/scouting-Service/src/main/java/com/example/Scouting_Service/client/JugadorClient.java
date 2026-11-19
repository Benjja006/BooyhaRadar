package com.example.Scouting_Service.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Map;

@Component
public class JugadorClient {

    private final RestClient restClient;

    public JugadorClient(
            @Value("${services.player.url}") String url) {
        this.restClient = RestClient.builder()
                .baseUrl(url)
                .build();
    }

    public List<Map<String, Object>> listarJugadores() {
        List<Map<String, Object>> jugadores = restClient.get()
                .uri("/jugadores")
                .retrieve()
                .body(new org.springframework.core.ParameterizedTypeReference<>() {});

        return jugadores != null ? jugadores : List.of();
    }

    public boolean existeJugador(Long jugadorId) {
        try {
            restClient.get()
                    .uri("/jugadores/{id}", jugadorId)
                    .retrieve()
                    .toBodilessEntity();
            return true;
        } catch (HttpClientErrorException.NotFound excepcion) {
            return false;
        }
    }
}
