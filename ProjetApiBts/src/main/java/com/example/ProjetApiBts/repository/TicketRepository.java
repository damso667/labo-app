package com.example.ProjetApiBts.repository;

import com.example.ProjetApiBts.models.Patient;
import com.example.ProjetApiBts.models.TicketCaisse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<TicketCaisse, Long> {
 TicketCaisse findByPatient(Patient patient);
 Optional findFirstByPatientOrderByDatePaiementDesc(Patient patient);
}
