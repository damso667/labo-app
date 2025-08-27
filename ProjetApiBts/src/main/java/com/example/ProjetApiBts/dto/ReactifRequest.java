package com.example.ProjetApiBts.dto;

import lombok.Data;

@Data
public class ReactifRequest {
    private String typeExamenNom;
    private String typeExamenDescription;
    private String nom;
    private String code;
    private String unite;
    private int stock;
    private double quantiteParAnalyse;
}
