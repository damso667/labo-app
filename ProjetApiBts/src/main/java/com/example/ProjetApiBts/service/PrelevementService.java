package com.example.ProjetApiBts.service;

import com.example.ProjetApiBts.dto.PrelevementDTO;
import com.example.ProjetApiBts.models.*;
import com.example.ProjetApiBts.repository.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


import com.example.ProjetApiBts.models.*;
import com.example.ProjetApiBts.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class PrelevementService {
    private final PrelevementRepository prelevementRepository;
    private final AnalyseRepository analyseRepository;
    private final TechnicienRepository technicienRepository;

    // Technicien enregistre un prélèvement pour une analyse donnée
    public Prelevement enregistrerPrelevement(Long analyseId, Long technicienId, String typePrelevement) {
        Analyse analyse = analyseRepository.findById(analyseId)
                .orElseThrow(() -> new NoSuchElementException("Analyse inexistante"));

        Technicient technicien = technicienRepository.findById(technicienId)
                .orElseThrow(() -> new NoSuchElementException("Technicien inexistant"));

        Prelevement prelevement = new Prelevement();
        prelevement.setAnalyse(analyse);
        prelevement.setTechnicien(technicien);
        prelevement.setTypePrelevement(typePrelevement);
        prelevement.setDatePrelevement(new Date());

        return prelevementRepository.save(prelevement);
    }
}