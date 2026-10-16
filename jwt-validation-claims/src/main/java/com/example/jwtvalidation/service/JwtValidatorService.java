package com.example.jwtvalidation.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Service;

@Service
public class JwtValidatorService {
    private final SecretKey secretKey;

    public JwtValidatorService(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    public Claims validateAndExtractClaims(String token) {
        if (token == null || token.isBlank()) {
            throw new IllegalArgumentException("Token must not be empty");
        }

        // Parsing verifies the signature and automatically rejects expired tokens.
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public List<String> extractRoles(Claims claims) {
        Object roleValue = claims.get("roles");
        if (roleValue instanceof List<?> values) {
            List<String> roles = new ArrayList<>();
            for (Object value : values) {
                if (value != null) roles.add(value.toString());
            }
            return roles;
        }
        if (roleValue instanceof String role && !role.isBlank()) {
            return List.of(role);
        }
        Object singleRole = claims.get("role");
        return singleRole == null ? List.of() : List.of(singleRole.toString());
    }
}
