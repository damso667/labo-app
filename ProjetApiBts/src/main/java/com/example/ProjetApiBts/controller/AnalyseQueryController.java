package com.example.ProjetApiBts.controller;


import com.example.ProjetApiBts.dto.ApiResponse;
import com.example.ProjetApiBts.dto.AnalyseDTO;
import com.example.ProjetApiBts.service.AnalyseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analyses")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('MEDECIN','TECHNITIEN')")
public class AnalyseQueryController {

    private final AnalyseService service;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AnalyseDTO>> byId(@PathVariable Long id) {
        var a = service.getById(id);
        if (a == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ApiResponse.ok(AnalyseDTO.of(a)));
    }
}
