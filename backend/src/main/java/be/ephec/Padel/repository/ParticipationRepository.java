package be.ephec.padel.repository;

import be.ephec.padel.model.Participation;
import be.ephec.padel.model.StatutPaiement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipationRepository extends JpaRepository<Participation, Long>
{
    long countByMatchId(Long matchId);
    long countByMatchIdAndStatutPaiement(Long matchId, StatutPaiement statutPaiement);

}