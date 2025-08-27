package com.example.ProjetApiBts.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "necessite_reactif")
public class NecessiteReactif {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "type_examen_id")
    @JsonBackReference
    private TypeExament typeExamen;

    @ManyToOne(optional = false)
    @JoinColumn(name = "reactif_id") private Reactif reactif; // quantité de réactif nécessaire pour UNE analyse de ce type

    @Column(nullable = false)
    private Double quantiteParAnalyse;

}
