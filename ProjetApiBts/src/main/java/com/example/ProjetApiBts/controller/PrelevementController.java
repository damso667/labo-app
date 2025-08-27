package com.example.ProjetApiBts.controller;

import com.example.ProjetApiBts.models.Prelevement;
import com.example.ProjetApiBts.models.Technicient;
import com.example.ProjetApiBts.service.PrelevementService;
import com.example.ProjetApiBts.dto.PrelevementDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/prelevements")
@RequiredArgsConstructor
public class PrelevementController {
    private final PrelevementService prelevementService;

    // Enregistrer un prélèvement pour une analyse donnée
    @PostMapping("/{analyseId}")
    @PreAuthorize("hasRole('TECHNICIEN')")
    public ResponseEntity<Prelevement> enregistrer(
            @PathVariable Long analyseId,
            @RequestBody PrelevementDTO request,
            @AuthenticationPrincipal Technicient technicien
    ) {
        Prelevement p = prelevementService.enregistrerPrelevement(analyseId, technicien.getId(), request.getTypePrelevement());
        return ResponseEntity.created(URI.create("/api/prelevements/" + p.getId())).body(p);
    }
}