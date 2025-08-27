package com.example.ProjetApiBts.controller;

import com.example.ProjetApiBts.dto.ReactifDTO;
import com.example.ProjetApiBts.dto.ReactifRequest;
import com.example.ProjetApiBts.models.Reactif;
import com.example.ProjetApiBts.service.ReactifService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.example.ProjetApiBts.models.Reactif;
import com.example.ProjetApiBts.repository.ReactifRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reactifs")
@RequiredArgsConstructor
@PreAuthorize("hasRole('SECRETAIRE')")
public class ReactifController {
    private final ReactifRepository repo;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<Reactif> all() {
        return repo.findAll();
    }
    private final ReactifService service;

    @PostMapping("/creer")
    public ResponseEntity<ReactifDTO> ajouterReactif(
            @AuthenticationPrincipal(expression = "id") Long responsableId,
            @RequestBody ReactifRequest dto
    ) {
        Reactif reactif = service.ajouterReactifEtAssocier(
                responsableId,
                dto.getTypeExamenNom(),
                dto.getTypeExamenDescription(),
                dto.getNom(),
                dto.getCode(),
                dto.getUnite(),
                dto.getStock(),
                dto.getQuantiteParAnalyse()
        );
        return ResponseEntity.ok(ReactifDTO.of(reactif));
    }

    @PutMapping("/reactifs/{id}/augmenter")
    public ResponseEntity<ReactifDTO> augmenterStock(
            @PathVariable Long id,
            @RequestParam int quantite,
            @AuthenticationPrincipal(expression = "id") Long responsableId
    ) {
        Reactif reactif = service.augmenterStock(id, quantite, responsableId);
        return ResponseEntity.ok(ReactifDTO.of(reactif));
    }

}