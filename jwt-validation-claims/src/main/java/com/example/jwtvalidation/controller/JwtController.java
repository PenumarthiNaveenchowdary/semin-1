package com.example.jwtvalidation.controller;

import com.example.jwtvalidation.dto.TokenRequest;
import com.example.jwtvalidation.dto.TokenResponse;
import com.example.jwtvalidation.service.JwtValidatorService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jwt")
@Tag(name = "JWT Validation", description = "Validate a signed JWT and extract its claims")
public class JwtController {
    private final JwtValidatorService validatorService;

    public JwtController(JwtValidatorService validatorService) {
        this.validatorService = validatorService;
    }

    @PostMapping("/validate")
    @Operation(summary = "Validate a JWT token and return username, roles, and claims")
    public ResponseEntity<TokenResponse> validate(@RequestBody TokenRequest request) {
        try {
            Claims claims = validatorService.validateAndExtractClaims(
                    request == null ? null : request.token());
            String username = claims.get("username", String.class);
            if (username == null) username = claims.getSubject();
            List<String> roles = validatorService.extractRoles(claims);

            return ResponseEntity.ok(new TokenResponse(
                    true, "Token Valid", username, roles, claims
            ));
        } catch (JwtException | IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new TokenResponse(false, "Invalid Token", null, List.of(), null)
            );
        }
    }
}
