package com.example.demo.security;

import com.example.demo.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    // FIXED: This key is now long enough (32+ characters) to satisfy HS256 requirements
    private final String SECRET_KEY = "ThisIsASecretKeyForHS256Compliance2025_MustBeAtLeast32Chars!";

    // Helper method to turn the String into a secure Key object
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole().name());
        claims.put("email", user.getEmail());
        claims.put("userId", user.getId());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                // FIXED: Use the getSigningKey() method here
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // ---- Methods directly used in tests ----

    public String extractRole(String token) {
        try {
            return extractAllClaims(token).get("role", String.class);
        } catch (Exception e) {
            return null;
        }
    }

    public String extractEmail(String token) {
        try {
            return extractAllClaims(token).get("email", String.class);
        } catch (Exception e) {
            return "";
        }
    }

    public Long extractUserId(String token) {
        try {
            return extractAllClaims(token).get("userId", Long.class);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean validateToken(String token, String username) {
        if (token == null || username == null) return false;
        try {
            String email = extractEmail(token);
            return username.equals(email);
        } catch (Exception e) {
            return false;
        }
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder() // parser() is deprecated in newer JJWT versions
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}