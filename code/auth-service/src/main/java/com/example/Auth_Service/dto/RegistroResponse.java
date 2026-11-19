package com.example.Auth_Service.dto;

public class RegistroResponse {

    private Long usuarioId;
    private String email;
    private String rol;

    public RegistroResponse(Long usuarioId, String email, String rol) {
        this.usuarioId = usuarioId;
        this.email = email;
        this.rol = rol;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public String getEmail() {
        return email;
    }

    public String getRol() {
        return rol;
    }
}
