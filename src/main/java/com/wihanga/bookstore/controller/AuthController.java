package com.wihanga.bookstore.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> login(Authentication authentication) {
        // If authentication is successful, Spring injects the Authentication object
        if (authentication != null && authentication.isAuthenticated()) {
            return ResponseEntity.ok().body("Login successful!");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
