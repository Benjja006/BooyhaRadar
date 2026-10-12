
package com.example.Statistics_Service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class ActualizarEstadisticaRequest {

    @NotNull(message = "El K/D es obligatorio")
    @PositiveOrZero(message = "El K/D no puede ser negativo")
    private Double kd;

    @NotNull(message = "El daño promedio es obligatorio")
    @PositiveOrZero(message = "El daño promedio no puede ser negativo")
    private Double danoPromedio;

    @NotNull(message = "Las partidas jugadas son obligatorias")
    @Min(value = 0, message = "Las partidas no pueden ser negativas")
    private Integer partidasJugadas;

    @NotNull(message = "Las kills son obligatorias")
    @Min(value = 0, message = "Las kills no pueden ser negativas")
    private Integer kills;

    public Double getKd() {
        return kd;
    }

    public void setKd(Double kd) {
        this.kd = kd;
    }

    public Double getDanoPromedio() {
        return danoPromedio;
    }

    public void setDanoPromedio(Double danoPromedio) {
        this.danoPromedio = danoPromedio;
    }

    public Integer getPartidasJugadas() {
        return partidasJugadas;
    }

    public void setPartidasJugadas(Integer partidasJugadas) {
        this.partidasJugadas = partidasJugadas;
    }

    public Integer getKills() {
        return kills;
    }

    public void setKills(Integer kills) {
        this.kills = kills;
    }
}
