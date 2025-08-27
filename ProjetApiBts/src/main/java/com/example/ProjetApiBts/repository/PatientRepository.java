package com.example.ProjetApiBts.repository;

// ========================== // PatientRepository.java (ajouts) // ========================== package com.example.ProjetApiBts.repository;

import com.example.ProjetApiBts.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

// Patients non assignés, tri alpha (Nom puis Prénom)
  List<Patient> findByMedecinIsNullOrderByNomAscPrenomAsc(); // Lecture avec verrou pour empêcher les races lors de l'assignation
      @Lock(LockModeType.PESSIMISTIC_WRITE)
      @Query("select p from Patient p where p.id = :id")
      Optional<Patient> lockById(@Param("id") Long id);
      Optional<Patient> findByEmail(String email);
List<Patient> findByMedecinIdOrderByNomAscPrenomAsc(Long medecinId);
}
