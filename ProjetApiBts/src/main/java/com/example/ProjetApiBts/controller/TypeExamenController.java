package com.example.ProjetApiBts.controller;


import com.example.ProjetApiBts.models.TypeExament;
import com.example.ProjetApiBts.repository.TypeExamenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import com.example.ProjetApiBts.dto.ApiResponse;


@RestController
@RequestMapping("/api/type-examens")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('MEDECIN','SECRETAIRE')")
public class TypeExamenController {

    private final TypeExamenRepository repo;

    @GetMapping
    public ResponseEntity<ApiResponse<List<TypeExament>>> all() {
        return ResponseEntity.ok(ApiResponse.ok(repo.findAll()));
    }
}



