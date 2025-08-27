package com.example.ProjetApiBts.repository;

import com.example.ProjetApiBts.models.Secretaire;
import com.example.ProjetApiBts.models.Technicient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SecretaireRepository extends JpaRepository<Secretaire,Long> {
    Optional<Secretaire> findByEmail(String email);
}
