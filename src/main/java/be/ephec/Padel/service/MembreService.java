package be.ephec.padel.service;

import be.ephec.padel.model.Membre;
import be.ephec.padel.repository.MembreRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDate;

@Service
public class MembreService
{
    private final MembreRepository membreRepository;

    // injection de dependance : Spring va fournir le repo auto 

    public MembreService(MembreRepository membreRepository)
    {
        this.membreRepository = membreRepository;
    }

    //recuperer tous les membres
    public List<Membre> getTousLesMembres()
    {
        return membreRepository.findAll();
    }

    //enregistrer un nouveau membre
    public Membre creerMembre(Membre membre)
    {
        return membreRepository.save(membre);
    }

     // REGLE METIER : appliquer une penalite d'une semaine a un membre
    public Membre appliquerPenalite(Long membreId) {
        Membre membre = membreRepository.findById(membreId)
                .orElseThrow(() -> new IllegalArgumentException("Membre non trouvé"));
        membre.setDateFinPenalite(LocalDate.now().plusWeeks(1));
        return membreRepository.save(membre);
    }

    // REGLE METIER : ce membre est-il actuellement penalise ?
    public boolean estPenalise(Long membreId) {
        Membre membre = membreRepository.findById(membreId)
                .orElseThrow(() -> new IllegalArgumentException("Membre non trouvé"));

        LocalDate finPenalite = membre.getDateFinPenalite();

        // Pas de panneau du tout -> jamais penalise
        if (finPenalite == null) {
            return false;
        }

        // Penalise si aujourd'hui est AVANT (ou egal a) la date de fin
        return !LocalDate.now().isAfter(finPenalite);
    }
}
