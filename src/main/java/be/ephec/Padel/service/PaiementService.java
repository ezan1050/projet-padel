package be.ephec.padel.service;

import be.ephec.padel.model.Paiement;
import be.ephec.padel.repository.PaiementRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service

public class PaiementService
{
    private final PaiementRepository paiementRepository;

    public PaiementService(PaiementRepository paiementRepository)
    {
        this.paiementRepository = paiementRepository;
    }

    // recuperer tous les paiements
    public List<Paiement> getTousLesPaiements()
    {
        return paiementRepository.findAll();
    }

    // enregistrer un nouveau paiement
    public Paiement creerPaiement (Paiement paiement)
    {
        return paiementRepository.save(paiement);
    }

    
}