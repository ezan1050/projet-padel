package be.ephec.padel.repository;

import be.ephec.padel.model.Membre;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MembreRepository extends JpaRepository<Membre, Long>
{
    Optional<Membre>findByMatricule(String matricule);
}