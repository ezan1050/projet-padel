package be.ephec.padel.controller;

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
    public List<Membre> getTousLesMembres()
    {
        return membreService.getTousLesMembres();
    }
    // POST /api/membres -> creer un nouveau membre
    @PostMapping
    public Membre creerMembre(@RequestBody Membre membre)
    {
        return membreService.creerMembre(membre);
    }
}