package sn.groupeisi.jeeappli.entiies;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "immeubles")
public class Immeuble {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "description")
    private String description;

    @ElementCollection
    @CollectionTable(name = "equipements", joinColumns = @JoinColumn(name = "immeuble_id"))
    @Column(name = "equipement")
    private List<String> equipements;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    public Immeuble() {
    }

    public Immeuble(String nom, String adresse, String description, List<String> equipements, Utilisateur utilisateur) {
        this.nom = nom;
        this.adresse = adresse;
        this.description = description;
        this.equipements = equipements;
        this.utilisateur = utilisateur;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getEquipements() {
        return equipements;
    }

    public void setEquipements(List<String> equipements) {
        this.equipements = equipements;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
