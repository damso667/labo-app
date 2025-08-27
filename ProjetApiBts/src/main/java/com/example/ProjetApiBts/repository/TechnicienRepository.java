package com.example.ProjetApiBts.repository;

import com.example.ProjetApiBts.models.Technicient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface TechnicienRepository extends JpaRepository<Technicient, Long> {
  Optional<Technicient> findByEmail(String email);
}
