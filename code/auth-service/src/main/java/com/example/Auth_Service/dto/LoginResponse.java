package com.example.Auth_Service.dto;

public class LoginResponse {

    private String token;
    private Long usuarioId;
    private String email;
    private String rol;

    public LoginResponse(String token, Long usuarioId, String email, String rol) {
        this.token = token;
        this.usuarioId = usuarioId;
        this.email = email;
        this.rol = rol;
    }

    public String getToken() {
        return token;
    }

    public String getEmail() {
        return email;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public String getRol() {
        return rol;
    }
}
