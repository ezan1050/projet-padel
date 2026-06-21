package be.ephec.padel.repository;

import be.ephec.padel.model.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipationRepository extends JpaRepository<Participation, Long>
{
    
}