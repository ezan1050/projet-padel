package be.ephec.padel.controller;

import be.ephec.padel.model.Match;
import be.ephec.padel.service.MatchService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/matchs")

public class MatchController
{
    private final MatchService matchService;

    public MatchController (MatchService matchService)
    {
        this.matchService = matchService;
    }

    // GET /api/matches -> liste de tous les matches
    @GetMapping
    public List<Match> getTousLesMatchs()
    {
        return matchService.getTousLesMatchs();
    }

    // POST /api/matches -> creer un nouveau match
    @PostMapping
    public Match creerMatch(@RequestBody Match match)
    {
        return matchService.creerMatch(match);
    }

    // GET /api/matchs/{id}/places-restantes
    @GetMapping("/{id}/places-restantes")
    public int getPlacesRestantes(@PathVariable Long id) {
        return matchService.getPlacesRestantes(id);
    }
    // GET /api/matchs/{id}/complet
    @GetMapping("/{id}/complet")
    public boolean estComplet(@PathVariable Long id) {
        return matchService.estComplet(id);
    }

    // GET /api/matchs/{id}/prix-par-joueur
    @GetMapping("/{id}/prix-par-joueur")
    public double getPrixParJoueur(@PathVariable Long id)
    {
        return matchService.getPrixParJoueur(id);
    }

    // GET /api/matchs/{id}/tous-payes
    @GetMapping("/{id}/tous-payes")
    public boolean tousOntPaye(@PathVariable Long id) {
        return matchService.tousOntPaye(id);
    }
    // DELETE /api/matchs/{id}
    // DELETE /api/matchs/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimerMatch(@PathVariable Long id) {
        try {
            matchService.supprimerMatch(id);
            return ResponseEntity.ok().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }
}