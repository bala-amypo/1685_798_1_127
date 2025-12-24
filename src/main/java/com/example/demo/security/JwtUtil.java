package com.example.demo.security;

import com.example.demo.entity.User;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    public String generateToken(User user) {
        // simple token for project/demo
        return "TOKEN_" + user.getEmail();
    }

    public String extractEmail(String token) {
        return token.replace("TOKEN_", "");
    }
}
