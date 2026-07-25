package be.ephec.padel.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class MatchDTO {

    private Long id;
    private LocalDate date;
    private LocalTime heureDebut;
    private double prix;
    private String type;
    private String statut;
    private String nomTerrain;
    private String nomOrganisateur;

    public MatchDTO(Long id, LocalDate date, LocalTime heureDebut, double prix,
                    String type, String statut, String nomTerrain, String nomOrganisateur) {
        this.id = id;
        this.date = date;
        this.heureDebut = heureDebut;
        this.prix = prix;
        this.type = type;
        this.statut = statut;
        this.nomTerrain = nomTerrain;
        this.nomOrganisateur = nomOrganisateur;
    }

    public Long getId() { return id; }
    public LocalDate getDate() { return date; }
    public LocalTime getHeureDebut() { return heureDebut; }
    public double getPrix() { return prix; }
    public String getType() { return type; }
    public String getStatut() { return statut; }
    public String getNomTerrain() { return nomTerrain; }
    public String getNomOrganisateur() { return nomOrganisateur; }
}