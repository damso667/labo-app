package com.example.ProjetApiBts.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SessionController {
    @GetMapping("/api/session-statut")
    public Map<String, Boolean> sessionStatus(HttpSession session){
        boolean active = session.getAttribute("user")!= null;
        return  Map.of("active",active);
    }
}
