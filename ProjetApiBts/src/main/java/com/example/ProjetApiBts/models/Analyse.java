
package com.example.ProjetApiBts.models;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Analyse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAnalyse;

    @Column(length = 1000)
    private String description;

    @Column(length = 2000, columnDefinition = "BOOLEAN")
    private String resultats; // texte libre simplifié

     private Boolean valide = false;
     @Temporal(TemporalType.TIMESTAMP)
     private Date dateValidation;

     @ManyToOne(optional = false)
     @JoinColumn(name = "patient_id")
     private Patient patient;

     @ManyToOne(optional = false)
     @JoinColumn(name = "medecin_prescripteur_id")
     private Medecin medecin; // prescripteur

     @ManyToOne
     @JoinColumn(name = "technicien_id")
     private Technicient technicien; // réalisateur

     @ManyToOne(optional = false)
     @JoinColumn(name = "type_examen_id")
     private TypeExament typeExamen;

}

