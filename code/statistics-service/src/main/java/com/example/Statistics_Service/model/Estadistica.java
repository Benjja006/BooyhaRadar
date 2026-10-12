
package com.example.Statistics_Service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "estadisticas")
@Getter
@Setter
@NoArgsConstructor
public class Estadistica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El ID del jugador es obligatorio")
    private Long jugadorId;

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

    private LocalDateTime fechaActualizacion;
}
