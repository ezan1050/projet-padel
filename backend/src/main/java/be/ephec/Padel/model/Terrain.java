package be.ephec.padel.model;

import jakarta.persistence.*;

@Entity
public class Terrain
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String surface;

    @ManyToOne
    private Site site;

    public Terrain()
    {
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}

    public String getSurface() {return surface;}
    public void setSurface(String surface) {this.surface = surface;}

    public Site getSite() {return site;}
    public void setSite(Site site) {this.site = site;}
}