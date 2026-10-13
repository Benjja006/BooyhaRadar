package com.example.Organization_Service.service;

import com.example.Organization_Service.model.EstadoAprobacion;
import com.example.Organization_Service.model.Organizacion;
import com.example.Organization_Service.repository.OrganizacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizacionService {

    private final OrganizacionRepository organizacionRepository;

    public OrganizacionService(
            OrganizacionRepository organizacionRepository) {
        this.organizacionRepository = organizacionRepository;
    }

    public Organizacion crear(Organizacion organizacion) {
        organizacion.setId(null);
        organizacion.setEstadoAprobacion(EstadoAprobacion.PENDIENTE);

        return organizacionRepository.save(organizacion);
    }

    public List<Organizacion> listarTodas() {
        return organizacionRepository.findAll();
    }

    public List<Organizacion> listarAprobadas() {
        return organizacionRepository
                .findByEstadoAprobacion(EstadoAprobacion.APROBADA);
    }

    public Organizacion buscarPorId(Long id) {
        return organizacionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No se encontró la organización con ID: " + id));
    }

    public Organizacion actualizar(Long id, Organizacion datosNuevos) {
        Organizacion actual = buscarPorId(id);

        actual.setNombre(datosNuevos.getNombre());
        actual.setPais(datosNuevos.getPais());
        actual.setDescripcion(datosNuevos.getDescripcion());
        actual.setContacto(datosNuevos.getContacto());
        actual.setAdministradorId(datosNuevos.getAdministradorId());

        // Una modificación requiere una nueva revisión administrativa.
        actual.setEstadoAprobacion(EstadoAprobacion.PENDIENTE);

        return organizacionRepository.save(actual);
    }

    public void eliminar(Long id) {
        Organizacion actual = buscarPorId(id);
        organizacionRepository.delete(actual);
    }

    
    public Organizacion aprobar(Long id) {
        Organizacion organizacion = buscarPorId(id);
        organizacion.setEstadoAprobacion(EstadoAprobacion.APROBADA);

        return organizacionRepository.save(organizacion);
    }

    public Organizacion rechazar(Long id) {
        Organizacion organizacion = buscarPorId(id);
        organizacion.setEstadoAprobacion(EstadoAprobacion.RECHAZADA);

        return organizacionRepository.save(organizacion);
    }

}
