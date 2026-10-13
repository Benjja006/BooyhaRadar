
package com.example.Organization_Service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "organizaciones")
@Getter
@Setter
@NoArgsConstructor
public class Organizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la organización es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "El país es obligatorio")
    @Column(nullable = false, length = 60)
    private String pais;

    @Size(max = 1000, message = "La descripción no puede superar los 1000 caracteres")
    @Column(length = 1000)
    private String descripcion;

    @NotBlank(message = "El correo de contacto es obligatorio")
    @Email(message = "El correo de contacto no tiene un formato válido")
    @Column(nullable = false)
    private String contacto;

    @NotNull(message = "El ID del administrador responsable es obligatorio")
    @Column(nullable = false)
    private Long administradorId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoAprobacion estadoAprobacion = EstadoAprobacion.PENDIENTE;
}
