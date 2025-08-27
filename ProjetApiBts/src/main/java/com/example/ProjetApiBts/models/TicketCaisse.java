package com.example.ProjetApiBts.models;

import com.example.ProjetApiBts.enums.EtatPaiement;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ticket_caisse") public class TicketCaisse {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date datePaiement;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EtatPaiement etatPaiement = EtatPaiement.NON_EFFECTUER;

    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "patient_id")
    private Patient patient;

}
