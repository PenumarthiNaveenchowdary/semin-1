package com.example.jwtvalidation.config;

import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    /*
     * Demo key only. Keep a strong secret in an environment variable in real
     * applications. This Base64 value decodes to a 256-bit HMAC key.
     */
    private static final String DEMO_SECRET_BASE64 =
            "MDEyMzQ1Njc4OWFiY2RlZjAxMjM0NTY3ODlhYmNkZWY=";

    @Bean
    public SecretKey jwtSecretKey() {
        return Keys.hmacShaKeyFor(java.util.Base64.getDecoder().decode(DEMO_SECRET_BASE64));
    }
}
