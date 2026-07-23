package be.ephec.padel.dto;

public class MembreDTO {

    private Long id;
    private String matricule;
    private String nom;
    private String prenom;
    private String email;
    private String role;

    public MembreDTO(Long id, String matricule, String nom, String prenom, String email, String role) {
        this.id = id;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.role = role;
    }

    public Long getId() { return id; }
    public String getMatricule() { return matricule; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
}