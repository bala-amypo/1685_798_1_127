package com.example.demo.security;

public interface JwtService {
    String generateToken(String email, Long userId, String role);
    String extractEmail(String token);
    Long extractUserId(String token);
    String extractRole(String token);
    boolean validateToken(String token, String username);
}
