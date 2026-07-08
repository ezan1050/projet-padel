package be.ephec.padel.config;

import be.ephec.padel.model.*;
import be.ephec.padel.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class DataSeeder implements CommandLineRunner {

    private final SiteRepository siteRepository;
    private final TerrainRepository terrainRepository;
    private final MembreRepository membreRepository;
    private final MatchRepository matchRepository;
    private final ParticipationRepository participationRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(SiteRepository siteRepository,
                      TerrainRepository terrainRepository,
                      MembreRepository membreRepository,
                      MatchRepository matchRepository,
                      ParticipationRepository participationRepository,
                      PasswordEncoder passwordEncoder) 
    {
        this.siteRepository = siteRepository;
        this.terrainRepository = terrainRepository;
        this.membreRepository = membreRepository;
        this.matchRepository = matchRepository;
        this.participationRepository = participationRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        Site site = new Site();
        site.setNom("Padel Brussels Center");
        site.setAdresse("Rue de la Loi 100, Bruxelles");
        site.setHeureDebut(LocalTime.of(9, 0));
        site.setHeureFin(LocalTime.of(22, 0));
        site.setAnnee(2026);
        siteRepository.save(site);

        Terrain terrain1 = new Terrain();
        terrain1.setNom("Terrain 1");
        terrain1.setSurface("gazon synthetique");
        terrain1.setSite(site);
        terrainRepository.save(terrain1);

        Terrain terrain2 = new Terrain();
        terrain2.setNom("Terrain 2");
        terrain2.setSurface("beton poreux");
        terrain2.setSite(site);
        terrainRepository.save(terrain2);

        Membre marie = new Membre();
        marie.setMatricule("G0001");
        marie.setNom("Dupont");
        marie.setPrenom("Marie");
        marie.setEmail("marie@example.com");
        marie.setType(TypeMembre.GLOBAL);
        marie.setMotDePasse(passwordEncoder.encode("marie123"));
        marie.setRole(Role.ADMIN_GLOBAL);
        membreRepository.save(marie);

        Membre luc = new Membre();
        luc.setMatricule("S0001");
        luc.setNom("Martin");
        luc.setPrenom("Luc");
        luc.setEmail("luc@example.com");
        luc.setType(TypeMembre.SITE);
        luc.setSite(site);
        luc.setMotDePasse(passwordEncoder.encode("luc123"));
        luc.setRole(Role.USER);
        membreRepository.save(luc);

        Match match = new Match();
        match.setDate(LocalDate.of(2026, 7, 1));
        match.setHeureDebut(LocalTime.of(18, 0));
        match.setPrix(60.0);
        match.setType(TypeMatch.PRIVE);
        match.setStatut(StatutMatch.EN_ATTENTE);
        match.setTerrain(terrain1);
        match.setOrganisateur(marie);
        matchRepository.save(match);

        Participation participation = new Participation();
        participation.setMembre(marie);
        participation.setMatch(match);
        participation.setStatutPaiement(StatutPaiement.EN_ATTENTE);
        participationRepository.save(participation);

        System.out.println(">>> Donnees de test inserees avec succes !");
    }
}