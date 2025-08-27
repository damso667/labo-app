package com.example.ProjetApiBts.error;


// ========================== // RestExceptionHandler.java (optionnel mais propre) // ========================== package com.example.ProjetApiBts.errors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@ControllerAdvice public class RestExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handle(ResponseStatusException ex){
        return ResponseEntity.status(ex.getStatusCode())
                .body(Map.of( "status", ex.getStatusCode().value(), "error", ex.getReason() ));
    }

}

// ========================== // Notes d'intégration rapide // ========================== //
// 1) Tes entités Medecin / Technicient implémentent déjà UserDetails et portent ROLE_MEDECIN / ROLE_TECHNITIEN.
// 2) Les mots de passe sont encodés via BCrypt (ok dans ton initData).
// 3) Pour tester: // - POST /api/auth/login {"email":"ndoumbe123@gmail.com","password":"12345Adrien"}
// - Puis appeler (avec le cookie de session renvoyé):
// GET /api/medecins/patients -> 200 et liste triée des patients sans médecin
// PUT /api/medecins/patients/1/recuperer -> 200 et patient assigné
// Re-essayer depuis un autre compte médecin -> 409 CONFLICT
// GET /api/medecins/patients -> le patient récupéré n’apparaît plus
// 4) Concurrence: l'assignation utilise un verrou pessimiste pour éviter qu’un 2e médecin prenne le même patient au même instant.
// 5) Retours HTTP: 401 si non connecté (géré par Spring Security), 403 si rôle incorrect, 404 si patient inexistant, 409 si déjà pris.
