package com.example.demo.util;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    public String generateToken(String username) {
        return "dummy-jwt-token-for-" + username;
    }
}
