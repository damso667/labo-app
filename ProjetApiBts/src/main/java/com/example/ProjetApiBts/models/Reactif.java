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
@Table(name = "reactif", indexes = {@Index(name = "idx_reactif_code", columnList = "code", unique = true)})
public class Reactif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nom;

    @Column(nullable = false, unique = true, length = 40)
    private String code; // ex: RBC-HEM-001

    @Override
    public boolean equals(Object o){
        if(this == o )return true;
        if(!(o instanceof Reactif))return false;
        Reactif that = (Reactif) o;
        return  id!= null && id.equals(that.id);

    }
    @Override
    public  int hashCode(){
        return getClass().hashCode();
    }

     @Column(length = 60)
     private String lot;

     @Column(length = 15)
     private String unite; // mL, mg, UI...

     private Integer stock; // quantité en stock

     @Temporal(TemporalType.DATE)
     private Date datePeremption;

     @Column(length = 1000)
     private String description;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_secretaire")
    private Secretaire secretaire;

}
