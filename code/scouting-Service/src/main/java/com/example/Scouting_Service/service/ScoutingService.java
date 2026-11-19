package com.example.Scouting_Service.service;

import com.example.Scouting_Service.client.JugadorClient;
import com.example.Scouting_Service.client.EstadisticaClient;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;

@Service
public class ScoutingService {

    private final JugadorClient jugadorClient;
    private final EstadisticaClient estadisticaClient;

    public ScoutingService(
            JugadorClient jugadorClient,
            EstadisticaClient estadisticaClient) {
        this.jugadorClient = jugadorClient;
        this.estadisticaClient = estadisticaClient;
    }

    public List<Map<String, Object>> buscarJugadores(
            String pais,
            Integer edadMinima,
            Integer edadMaxima,
            String rol,
            String experiencia,
            Double kdMinimo,
            Double danoMinimo,
            Integer partidasMinimas) {

        List<Map<String, Object>> jugadores =
                jugadorClient.listarJugadores();

        return jugadores.stream()
                .filter(j -> coincideTexto(j.get("pais"), pais))
                .filter(j -> coincideEdad(
                        j.get("fechaNacimiento"), edadMinima, edadMaxima))
                .filter(j -> coincideRol(
                        j.get("rolPrincipal"), j.get("rolSecundario"), rol))
                .filter(j -> coincideTexto(
                        j.get("experienciaCompetitiva"), experiencia))
                .filter(j -> coincideEstadisticas(
                        j, kdMinimo, danoMinimo, partidasMinimas))
                .toList();
    }

    private boolean coincideEstadisticas(
            Map<String, Object> jugador,
            Double kdMinimo,
            Double danoMinimo,
            Integer partidasMinimas) {

        if (kdMinimo == null && danoMinimo == null && partidasMinimas == null) {
            return true;
        }

        Object id = jugador.get("id");
        if (id == null) {
            return false;
        }

        List<Map<String, Object>> estadisticas =
                estadisticaClient.buscarPorJugador(Long.valueOf(id.toString()));

        return estadisticas.stream().anyMatch(e ->
                cumpleMinimo(e.get("kd"), kdMinimo)
                        && cumpleMinimo(e.get("danoPromedio"), danoMinimo)
                        && cumpleMinimo(e.get("partidasJugadas"), partidasMinimas));
    }

    private boolean cumpleMinimo(Object valor, Number minimo) {
        if (minimo == null) {
            return true;
        }
        if (!(valor instanceof Number numero)) {
            return false;
        }
        return numero.doubleValue() >= minimo.doubleValue();
    }

    private boolean coincideTexto(Object valor, String filtro) {
        if (filtro == null || filtro.isBlank()) {
            return true;
        }

        return valor != null
                && valor.toString().toLowerCase()
                .contains(filtro.toLowerCase());
    }

    private boolean coincideEdad(
            Object fechaNacimiento,
            Integer edadMinima,
            Integer edadMaxima) {

        if (edadMinima == null && edadMaxima == null) {
            return true;
        }

        if (fechaNacimiento == null) {
            return false;
        }

        LocalDate nacimiento =
                LocalDate.parse(fechaNacimiento.toString());

        int edad = Period.between(nacimiento, LocalDate.now()).getYears();

        return (edadMinima == null || edad >= edadMinima)
                && (edadMaxima == null || edad <= edadMaxima);
    }

    private boolean coincideRol(
            Object rolPrincipal,
            Object rolSecundario,
            String rol) {

        if (rol == null || rol.isBlank()) {
            return true;
        }

        return coincideTexto(rolPrincipal, rol)
                || coincideTexto(rolSecundario, rol);
    }
}
