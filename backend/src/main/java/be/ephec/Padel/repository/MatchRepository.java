package be.ephec.padel.repository;

import be.ephec.padel.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long>
{
    
}