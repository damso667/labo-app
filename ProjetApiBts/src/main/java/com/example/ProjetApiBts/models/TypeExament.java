package com.example.ProjetApiBts.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.List;


import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "type_examen", uniqueConstraints = @UniqueConstraint(columnNames = "nom"))
public class TypeExament {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nom; // "Hémogramme", "Glycémie", etc.
     @Column(length = 1000)
     private String description; // Association via table de jointure riche NecessiteReactif (avec quantite)
     @OneToMany(mappedBy = "typeExamen", cascade = CascadeType.ALL, orphanRemoval = true)
     @JsonManagedReference
     private List<NecessiteReactif> reactifsNecessaires = new ArrayList<>();

    @Override
    public boolean equals(Object o){
        if(this == o ) return true;
        if(!(o instanceof TypeExament)) return false;
        TypeExament that = (TypeExament) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }
}