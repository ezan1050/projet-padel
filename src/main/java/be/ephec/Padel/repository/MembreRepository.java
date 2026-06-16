package be.ephec.padel.repository;

import be.ephec.padel.model.Membre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembreRepository extends JpaRepository<Membre, Long>
{
    
}