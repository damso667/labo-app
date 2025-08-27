package com.example.ProjetApiBts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")

public class ControllerMedecin {
    @GetMapping("/login")
    public String mot(){
        return "bienvenue medecin";
    }
}
