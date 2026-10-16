package com.example.jwtvalidation.controller;

import io.jsonwebtoken.Jwts;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import javax.crypto.SecretKey;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Creates sample tokens for local testing. Remove or protect this endpoint in production. */
@RestController
@RequestMapping("/api/jwt")
public class DemoTokenController {
    private final SecretKey secretKey;

    public DemoTokenController(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    @GetMapping("/demo-token")
    public DemoTokenResponse createDemoToken() {
        Instant now = Instant.now();
        String token = Jwts.builder()
                .subject("naveen")
                .claim("username", "naveen")
                .claim("roles", List.of("USER", "STUDENT"))
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusSeconds(3600)))
                .signWith(secretKey)
                .compact();
        return new DemoTokenResponse(token, "Valid for 1 hour. Copy token into Swagger.");
    }

    public record DemoTokenResponse(String token, String note) {}
}
