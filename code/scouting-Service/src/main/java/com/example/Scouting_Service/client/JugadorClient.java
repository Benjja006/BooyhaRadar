package com.example.Scouting_Service.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Component
public class JugadorClient {

    private final RestClient restClient;

    public JugadorClient() {
        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:8082")
                .build();
    }

    public List<Map<String, Object>> listarJugadores() {
        List<Map<String, Object>> jugadores = restClient.get()
                .uri("/jugadores")
                .retrieve()
                .body(new org.springframework.core.ParameterizedTypeReference<>() {});

        return jugadores != null ? jugadores : List.of();
    }
}
