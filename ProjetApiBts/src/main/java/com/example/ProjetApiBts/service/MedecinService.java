package com.example.ProjetApiBts.service;

// ========================== // MedecinService.java // ==========================

import com.example.ProjetApiBts.enums.EtatPaiement;
import com.example.ProjetApiBts.enums.StatutPatient;
import com.example.ProjetApiBts.models.Medecin;
import com.example.ProjetApiBts.models.Patient;
import com.example.ProjetApiBts.models.TicketCaisse;
import com.example.ProjetApiBts.repository.MedecinRepository;
import com.example.ProjetApiBts.repository.PatientRepository;
import com.example.ProjetApiBts.repository.TicketRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor

public class MedecinService {

    private final PatientRepository patientRepository;
    private final MedecinRepository medecinRepository;// ⬅️ injection ajoutée
    private final TicketRepository ticketRepository;

    public List<Patient> listerPatientsDisponiblesTrieAlpha(){
        return patientRepository.findByMedecinIsNullOrderByNomAscPrenomAsc();
    }

    @Transactional
    public Patient recupererPatient(Long patientId, Medecin medecinCourant){
        // Réattacher le médecin pour éviter "detached entity passed to persist"
        Medecin medecinManaged = medecinRepository.findById(medecinCourant.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Médecin introuvable"));
        Patient p = patientRepository.lockById(patientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient introuvable"));

        if (p.getMedecin() == null) {
            p.setMedecin(medecinManaged);
            p.setStatutConsultation(StatutPatient.EN_COURS);
            TicketCaisse ticketCaisse = new TicketCaisse();
            ticketCaisse.setDatePaiement(new Date());
            ticketCaisse.setEtatPaiement(EtatPaiement.EFFECTUER);
            ticketCaisse.setPatient(p);
            ticketRepository.save(ticketCaisse);
            return p;
        }

        if (p.getMedecin().getId() == medecinManaged.getId()) {
            return p;
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT, "Patient déjà récupéré par un autre médecin");
    }

    public List<Patient> listerPatient(Medecin medecin){
        return patientRepository.findByMedecinIdOrderByNomAscPrenomAsc(medecin.getId());
    }
}
