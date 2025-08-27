
 package com.example.ProjetApiBts.repository;
 import com.example.ProjetApiBts.models.Analyse;
 import com.example.ProjetApiBts.models.Prelevement;
 import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.data.jpa.repository.Query;
 import org.springframework.data.repository.query.Param;

 import java.util.List;

 public interface PrelevementRepository extends JpaRepository<Prelevement, Long> {
  @Query("SELECT a FROM Analyse a " +
          "WHERE a.technicien.id = :technicienId " +
          "AND a.id NOT IN (SELECT p.analyse.id FROM Prelevement p)")
  List<Analyse> findAnalysesEnAttentePourTechnicien(@Param("technicientId") Long technicienId);

 }