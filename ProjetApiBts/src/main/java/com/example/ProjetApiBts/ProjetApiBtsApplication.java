package com.example.ProjetApiBts;

import com.example.ProjetApiBts.enums.Sexe;
import com.example.ProjetApiBts.enums.Specilite;
import com.example.ProjetApiBts.enums.StatutPatient;
import com.example.ProjetApiBts.models.*;
import com.example.ProjetApiBts.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@SpringBootApplication
public class ProjetApiBtsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetApiBtsApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(MedecinRepository medecinRepository, TechnicienRepository technicienRepository,
                               TypeExamenRepository typeExamenRepository,NecessiteReactifRepositoy necessiteReactifRepository,SecretaireRepository secretaireRepository,
                               PasswordEncoder passwordEncoder, PatientRepository patientRepository , ReactifRepository reactifRepository) {
        return args -> {
            // Médecins
            Medecin m1 = new Medecin();
            m1.setNom("NOUBAM NJASSINE");
            m1.setPrenom("Adrien Christien");
            m1.setEmail("Njassine@gmail.com");
            m1.setDateNaissance("01-11-1980");
            m1.setTelephone("674882603");
            m1.setPassword(passwordEncoder.encode("1234Adrien"));
            m1.setSpecialiter(Specilite.CARDIOLOGIE);

            Medecin m2 = new Medecin();
            m2.setNom("DOGMO FEUDJEU");
            m2.setPrenom("Ivan Roma");
            m2.setEmail("ThomaVArp@gmail.com");
            m2.setDateNaissance("01-11-1990");
            m2.setTelephone("677852603");
            m2.setPassword(passwordEncoder.encode("1234#Adrien"));
            m2.setSpecialiter(Specilite.OPHTAMOLOGIE);

            Medecin m3 = new Medecin();
            m3.setNom("NDOUMBE NDOPGANG");
            m3.setPrenom("Adrien Romaric");
            m3.setEmail("ndoumbe123@gmail.com");
            m3.setDateNaissance("01-11-1984");
            m3.setTelephone("654882603");
            m3.setPassword(passwordEncoder.encode("ndoumbe123@gmail.com"));
            m3.setSpecialiter(Specilite.OPTICIEN);

            Medecin m4 = new Medecin();
            m4.setNom("TALLA KOUMETIO");
            m4.setPrenom("Romeo");
            m4.setEmail("romeoTalla@gmail.com");
            m4.setDateNaissance("01-11-1984");
            m4.setTelephone("674431484");
            m4.setPassword(passwordEncoder.encode("7894romeo"));
            m4.setSpecialiter(Specilite.PEDIATRIE);

            Optional<Medecin> medecinOptional = medecinRepository.findByEmail(m1.getEmail());
            Optional<Medecin> medecinOptional2 = medecinRepository.findByEmail(m2.getEmail());
            Optional<Medecin> medecinOptional3 = medecinRepository.findByEmail(m3.getEmail());
            Optional<Medecin> medecinOptional4 = medecinRepository.findByEmail(m4.getEmail());


            if (medecinOptional.isEmpty()) {
                medecinRepository.save(m1);
            }
            if (medecinOptional2.isEmpty()) {
                medecinRepository.save(m2);
            }
            if (medecinOptional3.isEmpty()) {
                medecinRepository.save(m3);
            }
            if (medecinOptional4.isEmpty()) {
                medecinRepository.save(m4);
            }


            // Techniciens
            Technicient t1 = new Technicient();
            t1.setNom("Techno");
            t1.setPrenom("Alpha");
            t1.setEmail("alpha@hopital.com");
            t1.setDateNaissance("02-02-1990");
            t1.setTelephone("650000000");
            t1.setPassword(passwordEncoder.encode("alpha123"));

            Technicient t2 = new Technicient();
            t2.setNom("Techno");
            t2.setPrenom("Bravo");
            t2.setEmail("bravo@hopital.com");
            t2.setDateNaissance("03-03-1988");
            t2.setTelephone("660000000");
            t2.setPassword(passwordEncoder.encode("bravo123"));

            Technicient t3 = new Technicient();
            t3.setNom("NDOPGANG CHTUIGWA");
            t3.setPrenom("Brayan");
            t3.setEmail("ndopgang@gmail.com");
            t3.setDateNaissance("03-03-1978");
            t3.setTelephone("659426532");
            t3.setPassword(passwordEncoder.encode("brayan12345"));

            Optional<Technicient> technicientOptional1 = technicienRepository.findByEmail(t1.getEmail());
            Optional<Technicient> technicientOptional2 = technicienRepository.findByEmail(t2.getEmail());
            Optional<Technicient> technicientOptional3 = technicienRepository.findByEmail(t3.getEmail());


            if (technicientOptional1.isEmpty()) {
                technicienRepository.save(t1);
            }
            if (technicientOptional2.isEmpty()) {
                technicienRepository.save(t2);
            }
            if (technicientOptional3.isEmpty()) {
                technicienRepository.save(t3);
            }

            //secretaire

            Secretaire s1 = new Secretaire();
            s1.setNom("Varo");
            s1.setPrenom("Roman");
            s1.setEmail("Varo123@gmail.com");
            s1.setTelephone("655628596");
            s1.setDomicile("Ngoa Ekele");
            s1.setPassword(passwordEncoder.encode("Roman1234"));

            Secretaire s2 = new Secretaire();
            s2.setNom("ndonfack");
            s2.setPrenom("Elen");
            s2.setEmail("ndonfack1254@gmail.com");
            s2.setTelephone("674431484");
            s2.setDomicile("Melen");
            s2.setPassword(passwordEncoder.encode("viva12365à"));

            Secretaire s3 = new Secretaire();
            s3.setNom("Noubam");
            s3.setPrenom("leonard");
            s3.setEmail("noubam2@gmail.com");
            s3.setTelephone("678521036");
            s3.setDomicile("Nkoabang");
            s3.setPassword(passwordEncoder.encode("leonard123"));

            Optional<Secretaire> secretaireOptional1 = secretaireRepository.findByEmail(s1.getEmail());
            Optional<Secretaire> secretaireOptional2 = secretaireRepository.findByEmail(s2.getEmail());
            Optional<Secretaire> secretaireOptional3 = secretaireRepository.findByEmail(s3.getEmail());


            if (secretaireOptional1.isEmpty()) {
                secretaireRepository.save(s1);
            }
            if (secretaireOptional2.isEmpty()) {
                secretaireRepository.save(s2);
            }
            if (secretaireOptional3.isEmpty()) {
                secretaireRepository.save(s3);
            }

            //patient

            Patient p1 = new Patient();
            p1.setNom("Tamo");
            p1.setPrenom("Geraard");
            p1.setSexe(Sexe.MASCULIN);
            p1.setEmail("Gerad@gmail.com");
            p1.setDate_Naissance("02-02-1980");
            p1.setTelephone("650000000");
            p1.setAdresse("MELEN");
            p1.setStatutConsultation(StatutPatient.EN_ATTENTE);


            Patient p2 = new Patient();
            p2.setNom("waffo");
            p2.setPrenom("Robert");
            p2.setSexe(Sexe.MASCULIN);
            p2.setEmail("waffoRobert@gmail.com");
            p2.setDate_Naissance("03-03-1985");
            p2.setTelephone("643852065");
            p2.setAdresse("NKOABANG");
            p2.setStatutConsultation(StatutPatient.EN_ATTENTE);


            Patient p3 = new Patient();
            p3.setNom("AWOTA");
            p3.setPrenom("Loren");
            p3.setSexe(Sexe.FEMININ);
            p3.setEmail("awota@gmail.com");
            p3.setDate_Naissance("03-03-1995");
            p3.setTelephone("653857075");
            p3.setAdresse("TRADAT");
            p3.setStatutConsultation(StatutPatient.EN_ATTENTE);

            Patient p4 = new Patient();
            p4.setNom("SONFACK TAFFO");
            p4.setPrenom("Lorena");
            p4.setSexe(Sexe.FEMININ);
            p4.setEmail("taffo@gmail.com");
            p4.setDate_Naissance("03-03-1975");
            p4.setTelephone("683857075");
            p4.setStatutConsultation(StatutPatient.EN_ATTENTE);

            Patient p5 = new Patient();
            p5.setNom("ATANGANA");
            p5.setPrenom("ROMARIC");
            p5.setSexe(Sexe.MASCULIN);
            p5.setEmail("atangana@gmail.com");
            p5.setDate_Naissance("03-03-1985");
            p5.setTelephone("693857075");
            p5.setAdresse("POSTE CENTRALLE");
            p5.setStatutConsultation(StatutPatient.EN_ATTENTE);

            Patient p6 = new Patient();
            p6.setNom("MFOUNDI");
            p6.setPrenom("ROBERT");
            p6.setSexe(Sexe.MASCULIN);
            p6.setEmail("mfoundi@gmail.com");
            p6.setDate_Naissance("03-03-1975");
            p6.setTelephone("695867075");
            p6.setAdresse("MELEN");
            p6.setStatutConsultation(StatutPatient.EN_ATTENTE);

            Patient p7 = new Patient();
            p7.setNom("HENGUE");
            p7.setPrenom("ViVie");
            p7.setSexe(Sexe.FEMININ);
            p7.setEmail("Hengue@gmail.com");
            p7.setDate_Naissance("04-09-2000");
            p7.setTelephone("695867075");
            p7.setAdresse("MEEC");
            p7.setStatutConsultation(StatutPatient.EN_ATTENTE);


            Optional<Patient> patientOptional1 = patientRepository.findByEmail(p1.getEmail());
            Optional<Patient> patientOptional2 = patientRepository.findByEmail(p2.getEmail());
            Optional<Patient> patientOptional3 = patientRepository.findByEmail(p3.getEmail());
            Optional<Patient> patientOptional4 = patientRepository.findByEmail(p4.getEmail());
            Optional<Patient> patientOptional5 = patientRepository.findByEmail(p5.getEmail());
            Optional<Patient> patientOptional6 = patientRepository.findByEmail(p6.getEmail());
            Optional<Patient> patientOptional7 = patientRepository.findByEmail(p6.getEmail());




            if (patientOptional1.isEmpty()) {
                patientRepository.save(p1);
            }
            if (patientOptional2.isEmpty()) {
                patientRepository.save(p2);
            }
            if (patientOptional3.isEmpty()) {
                patientRepository.save(p3);
            }
            if (patientOptional4.isEmpty()) {
                patientRepository.save(p4);
            }
            if (patientOptional5.isEmpty()) {
                patientRepository.save(p5);
            }
            if (patientOptional6.isEmpty()) {
                patientRepository.save(p6);
            }
            if (patientOptional7.isEmpty()) {
                patientRepository.save(p7);
            }

             // déjà seedé
            // 5 types d'examens connus au démarrage
// Hémogramme
            TypeExament hemo = typeExamenRepository.findByNom("Hémogramme")
                    .orElseGet(() -> typeExamenRepository.save(
                            TypeExament.builder().nom("Hémogramme").description("Numération formule sanguine").build()
                    ));

            Reactif r1 = reactifRepository.findByCode("RBC-HEM-001")
                    .orElseGet(() -> reactifRepository.save(
                            Reactif.builder().nom("Réactif hématologie").code("RBC-HEM-001").unite("mL").stock(500).build()
                    ));

            if (necessiteReactifRepository.findByTypeExamenAndReactif(hemo, r1).isEmpty()) {
                necessiteReactifRepository.save(
                        NecessiteReactif.builder().typeExamen(hemo).reactif(r1).quantiteParAnalyse(2.0).build()
                );
            }

// Glycémie à jeun
            TypeExament gly = typeExamenRepository.findByNom("Glycémie à jeun")
                    .orElseGet(() -> typeExamenRepository.save(
                            TypeExament.builder().nom("Glycémie à jeun").description("Dosage glucose sanguin").build()
                    ));

            Reactif r2 = reactifRepository.findByCode("GLU-GOD-002")
                    .orElseGet(() -> reactifRepository.save(
                            Reactif.builder().nom("Réactif glucose oxydase").code("GLU-GOD-002").unite("mL").stock(300).build()
                    ));

            if (necessiteReactifRepository.findByTypeExamenAndReactif(gly, r2).isEmpty()) {
                necessiteReactifRepository.save(
                        NecessiteReactif.builder().typeExamen(gly).reactif(r2).quantiteParAnalyse(1.5).build()
                );
            }

// Cholestérol total
            TypeExament chol = typeExamenRepository.findByNom("Cholestérol total")
                    .orElseGet(() -> typeExamenRepository.save(
                            TypeExament.builder().nom("Cholestérol total").description("Lipidémie").build()
                    ));

            Reactif r3 = reactifRepository.findByCode("CHOL-ENZ-003")
                    .orElseGet(() -> reactifRepository.save(
                            Reactif.builder().nom("Réactif cholestérol").code("CHOL-ENZ-003").unite("mL").stock(300).build()
                    ));

            if (necessiteReactifRepository.findByTypeExamenAndReactif(chol, r3).isEmpty()) {
                necessiteReactifRepository.save(
                        NecessiteReactif.builder().typeExamen(chol).reactif(r3).quantiteParAnalyse(1.5).build()
                );
            }

// Créatininémie
            TypeExament creat = typeExamenRepository.findByNom("Créatininémie")
                    .orElseGet(() -> typeExamenRepository.save(
                            TypeExament.builder().nom("Créatininémie").description("Fonction rénale").build()
                    ));

            Reactif r4 = reactifRepository.findByCode("CREA-JAF-004")
                    .orElseGet(() -> reactifRepository.save(
                            Reactif.builder().nom("Réactif créatinine").code("CREA-JAF-004").unite("mL").stock(200).build()
                    ));

            if (necessiteReactifRepository.findByTypeExamenAndReactif(creat, r4).isEmpty()) {
                necessiteReactifRepository.save(
                        NecessiteReactif.builder().typeExamen(creat).reactif(r4).quantiteParAnalyse(1.2).build()
                );
            }

// Groupage sanguin
            TypeExament groupage = typeExamenRepository.findByNom("Groupage sanguin")
                    .orElseGet(() -> typeExamenRepository.save(
                            TypeExament.builder().nom("Groupage sanguin").description("ABO/Rh").build()
                    ));

            Reactif r5 = reactifRepository.findByCode("ABO-RH-005")
                    .orElseGet(() -> reactifRepository.save(
                            Reactif.builder().nom("Anti-sérums ABO/Rh").code("ABO-RH-005").unite("mL").stock(100).build()
                    ));

            if (necessiteReactifRepository.findByTypeExamenAndReactif(groupage, r5).isEmpty()) {
                necessiteReactifRepository.save(
                        NecessiteReactif.builder().typeExamen(groupage).reactif(r5).quantiteParAnalyse(0.5).build()
                );
            }

        };
    }
}