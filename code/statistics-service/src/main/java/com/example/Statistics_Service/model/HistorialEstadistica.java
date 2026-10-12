
package com.example.Statistics_Service.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "historial_estadisticas")
@Getter
@Setter
@NoArgsConstructor
public class HistorialEstadistica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long jugadorId;

    @Column(nullable = false)
    private Long estadisticaId;

    @Column(nullable = false)
    private Double kd;

    @Column(nullable = false)
    private Double danoPromedio;

    @Column(nullable = false)
    private Integer partidasJugadas;

    @Column(nullable = false)
    private Integer kills;

    @Column(nullable = false)
    private LocalDateTime fechaActualizacion;
}
