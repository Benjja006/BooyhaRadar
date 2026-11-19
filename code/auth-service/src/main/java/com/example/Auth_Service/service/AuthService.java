package com.example.Auth_Service.service;

import com.example.Auth_Service.dto.LoginRequest;
import com.example.Auth_Service.dto.LoginResponse;
import com.example.Auth_Service.dto.RegistroRequest;
import com.example.Auth_Service.exception.RecursoNoEncontradoException;
import com.example.Auth_Service.model.Rol;
import com.example.Auth_Service.model.Usuario;
import com.example.Auth_Service.repository.UsuarioRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

public AuthService(
        UsuarioRepository usuarioRepository,
        PasswordEncoder passwordEncoder,
        JwtService jwtService) {

    this.usuarioRepository = usuarioRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
}

    public Usuario registrar(RegistroRequest request) {

        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException(
                    "El email ya está registrado"
            );
        }

        Usuario usuario = new Usuario();

        usuario.setEmail(request.getEmail());

        usuario.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        Rol rol = Rol.PLAYER;
        if (request.getRol() != null && !request.getRol().isBlank()) {
            try {
                rol = Rol.valueOf(request.getRol().toUpperCase());
            } catch (IllegalArgumentException excepcion) {
                throw new IllegalArgumentException("El rol solicitado no es válido");
            }
            if (rol == Rol.ADMIN) {
                throw new IllegalArgumentException(
                        "No se puede registrar una cuenta administradora");
            }
        }

        usuario.setRol(rol);

        usuario.setActivo(true);

        return usuarioRepository.save(usuario);
    }

    public LoginResponse login(LoginRequest request) {

    Usuario usuario = usuarioRepository
            .findByEmail(request.getEmail())
            .orElseThrow(() ->
                    new RecursoNoEncontradoException(
                            "Usuario no encontrado"
                    )
            );

    if (!passwordEncoder.matches(
            request.getPassword(),
            usuario.getPassword())) {

        throw new IllegalArgumentException(
                "Credenciales incorrectas"
        );
    }

    if (!usuario.isActivo()) {
        throw new IllegalArgumentException(
                "El usuario está inactivo"
        );
    }
    String token = jwtService.generarToken(
            usuario.getId(),
            usuario.getEmail(),
            usuario.getRol().name()
    );

    return new LoginResponse(
            token,
            usuario.getId(),
            usuario.getEmail(),
            usuario.getRol().name()
    );
    }
}
