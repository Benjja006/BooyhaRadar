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

        usuario.setRol(Rol.PLAYER);

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
            usuario.getEmail(),
            usuario.getRol().name()
    );

    return new LoginResponse(
            token,
            usuario.getEmail(),
            usuario.getRol().name()
    );
    }
}