package be.ephec.padel.controller;

import be.ephec.padel.dto.MembreDTO;
import be.ephec.padel.model.Membre;
import be.ephec.padel.service.MembreService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/membres")

public class MembreController
{
    private final MembreService membreService;

    public MembreController (MembreService membreService)
    {
        this.membreService = membreService;
    }

    // GET /api/membres -> liste de tous les membres
    @GetMapping
    public List<MembreDTO> getTousLesMembres() {
        return membreService.getTousLesMembres().stream()
                .map(membre -> new MembreDTO(
                        membre.getId(),
                        membre.getMatricule(),
                        membre.getNom(),
                        membre.getPrenom(),
                        membre.getEmail(),
                        membre.getRole() != null ? membre.getRole().name() : null
                ))
                .toList();
    }
    // POST /api/membres -> creer un nouveau membre
    @PostMapping
    public Membre creerMembre(@RequestBody Membre membre)
    {
        return membreService.creerMembre(membre);
    }

    // POST /api/membres/{id}/penalite -> applique une penalite d'une semaine
    @PostMapping("/{id}/penalite")
    public Membre appliquerPenalite(@PathVariable Long id) {
        return membreService.appliquerPenalite(id);
    }

    // GET /api/membres/{id}/penalise -> le membre est-il penalise ?
    @GetMapping("/{id}/penalise")
    public boolean estPenalise(@PathVariable Long id) {
        return membreService.estPenalise(id);
    }
}