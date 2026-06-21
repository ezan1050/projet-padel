package be.ephec.padel.controller;

import be.ephec.padel.model.Participation;
import be.ephec.padel.service.ParticipationService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/participations")
public class ParticipationController
{
    private final ParticipationService participationService;

    public ParticipationController(ParticipationService participationService)
    {
        this.participationService = participationService;
    }

    @GetMapping
    public List<Participation> getToutesLesParticipations()
    {
        return participationService.getToutesLesParticipations();
    }

    @PostMapping
    public Participation creerParticipation(@RequestBody Participation participation)
    {
        return participationService.creerParticipation(participation);
    }
}