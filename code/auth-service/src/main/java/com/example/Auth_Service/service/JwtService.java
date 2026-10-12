package com.example.Auth_Service.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private final JwtEncoder jwtEncoder;

    public JwtService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public String generarToken(String email, String rol) {

        Instant ahora = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(email)
                .claim("rol", rol)
                .issuedAt(ahora)
                .expiresAt(ahora.plus(2, ChronoUnit.HOURS))
                .build();

        return jwtEncoder
                .encode(JwtEncoderParameters.from(claims))
                .getTokenValue();
    }
}