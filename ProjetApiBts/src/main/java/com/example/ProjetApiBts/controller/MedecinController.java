package com.example.ProjetApiBts.controller;

// ========================== // MedecinController.java // ========================== package com.example.ProjetApiBts.controllers;

import com.example.ProjetApiBts.dto.AnalyseDTO;
import com.example.ProjetApiBts.dto.ApiResponse;
import com.example.ProjetApiBts.dto.PatientDTO;
import com.example.ProjetApiBts.models.Analyse;
import com.example.ProjetApiBts.models.Medecin;
import com.example.ProjetApiBts.models.Patient;
import com.example.ProjetApiBts.service.AnalyseService;
import com.example.ProjetApiBts.service.MedecinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/medecins")
@CrossOrigin(origins = "http://localhost:4200")

@RequiredArgsConstructor
public class MedecinController {

    private final MedecinService medecinService;// GET /api/medecins/patients?disponibles=true (par défaut true)
    private final AnalyseService service;
     @GetMapping("/patients")
     public ResponseEntity<List<PatientDTO>> listerPatients(@RequestParam(value = "disponibles", defaultValue = "true") boolean disponibles){
         List<Patient> patients = disponibles ? medecinService
                 .listerPatientsDisponiblesTrieAlpha() : medecinService.listerPatientsDisponiblesTrieAlpha(); // ici on n'expose que les disponibles selon ton besoin
          return ResponseEntity.ok(patients.stream().map(PatientDTO::of).toList());
     }
     // PUT /api/medecins/patients/{id}/recuperer

     @PutMapping("/patients/{id}/recuperer")
     public ResponseEntity<PatientDTO> recuperer(@PathVariable("id") Long id, Authentication authentication){
         Object principal = authentication.getPrincipal();
         if(!(principal instanceof Medecin medecin)){ // Sécurité supplémentaire: ce contrôleur est déjà filtré par ROLE_MEDECIN, mais au cas où
              return ResponseEntity.status(403).build();
         }
         Patient p = medecinService.recupererPatient(id, medecin);
         return ResponseEntity.ok(PatientDTO.of(p));
     }



     @GetMapping("/mes-patient")
     public ResponseEntity<List<PatientDTO>>listerpatient(Authentication authentication){
         Object principal =  authentication.getPrincipal();
         if(!(principal instanceof  Medecin medecin)){
             return ResponseEntity.status(403).build();
         }
         List<Patient> patients = medecinService.listerPatient(medecin);
         return ResponseEntity.ok(patients.stream().map(PatientDTO::of).toList());
     }

    // POST /api/medecins/analyses : prescrire
    @PostMapping("/analyses")
    public ResponseEntity<ApiResponse<AnalyseDTO>> prescrire(
            @RequestParam Long patientId,
            @RequestParam Long typeExamenId,
            @RequestBody(required = false) String description,
            @AuthenticationPrincipal(expression = "id") Long medecinId
    ) {
        Analyse a = service.creerAnalyse(patientId, typeExamenId, description, medecinId);
        if (a == null) return ResponseEntity.badRequest().body(ApiResponse.fail("Refus: ticket non payé, patient inconnu, type examen inconnu, ou vous ne suivez pas ce patient."));
        return ResponseEntity.created(URI.create("/api/analyses/" + a.getId()))
                .body(ApiResponse.created(AnalyseDTO.of(a)));
    }

    // PATCH /api/medecins/analyses/{id}/valider : valider
    @PatchMapping("/analyses/{id}/valider")
    public ResponseEntity<ApiResponse<AnalyseDTO>> valider(
            @PathVariable Long id,
            @AuthenticationPrincipal(expression = "id") Long medecinId
    ) {
        Analyse a = service.validerAnalyse(id, medecinId);
        if (a == null) return ResponseEntity.status(403).body(ApiResponse.fail("Refus: vous devez suivre le patient ET être le prescripteur."));
        return ResponseEntity.ok(ApiResponse.ok(AnalyseDTO.of(a)));
    }

    // GET /api/medecins/analyses/a-valider
    @GetMapping("/analyses/a-valider")
    public ResponseEntity<ApiResponse<List<AnalyseDTO>>> aValider(
            @AuthenticationPrincipal(expression = "id") Long medecinId
    ) {
        List<AnalyseDTO> list = service.aValiderPourMedecin(medecinId).stream()
                .map(AnalyseDTO::of)
                .toList();
        return ResponseEntity.ok(ApiResponse.ok(list));
    }

    // GET /api/medecins/analyses/resultats
    @GetMapping("/analyses/resultats")
    public ResponseEntity<ApiResponse<List<AnalyseDTO>>> resultats(
            @AuthenticationPrincipal(expression = "id") Long medecinId
    ) {
        List<AnalyseDTO> list = service.resultatsPrescritsEtSuivis(medecinId).stream()
                .map(AnalyseDTO::of)
                .toList();
        return ResponseEntity.ok(ApiResponse.ok(list));
    }
}

