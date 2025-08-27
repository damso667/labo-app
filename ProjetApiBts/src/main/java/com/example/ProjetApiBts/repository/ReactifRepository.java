package com.example.ProjetApiBts.repository;

 import com.example.ProjetApiBts.models.Reactif;
 import com.example.ProjetApiBts.models.TypeExament;
 import org.springframework.data.jpa.repository.JpaRepository;

 import java.util.Optional;

public interface ReactifRepository extends JpaRepository<Reactif, Long> {
 Optional<Reactif> findByCode(String code);

 }
