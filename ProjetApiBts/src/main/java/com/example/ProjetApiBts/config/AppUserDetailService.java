// ========================== // AppUserDetailsService.java // ========================== package com.example.ProjetApiBts.security;
package  com.example.ProjetApiBts.config;
import com.example.ProjetApiBts.models.Medecin;
import com.example.ProjetApiBts.models.Secretaire;
import com.example.ProjetApiBts.models.Technicient;
import com.example.ProjetApiBts.repository.MedecinRepository;
import com.example.ProjetApiBts.repository.SecretaireRepository;
import com.example.ProjetApiBts.repository.TechnicienRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor public class AppUserDetailService implements UserDetailsService {

    private final MedecinRepository medecinRepository;
    private final TechnicienRepository technicienRepository;
    private  final SecretaireRepository secretaireRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return
                medecinRepository.findByEmail(email).<UserDetails>map(m -> (Medecin)m)
                .or(() -> technicienRepository.findByEmail(email).map(t -> (Technicient) t))
                .or(() -> secretaireRepository.findByEmail(email).map( s -> (Secretaire) s) )
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable: " + email));
    }

}
