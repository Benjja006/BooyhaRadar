package com.example.Scouting_Service.service;

import com.example.Scouting_Service.client.JugadorClient;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;

@Service
public class ScoutingService {

    private final JugadorClient jugadorClient;

    public ScoutingService(JugadorClient jugadorClient) {
        this.jugadorClient = jugadorClient;
    }

    public List<Map<String, Object>> buscarJugadores(
            String pais,
            Integer edadMinima,
            Integer edadMaxima,
            String rol,
            String experiencia) {

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
                .toList();
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
