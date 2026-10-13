package com.example.Organization_Service.repository;

import com.example.Organization_Service.model.Organizacion;
import com.example.Organization_Service.model.EstadoAprobacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganizacionRepository
        extends JpaRepository<Organizacion, Long> {

    List<Organizacion> findByEstadoAprobacion(
            EstadoAprobacion estadoAprobacion);
}
