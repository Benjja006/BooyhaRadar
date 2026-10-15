package com.example.Scouting_Service.controller;

import com.example.Scouting_Service.service.ScoutingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/scouting")
public class ScoutingController {

    private final ScoutingService scoutingService;

    public ScoutingController(ScoutingService scoutingService) {
        this.scoutingService = scoutingService;
    }

    @GetMapping("/jugadores")
    public List<Map<String, Object>> buscarJugadores(
            @RequestParam(required = false) String pais,
            @RequestParam(required = false) Integer edadMinima,
            @RequestParam(required = false) Integer edadMaxima,
            @RequestParam(required = false) String rol,
            @RequestParam(required = false) String experiencia) {

        return scoutingService.buscarJugadores(
                pais, edadMinima, edadMaxima, rol, experiencia);
    }
}
