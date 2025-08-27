package com.example.ProjetApiBts.dto;

import com.example.ProjetApiBts.models.Reactif;
import com.example.ProjetApiBts.models.Secretaire;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReactifDTO {
    private Long id;
    private String nom;
    private String code;
    private String unite;
    private int stock;
    private Long dernierModifieParId;

    public static ReactifDTO of(Reactif r) {
        return ReactifDTO.builder()
                .id(r.getId())
                .nom(r.getNom())
                .code(r.getCode())
                .unite(r.getUnite())
                .stock(r.getStock())
                .dernierModifieParId(
                        r.getSecretaire() != null ? r.getSecretaire().getId() : null
                )
                .build();
    }
}
