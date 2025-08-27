package com.example.ProjetApiBts.repository;

import com.example.ProjetApiBts.models.NecessiteReactif;
import com.example.ProjetApiBts.models.Reactif;
import com.example.ProjetApiBts.models.TypeExament;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.lang.model.element.TypeElement;
import java.util.Optional;

public interface NecessiteReactifRepositoy extends JpaRepository<NecessiteReactif,Long> {
    Optional<NecessiteReactif>findByTypeExamenAndReactif(TypeExament typeExament , Reactif reactif);
}
