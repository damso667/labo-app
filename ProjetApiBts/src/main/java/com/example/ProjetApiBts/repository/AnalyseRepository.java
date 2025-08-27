package com.example.ProjetApiBts.repository;

import com.example.ProjetApiBts.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// ---- AnalyseRepository

public interface AnalyseRepository extends JpaRepository<Analyse, Long> {
    List<Analyse> findByPatientId(Long patientId);
    List<Analyse>  findByMedecinId(Long medecinId);
    List<Analyse>  findByTechnicienId(Long technicienId);
    List<Analyse> findByTechnicienIsNull();
    List<Analyse>findByTechnicienIsNullAndValideFalse();

    @Query("""
       select a from Analyse a
       where a.medecin.id = :medId
         and a.patient.medecin.id = :medId
         and a.valide = false
         and a.resultats is not null
       """)
    List<Analyse> aValiderPourMedecin(@Param("medId") Long medId);

    // Toutes les analyses (validées ou non) avec résultats,
// prescrites par ce médecin pour des patients qu’il suit
    @Query("""
       select a from Analyse a
       where a.medecin.id = :medId
         and a.patient.medecin.id = :medId
         and a.resultats is not null
       order by a.dateAnalyse desc
       """)
    List<Analyse> resultatsPrescritsEtSuivis(@Param("medId") Long medId);


}
