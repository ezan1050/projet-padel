package be.ephec.Padel.service;

import be.ephec.padel.repository.MatchRepository;
import be.ephec.padel.repository.ParticipationRepository;
import be.ephec.padel.service.MatchService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MatchServiceTest {

    @Test
    void getPlacesRestantes_avec2Participations_retourne2() {
        MatchRepository matchRepository = mock(MatchRepository.class);
        ParticipationRepository participationRepository = mock(ParticipationRepository.class);

        when(participationRepository.countByMatchId(1L)).thenReturn(2L);

        MatchService service = new MatchService(matchRepository, participationRepository);

        int placesRestantes = service.getPlacesRestantes(1L);

        assertEquals(2, placesRestantes);
    }

    @Test
    void estComplet_avec4Participations_retourneTrue() {
        MatchRepository matchRepository = mock(MatchRepository.class);
        ParticipationRepository participationRepository = mock(ParticipationRepository.class);

        when(participationRepository.countByMatchId(1L)).thenReturn(4L);

        MatchService service = new MatchService(matchRepository, participationRepository);

        assertTrue(service.estComplet(1L));
    }

    @Test
    void estComplet_avec2Participations_retourneFalse() {
        MatchRepository matchRepository = mock(MatchRepository.class);
        ParticipationRepository participationRepository = mock(ParticipationRepository.class);

        when(participationRepository.countByMatchId(1L)).thenReturn(2L);

        MatchService service = new MatchService(matchRepository, participationRepository);

        assertFalse(service.estComplet(1L));
    }
}