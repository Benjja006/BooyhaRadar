package com.example.Auth_Service.controller;

import com.example.Auth_Service.dto.LoginRequest;
import com.example.Auth_Service.dto.LoginResponse;
import com.example.Auth_Service.dto.RegistroRequest;
import com.example.Auth_Service.dto.RegistroResponse;
import com.example.Auth_Service.model.Usuario;
import com.example.Auth_Service.service.AuthService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegistroResponse> registrar(
            @Valid @RequestBody RegistroRequest request) {

        Usuario usuario = authService.registrar(request);
        RegistroResponse respuesta = new RegistroResponse(
                usuario.getId(), usuario.getEmail(), usuario.getRol().name());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(respuesta);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse respuesta = authService.login(request);

        return ResponseEntity.ok(respuesta);
}
}
