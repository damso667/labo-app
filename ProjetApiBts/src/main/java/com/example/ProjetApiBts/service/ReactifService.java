package com.example.ProjetApiBts.service;

import com.example.ProjetApiBts.models.NecessiteReactif;
import com.example.ProjetApiBts.models.Reactif;
import com.example.ProjetApiBts.models.Secretaire;
import com.example.ProjetApiBts.models.TypeExament;
import com.example.ProjetApiBts.repository.NecessiteReactifRepositoy;
import com.example.ProjetApiBts.repository.ReactifRepository;
import com.example.ProjetApiBts.repository.SecretaireRepository;
import com.example.ProjetApiBts.repository.TypeExamenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReactifService {

    private final ReactifRepository reactifRepository;
    private final SecretaireRepository secretaireRepository;
    private  final TypeExamenRepository typeExamenRepository;
    private  final NecessiteReactifRepositoy necessiteReactifRepositoy;
    public Reactif ajouterReactifEtAssocier(Long responsableId, String typeExamenNom, String description,
                                            String reactifNom, String code, String unite,
                                            int stock, double quantiteParAnalyse) {
        // Charger le responsable
        Secretaire responsable = secretaireRepository.findById(responsableId).orElseThrow();

        // Vérifier ou créer le type examen
        TypeExament typeExamen = typeExamenRepository.findByNom(typeExamenNom)
                .orElseGet(() -> typeExamenRepository.save(
                        TypeExament.builder()
                                .nom(typeExamenNom)
                                .description(description)
                                .build()
                ));

        // Vérifier ou créer le réactif
        Reactif reactif = reactifRepository.findByCode(code)
                .orElseGet(() -> reactifRepository.save(
                        Reactif.builder()
                                .nom(reactifNom)
                                .code(code)
                                .unite(unite)
                                .stock(stock)
                                .secretaire(responsable)
                                .build()
                ));

        // Association NecessiteReactif
        if (necessiteReactifRepositoy.findByTypeExamenAndReactif(typeExamen, reactif).isEmpty()) {
            necessiteReactifRepositoy.save(
                    NecessiteReactif.builder()
                            .typeExamen(typeExamen)
                            .reactif(reactif)
                            .quantiteParAnalyse(quantiteParAnalyse)
                            .build()
            );
        }

        return reactif;
    }

    @Transactional
    public Reactif augmenterStock(Long reactifId, int quantite, Long responsableId) {
        Secretaire responsable = secretaireRepository.findById(responsableId).orElseThrow();

        Reactif reactif = reactifRepository.findById(reactifId).orElseThrow();
        reactif.setStock(reactif.getStock() + quantite);
        reactif.setSecretaire(responsable);

        return reactifRepository.save(reactif);
    }
    // ✅ Lister tous les réactifs
    public List<Reactif> listerReactifs() {
        return reactifRepository.findAll();
    }
}