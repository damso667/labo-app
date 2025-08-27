package com.example.ProjetApiBts.models;


import com.example.ProjetApiBts.enums.Specilite;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Medecin implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private  long id;
    private  String nom;
    private String prenom;
    private String DateNaissance;
    private String email;
    private String telephone;

    @Enumerated(EnumType.STRING)
    private Specilite specialiter;

    private String password;

    @OneToMany(mappedBy = "medecin")
    private List<Patient> patient;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_MEDECIN"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
