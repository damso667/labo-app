 package com.example.ProjetApiBts.service;
 import com.example.ProjetApiBts.enums.EtatPaiement;
 import com.example.ProjetApiBts.models.*;
 import com.example.ProjetApiBts.repository.*;
 import com.example.ProjetApiBts.shared.Ids;
 import lombok.RequiredArgsConstructor;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;

 import java.util.Date;
 import java.util.List;

 @Service
 @RequiredArgsConstructor
 @Transactional
 public class AnalyseService {

     private final AnalyseRepository analyseRepository;
     private final PatientRepository patientRepository;
     private final TicketRepository ticketRepository;
     private final TypeExamenRepository typeExamenRepository;
     private final PrelevementRepository prelevementRepository;
     private final MedecinRepository medecinRepository;
     private final TechnicienRepository technicientRepository;

     // ----- MÉDECIN : prescrire une analyse (seulement si le ticket du patient est payé ET s'il suit le patient)
     public Analyse creerAnalyse(Long patientId, Long typeExamenId, String description, Long medecinConnecteId) {
         Patient patient = patientRepository.findById(patientId).orElse(null);
         if (patient == null) return null;

         Medecin medecinConnecte = medecinRepository.findById(medecinConnecteId).orElse(null);
         if (medecinConnecte == null) return null;

         // Le médecin doit suivre ce patient
         Medecin medecinQuiSuit = patient.getMedecin();
         if (!(medecinQuiSuit != null && Ids.same(medecinQuiSuit.getId(), medecinConnecte.getId()))) {
             return null;
         }

         // Ticket payé ?
         TicketCaisse ticket = (TicketCaisse) ticketRepository.findFirstByPatientOrderByDatePaiementDesc(patient).orElse(null);
         if (!(ticket != null && ticket.getEtatPaiement() == EtatPaiement.EFFECTUER)) { // conforme à ton enum
             return null;
         }

         TypeExament typeExamen = typeExamenRepository.findById(typeExamenId).orElse(null);
         if (typeExamen == null) return null;

         Analyse analyse = new Analyse();
         analyse.setDateAnalyse(new Date());
         analyse.setDescription(description);
         analyse.setPatient(patient);
         analyse.setMedecin(medecinConnecte); // prescripteur
         analyse.setTypeExamen(typeExamen);
         analyse.setValide(false);
         return analyseRepository.save(analyse);


     }
     // Liste des analyses avec résultats, non validées, que le médecin doit revoir
     public List<Analyse> aValiderPourMedecin(Long medecinId) {
         return analyseRepository.aValiderPourMedecin(medecinId);
     }

     // Toutes les analyses avec résultats (validées ou non) prescrites par ce médecin pour ses patients
     public List<Analyse> resultatsPrescritsEtSuivis(Long medecinId) {
         return analyseRepository.resultatsPrescritsEtSuivis(medecinId);
     }

     // ----- TECHNICIEN : voir les analyses à réaliser (non prises et non validées)
     public List<Analyse> analysesAFaire() {
         return analyseRepository.findByTechnicienIsNullAndValideFalse();
     }

     // ----- TECHNICIEN : prendre en charge une analyse (verrouillage)
     public Analyse technicienPrend(Long analyseId, Long technicienId) {
         Analyse a = analyseRepository.findById(analyseId).orElse(null);
         if (a == null) return null;

         Technicient tech = technicientRepository.findById(technicienId).orElse(null);
         if (tech == null) return null;

         if (a.getTechnicien() == null) {
             a.setTechnicien(tech);
             return analyseRepository.save(a);
         }
         // déjà pris par lui ? ok, sinon refus
         return (Ids.same(a.getTechnicien().getId(), tech.getId())) ? a : null;
     }



     // ----- TECHNICIEN : renseigner le prélèvement (doit être le technicien qui a pris l’analyse)
     public Prelevement renseignerPrelevement(Long analyseId, String typePrelevement, Long technicienId) {
         Analyse a = analyseRepository.findById(analyseId).orElse(null);
         if (a == null) return null;

         Technicient tech = technicientRepository.findById(technicienId).orElse(null);
         if (tech == null) return null;

         if (!(a.getTechnicien() != null && Ids.same(a.getTechnicien().getId(), tech.getId()))) {
             return null;
         }

         Prelevement p = new Prelevement();
         p.setAnalyse(a);
         p.setTechnicien(tech);
         p.setTypePrelevement(typePrelevement);
         p.setDatePrelevement(new Date());
         return prelevementRepository.save(p);
     }

     // ----- TECHNICIEN : saisir les résultats (doit être le technicien de l’analyse)
     public Analyse saisirResultats(Long analyseId, String resultats, Long technicienId) {
         Analyse a = analyseRepository.findById(analyseId).orElse(null);
         if (a == null) return null;

         if (!(a.getTechnicien() != null && Ids.same(a.getTechnicien().getId(), technicienId))) {
             return null;
         }

         a.setResultats(resultats);
         a.setValide(false); // en attente de validation
         return analyseRepository.save(a);
     }

     // ----- MÉDECIN : valider (doit SUIT le patient + être le PRESCRIPTEUR)
     public Analyse validerAnalyse(Long analyseId, Long medecinId) {
         Analyse a = analyseRepository.findById(analyseId).orElse(null);
         if (a == null) return null;

         Medecin m = medecinRepository.findById(medecinId).orElse(null);
         if (m == null) return null;

         Patient patient = a.getPatient();
         boolean suitLePatient = (patient != null && patient.getMedecin() != null && Ids.same(patient.getMedecin().getId(), m.getId()));
         boolean estPrescripteur = (a.getMedecin() != null && Ids.same(a.getMedecin().getId(), m.getId()));

         if (!(suitLePatient && estPrescripteur)) return null;

         a.setValide(true);
         a.setDateValidation(new Date());
         return analyseRepository.save(a);

     }

     public  List<Analyse>findAnalyseEnAttentePourTechnicien(Long technitientId){
         return prelevementRepository.findAnalysesEnAttentePourTechnicien(technitientId);
     }

     public List<Analyse> analysesParPatient(Long patientId) {
         return analyseRepository.findByPatientId(patientId);
     }

     public List<Analyse> analysesDuMedecin(Long medecinId) {
         return analyseRepository.findByMedecinId(medecinId);
     }

     public List<Analyse> analysesDuTechnicien(Long technicienId) {
         return analyseRepository.findByTechnicienId(technicienId);
     }

     public Analyse getById(Long id) {
         return analyseRepository.findById(id).orElse(null);
     }
 }
