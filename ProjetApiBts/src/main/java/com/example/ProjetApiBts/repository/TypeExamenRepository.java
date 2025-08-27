package com.example.ProjetApiBts.repository;

import com.example.ProjetApiBts.models.TypeExament;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeExamenRepository extends JpaRepository<TypeExament, Long> {
  Optional<TypeExament>findByNom(String nom);


}
