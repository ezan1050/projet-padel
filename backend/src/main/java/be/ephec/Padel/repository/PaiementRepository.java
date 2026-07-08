package be.ephec.padel.repository;

import be.ephec.padel.model.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaiementRepository extends JpaRepository<Paiement, Long>
{
    
}