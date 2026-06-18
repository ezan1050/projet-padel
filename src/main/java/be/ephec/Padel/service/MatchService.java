package be.ephec.padel.service;

import be.ephec.padel.model.Match;
import be.ephec.padel.repository.MatchRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MatchService
{
    private final MatchRepository matchRepository;

    // injection de dependance : Spring va fournir le repo auto 

    public MatchService(MatchRepository matchRepository)
    {
        this.matchRepository = matchRepository;
    }

    // recuperer tous les matchs
    public List<Match> getTousLesMatchs()
    {
        return matchRepository.findAll();
    }

    // enregistrer un nouveau match
    public Match creerMatch (Match match)
    {
        return matchRepository.save(match);
    }
}