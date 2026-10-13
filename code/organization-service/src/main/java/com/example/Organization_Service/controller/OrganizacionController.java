
package com.example.Organization_Service.controller;

import com.example.Organization_Service.model.EstadoAprobacion;
import com.example.Organization_Service.model.Organizacion;
import com.example.Organization_Service.service.OrganizacionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizaciones")
public class OrganizacionController {

    private final OrganizacionService organizacionService;

    public OrganizacionController(OrganizacionService organizacionService) {
        this.organizacionService = organizacionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Organizacion crear(@Valid @RequestBody Organizacion organizacion) {
        return organizacionService.crear(organizacion);
    }

    @GetMapping
    public List<Organizacion> listarAprobadas() {
        return organizacionService.listarAprobadas();
    }

    @GetMapping("/{id}")
    public Organizacion buscarPorId(@PathVariable Long id) {
        return organizacionService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Organizacion actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Organizacion organizacion) {
        return organizacionService.actualizar(id, organizacion);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        organizacionService.eliminar(id);
    }
    
    @PatchMapping("/{id}/aprobacion")
    public Organizacion cambiarAprobacion(
            @PathVariable Long id,
            @RequestParam EstadoAprobacion estado) {
        if (estado == EstadoAprobacion.APROBADA) {
            return organizacionService.aprobar(id);
        }
        if (estado == EstadoAprobacion.RECHAZADA) {
            return organizacionService.rechazar(id);
        }
        throw new IllegalArgumentException(
                "El estado debe ser APROBADA o RECHAZADA");
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    public org.springframework.http.ResponseEntity<String> manejarNoEncontrado(
            IllegalArgumentException excepcion) {
        return org.springframework.http.ResponseEntity
                .status(org.springframework.http.HttpStatus.NOT_FOUND)
                .body(excepcion.getMessage());
    }


}
