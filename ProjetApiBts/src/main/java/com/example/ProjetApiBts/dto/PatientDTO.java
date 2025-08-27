package com.example.ProjetApiBts.dto;

// ========================== // Dtos simples pour sorties propres // ========================== package com.example.ProjetApiBts.dtos;

import com.example.ProjetApiBts.models.Patient;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PatientDTO {
    Long id;
    String nom;
    String prenom;
    String email;
    String telephone;
    String dateNaissance;
    String sexe;
    Long medecinId;
    String statutConsultation;

    public static PatientDTO of(Patient p){
        return PatientDTO.builder()
                .id(p.getId())
                .nom(p.getNom())
                .prenom(p.getPrenom())
                .email(p.getEmail())
                .telephone(p.getTelephone())
                .dateNaissance(p.getDate_Naissance())
                .sexe(p.getSexe() != null ? p.getSexe().name() : null)
                .statutConsultation(p.getStatutConsultation() != null ? p.getStatutConsultation().name(): null)
                .medecinId(p.getMedecin() != null ? p.getMedecin().getId() : null)
                .build();
    }

}
