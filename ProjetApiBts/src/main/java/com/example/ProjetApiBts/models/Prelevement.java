
package com.example.ProjetApiBts.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Prelevement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String typePrelevement; // sang, urine, salive, etc.

     @Temporal(TemporalType.TIMESTAMP)
     private Date datePrelevement;

     @ManyToOne(optional = false)
     @JoinColumn(name = "analyse_id")
     private Analyse analyse;

     @ManyToOne(optional = false)
     @JoinColumn(name = "technicien_id")
     private Technicient technicien; // auteur du prélèvement

}
