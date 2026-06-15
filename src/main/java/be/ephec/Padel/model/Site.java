package be.ephec.padel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalTime;

@Entity
public class Site 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String adresse;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private int annee;

    // constructeur vide (obligatoire pour JPA)

    public Site()
    {

    }

    // Getters et setters
    public Long getId(){return id;}
    public void setId(Long id) {this.id=id;}
    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}
    public String getAdresse() {return adresse;}
    public void setAdresse(String adresse) {this.adresse = adresse;}
    public LocalTime getHeureDebut() {return heureDebut;}
    public void setHeureDebut(LocalTime heureDebut) {this.heureDebut = heureDebut;}
    public LocalTime getHeureFin() {return heureFin;}
    public void setHeureFin(LocalTime heureFin) {this.heureFin = heureFin;}
    public int getAnnee() {return annee;}
    public void setAnnee(int annee) {this.annee = annee;}
}