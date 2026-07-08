package be.ephec.padel.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Paiement 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double montant;
    private LocalDateTime datePaiement;

    @OneToOne
    private Participation participation;

    public Paiement() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public double getMontant() { return montant; }
    public void setMontant(double montant) { this.montant = montant; }

    public LocalDateTime getDatePaiement() { return datePaiement; }
    public void setDatePaiement(LocalDateTime datePaiement) { this.datePaiement = datePaiement; }

    public Participation getParticipation() { return participation; }
    public void setParticipation(Participation participation) { this.participation = participation; }
}