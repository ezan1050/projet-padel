package be.ephec.padel.controller;

import be.ephec.padel.model.Paiement;
import be.ephec.padel.service.PaiementService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/paiements")


public class PaiementController
{
    private final PaiementService paiementService;

    public PaiementController (PaiementService paiementService)
    {
        this.paiementService = paiementService;
    }

    // GET /api/paiements -> liste de tous les paiements
    @GetMapping
    public List<Paiement> getTousLesPaiements()
    {
        return paiementService.getTousLesPaiements();
    }

    // POST /api/paiements -> creer un nouveau paiement
    @PostMapping
    public Paiement creerPaiement(@RequestBody Paiement paiement)
    {
        return paiementService.creerPaiement(paiement);
    }
}