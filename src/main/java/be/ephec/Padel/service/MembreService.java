package be.ephec.padel.service;

import be.ephec.padel.model.Membre;
import be.ephec.padel.repository.MembreRepository;
import org.springframework.stereotype.Service;
import java.util.List;

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
}
