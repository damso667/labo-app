package com.example.ProjetApiBts.models;


import com.example.ProjetApiBts.enums.Sexe;
import com.example.ProjetApiBts.enums.StatutPatient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private  long id ;
    private  String nom;
    private  String prenom;
    private  String Date_Naissance;

    @Enumerated(EnumType.STRING)
    private StatutPatient StatutConsultation;

    @Enumerated(EnumType.STRING)
    private Sexe sexe;

    @Column(unique = true)
    private  String email;

    private  String telephone;
    private  String Adresse;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_medecin")
    private Medecin medecin;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "technicien_id")
    private Technicient technicien;

}
