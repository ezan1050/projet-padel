package be.ephec.padel.model;

import jakarta.persistence.*;

@Entity
public class Participation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Membre membre;

    @ManyToOne
    private Match match;

    @Enumerated(EnumType.STRING)
    private StatutPaiement statutPaiement;

    public Participation() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Membre getMembre() { return membre; }
    public void setMembre(Membre membre) { this.membre = membre; }

    public Match getMatch() { return match; }
    public void setMatch(Match match) { this.match = match; }

    public StatutPaiement getStatutPaiement() { return statutPaiement; }
    public void setStatutPaiement(StatutPaiement statutPaiement) { this.statutPaiement = statutPaiement; }
}