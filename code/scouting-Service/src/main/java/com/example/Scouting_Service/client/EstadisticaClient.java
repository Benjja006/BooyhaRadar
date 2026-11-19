package com.example.Scouting_Service.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Component
public class EstadisticaClient {

    private final RestClient restClient;

    public EstadisticaClient(
            @Value("${services.statistics.url}") String url) {
        this.restClient = RestClient.builder()
                .baseUrl(url)
                .build();
    }

    public List<Map<String, Object>> buscarPorJugador(Long jugadorId) {
        List<Map<String, Object>> estadisticas = restClient.get()
                .uri("/estadisticas/jugador/{jugadorId}", jugadorId)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

        return estadisticas != null ? estadisticas : List.of();
    }
}
