package com.example.ProjetApiBts.repository;

import com.example.ProjetApiBts.models.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedecinRepository extends JpaRepository<Medecin,Long> {
    Optional<Medecin> findByEmail(String email);

}
