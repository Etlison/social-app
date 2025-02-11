package com.laserviewtechnologies.socialapp.controller;

import com.laserviewtechnologies.socialapp.model.User;
import com.laserviewtechnologies.socialapp.service.AuthService;
import com.laserviewtechnologies.socialapp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody User user) {
        try {
            authService.registerUser(user);
            return ResponseEntity.ok("User registered successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Registration failed: " + e.getMessage());
        }
    }

    // User login
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User user) {
        try {
            String token = authService.authenticate(user);
            if (token != null) {
                return ResponseEntity.ok("Bearer " + token);
            } else {
                return ResponseEntity.status(401).body("Invalid credentials!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }

    // Token validation endpoint (optional)
    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestParam String token) {
        if (jwtUtil.isTokenValid(token)) {
            return ResponseEntity.ok("Token is valid");
        } else {
            return ResponseEntity.status(400).body("Invalid token");
        }
    }
}

