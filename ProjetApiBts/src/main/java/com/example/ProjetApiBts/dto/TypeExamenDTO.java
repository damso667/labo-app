package com.example.ProjetApiBts.dto;

import com.example.ProjetApiBts.models.Reactif;
import com.example.ProjetApiBts.models.TypeExament;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TypeExamenDTO {
    private Long id;
    private String nom;
    private List<Long> reactifIds;

    public static TypeExamenDTO of(TypeExament t){
        return TypeExamenDTO.builder()
                .id(t.getId())
                .nom(t.getNom())
                .build();
    }
}
