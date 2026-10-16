package com.example.jwtvalidation.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "JWT token to validate")
public record TokenRequest(
        @Schema(description = "Complete JWT string, including three dot-separated sections",
                example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuYXZlZW4ifQ.signature")
        String token
) {}
