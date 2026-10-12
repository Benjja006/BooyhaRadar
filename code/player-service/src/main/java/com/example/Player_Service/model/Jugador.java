package com.example.Player_Service.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "jugador")
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false, unique = true)
    private String uid;

    @Column(nullable = false)
    private String pais;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @Column(nullable = false)
    private String rolPrincipal;

    private String rolSecundario;

    @Column(length = 1000)
    private String biografia;

    private String experienciaCompetitiva;

    @Column(length = 2000)
    private String logros;

    private String contacto;

    @Column(nullable = false)
    private String estadoCompetitivo;

    public Jugador() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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