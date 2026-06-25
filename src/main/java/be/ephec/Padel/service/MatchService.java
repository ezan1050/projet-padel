package be.ephec.padel.service;

import be.ephec.padel.model.Match;
import be.ephec.padel.repository.MatchRepository;
import be.ephec.padel.repository.ParticipationRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MatchService
{
    private final MatchRepository matchRepository;
    private final ParticipationRepository participationRepository;

    // injection de dependance : Spring va fournir le repo auto 

    public MatchService(MatchRepository matchRepository,
     ParticipationRepository participationRepository)
    {
        this.matchRepository = matchRepository;
        this.participationRepository = participationRepository;
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

    //Regle Metier : combien  de place reste-t-il
    public int getPlacesRestantes(Long matchId)
    {
        long joueursInscrits = participationRepository.countByMatchId(matchId);
        return 4 - (int) joueursInscrits;
    }
}