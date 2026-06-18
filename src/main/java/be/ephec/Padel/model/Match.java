package be.ephec.padel.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "matchs")
public class Match
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private LocalTime heureDebut;
    private double prix;

    @Enumerated(EnumType.STRING)
    private TypeMatch type;

    @Enumerated(EnumType.STRING)
    private StatutMatch statut;

    @ManyToOne
    private Terrain terrain;

    @ManyToOne
    private Membre organisateur;

    public Match()
    {
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public LocalDate getDate() {return date;}
    public void setDate(LocalDate date) {this.date = date;}

    public LocalTime getHeureDebut() {return heureDebut;}
    public void setHeureDebut(LocalTime heureDebut) {this.heureDebut = heureDebut;}

    public double getPrix() {return prix;}
    public void setPrix(double prix) {this.prix = prix;}

    public TypeMatch getType() {return type;}
    public void setType(TypeMatch type) {this.type = type;}

    public StatutMatch getStatut() {return statut;}
    public void setStatut(StatutMatch statut) {this.statut = statut;}

    public Terrain getTerrain() {return terrain;}
    public void setTerrain(Terrain terrain) {this.terrain = terrain;}

    public Membre getOrganisateur() {return organisateur;}
    public void setOrganisateur(Membre organisateur) {this.organisateur = organisateur;}
}