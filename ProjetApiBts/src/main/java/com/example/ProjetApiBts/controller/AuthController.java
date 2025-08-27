package com.example.ProjetApiBts.controller;


// ========================== // AuthController.java // ========================== package com.example.ProjetApiBts.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor; import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:8080")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req, HttpServletRequest httpReq){
        Authentication auth = authenticationManager
                .authenticate( new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
                );
        SecurityContextHolder.getContext()
                .setAuthentication(auth);
        HttpSession session = httpReq.getSession(true); // crée la session si absente
         session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
         String role = auth.getAuthorities()
                 .stream().map(GrantedAuthority::getAuthority)
                 .findFirst().orElse("");
         return ResponseEntity.ok(Map.of( "message", "Connexion réussie", "role", role ));
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok(Map.of("message", "Déconnecté"));
    }

    @Data
    @AllArgsConstructor
    static class LoginRequest{
        private String email;
        private String password;
    }

    }
