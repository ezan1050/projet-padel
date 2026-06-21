package be.ephec.padel.service;

import be.ephec.padel.model.Participation;
import be.ephec.padel.repository.ParticipationRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ParticipationService
{
    private final ParticipationRepository participationRepository;

    public ParticipationService(ParticipationRepository participationRepository)
    {
        this.participationRepository = participationRepository;
    }

    public List<Participation> getToutesLesParticipations()
    {
        return participationRepository.findAll();
    }

    public Participation creerParticipation(Participation participation)
    {
        return participationRepository.save(participation);
    }
}