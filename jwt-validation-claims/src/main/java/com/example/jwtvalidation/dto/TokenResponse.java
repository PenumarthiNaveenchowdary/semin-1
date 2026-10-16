package com.example.jwtvalidation.dto;

import java.util.List;
import java.util.Map;

public record TokenResponse(
        boolean valid,
        String message,
        String username,
        List<String> roles,
        Map<String, Object> claims
) {}
