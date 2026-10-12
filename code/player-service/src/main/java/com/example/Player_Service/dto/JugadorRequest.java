package com.example.Player_Service.dto;

import com.example.Player_Service.validation.StrictStringDeserializer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import tools.jackson.databind.annotation.JsonDeserialize;

public class JugadorRequest {

    @JsonDeserialize(using = StrictStringDeserializer.class)
    @NotBlank(message = "El nickname es obligatorio")
    private String nickname;

    @JsonDeserialize(using = StrictStringDeserializer.class)
    @NotBlank(message = "El UID es obligatorio")
    private String uid;

    @JsonDeserialize(using = StrictStringDeserializer.class)
    @NotBlank(message = "El país es obligatorio")
    private String pais;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser válida")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;

    @JsonDeserialize(using = StrictStringDeserializer.class)
    @NotBlank(message = "El rol principal es obligatorio")
    private String rolPrincipal;

    @JsonDeserialize(using = StrictStringDeserializer.class)
    private String rolSecundario;

    @JsonDeserialize(using = StrictStringDeserializer.class)
    @Size(max = 1000, message = "La biografía no puede superar los 1000 caracteres")
    private String biografia;

    @JsonDeserialize(using = StrictStringDeserializer.class)
    private String experienciaCompetitiva;

    @JsonDeserialize(using = StrictStringDeserializer.class)
    @Size(max = 2000, message = "Los logros no pueden superar los 2000 caracteres")
    private String logros;

    @JsonDeserialize(using = StrictStringDeserializer.class)
    private String contacto;

    @JsonDeserialize(using = StrictStringDeserializer.class)
    @NotBlank(message = "El estado competitivo es obligatorio")
    private String estadoCompetitivo;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getRolPrincipal() {
        return rolPrincipal;
    }

    public void setRolPrincipal(String rolPrincipal) {
        this.rolPrincipal = rolPrincipal;
    }

    public String getRolSecundario() {
        return rolSecundario;
    }

    public void setRolSecundario(String rolSecundario) {
        this.rolSecundario = rolSecundario;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getExperienciaCompetitiva() {
        return experienciaCompetitiva;
    }

    public void setExperienciaCompetitiva(String experienciaCompetitiva) {
        this.experienciaCompetitiva = experienciaCompetitiva;
    }

    public String getLogros() {
        return logros;
    }

    public void setLogros(String logros) {
        this.logros = logros;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getEstadoCompetitivo() {
        return estadoCompetitivo;
    }

    public void setEstadoCompetitivo(String estadoCompetitivo) {
        this.estadoCompetitivo = estadoCompetitivo;
    }
}