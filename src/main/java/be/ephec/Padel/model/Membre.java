package be.ephec.padel.model;

import jakarta.persistence.*;


/**
 * Ce que tu connais déjà : @Entity, l'@Id avec @GeneratedValue, des attributs simples (matricule, nom, prenom, email), le constructeur vide, les getters/setters.
Les deux nouveautés :
java@Enumerated(EnumType.STRING)
private TypeMembre type;

private TypeMembre type; → le type du membre est de type TypeMembre (notre enum). Il ne pourra valoir que GLOBAL, SITE ou LIBRE.
@Enumerated(EnumType.STRING) → dit à Hibernate : « stocke ce type en base sous forme de texte » (donc la colonne contiendra "GLOBAL", "SITE" ou "LIBRE"). Sans ça, il stockerait des chiffres (0, 1, 2), moins lisibles et risqués si on réordonne l'enum. Le STRING est la bonne pratique.

java@ManyToOne
private Site site;

Comme pour Terrain : un membre peut être rattaché à un site (utile surtout pour les membres de type SITE). C'est la même relation @ManyToOne que tu connais. Pour les membres Global et Libre, ce champ pourra rester vide (null), c'est voulu.
 * 
 */

@Entity
public class Membre
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String matricule;
    private String nom;
    private String prenom;
    private String email;

    @Enumerated(EnumType.STRING)
    private TypeMembre type;

    @ManyToOne
    private Site site;

    public Membre()
    {

    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getMatricule() {return matricule;}
    public void setMatricule(String matricule) {this.matricule = matricule;}

    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}

    public String getPrenom() {return prenom;}
    public void setPrenom(String prenom) {this.prenom = prenom;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public TypeMembre getType() {return type;}
    public void setType(TypeMembre type) {this.type = type;}

    public Site getSite() {return site;}
    public void setSite(Site site) {this.site = site;}
}