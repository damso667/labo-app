package com.example.ProjetApiBts.controller;

import com.example.ProjetApiBts.dto.ApiResponse;
import com.example.ProjetApiBts.dto.AnalyseDTO;
import com.example.ProjetApiBts.models.Analyse;
import com.example.ProjetApiBts.models.Prelevement;
import com.example.ProjetApiBts.models.Technicient;
import com.example.ProjetApiBts.service.AnalyseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/techniciens")
@RequiredArgsConstructor
@PreAuthorize("hasRole('TECHNITIEN')") // orthographe conforme à ta config
public class TechnicienController {

    private final AnalyseService service;

    // GET /api/techniciens/analyses-a-faire
    @GetMapping("/analyses-a-faire")
    public ResponseEntity<ApiResponse<List<AnalyseDTO>>> aFaire() {
        List<AnalyseDTO> list = service.analysesAFaire().stream().map(AnalyseDTO::of).toList();
        return ResponseEntity.ok(ApiResponse.ok(list));
    }

    @GetMapping("/mes-analyses")
    public ResponseEntity<ApiResponse<List<AnalyseDTO>>> mesAnalyses(
            @AuthenticationPrincipal(expression = "id") Long technicienId
    ) {
        List<AnalyseDTO> list = service.analysesDuTechnicien(technicienId)
                .stream()
                .map(AnalyseDTO::of)
                .toList();
        return ResponseEntity.ok(ApiResponse.ok(list));
    }

    // POST /api/techniciens/analyses/{id}/prendre
    @PostMapping("/analyses/{id}/prendre")
    public ResponseEntity<ApiResponse<AnalyseDTO>> prendre(
            @PathVariable Long id,
            @AuthenticationPrincipal(expression = "id") Long technicienId
    ) {
        Analyse a = service.technicienPrend(id, technicienId);
        if (a == null) return ResponseEntity.status(409).body(ApiResponse.fail("Analyse déjà prise par un autre technicien ou inexistante."));
        return ResponseEntity.ok(ApiResponse.ok(AnalyseDTO.of(a)));
    }

    // POST /api/techniciens/analyses/{id}/prelevements
    @PostMapping("/analyses/{id}/prelevements")
    public ResponseEntity<ApiResponse<Long>> prelever(
            @PathVariable Long id,
            @RequestParam String typePrelevement,
            @AuthenticationPrincipal(expression = "id") Long technicienId
    ) {
        Prelevement p = service.renseignerPrelevement(id, typePrelevement, technicienId);
        if (p == null) return ResponseEntity.status(403).body(ApiResponse.fail("Refus: vous devez avoir pris cette analyse."));
        return ResponseEntity.ok(ApiResponse.ok(p.getId()));
    }

    @GetMapping("/analyses-not-prelevement")
    @PreAuthorize("hasRole('TECHNITIEN')")
    public ResponseEntity<List<Analyse>>getAnalyses(@AuthenticationPrincipal Technicient technicient){
        List<Analyse> analyses = service.findAnalyseEnAttentePourTechnicien(technicient.getId());
        return ResponseEntity.ok(analyses);
    }



    // PATCH /api/techniciens/analyses/{id}/resultats
    @PatchMapping("/analyses/{id}/resultats")
    public ResponseEntity<ApiResponse<AnalyseDTO>> resultats(
            @PathVariable Long id,
            @RequestBody String resultats,
            @AuthenticationPrincipal(expression = "id") Long technicienId
    ) {
        Analyse a = service.saisirResultats(id, resultats, technicienId);
        if (a == null) return ResponseEntity.status(403).body(ApiResponse.fail("Refus: vous n'êtes pas le technicien affecté."));
        return ResponseEntity.ok(ApiResponse.ok(AnalyseDTO.of(a)));
    }
}
