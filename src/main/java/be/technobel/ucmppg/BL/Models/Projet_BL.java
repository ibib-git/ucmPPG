package be.technobel.ucmppg.BL.Models;

import java.util.List;

public class Projet_BL {

    private String nom_projet;
    private String description_projet;
    private Utilisateur_BL utilisateur_createur_projet;
    private List<Utilisateur_BL> utilisateurs_projet;

    public Projet_BL(String nom_projet, String description_projet, Utilisateur_BL utilisateur_createur_projet) {
        this.nom_projet = nom_projet;
        this.description_projet = description_projet;
        this.utilisateur_createur_projet = utilisateur_createur_projet;
    }

    public Projet_BL() {
    }

    public String getNom_projet() {
        return nom_projet;
    }

    public void setNom_projet(String nom_projet) {
        this.nom_projet = nom_projet;
    }

    public String getDescription_projet() {
        return description_projet;
    }

    public void setDescription_projet(String description_projet) {
        this.description_projet = description_projet;
    }

    public Utilisateur_BL getUtilisateur_createur_projet() {
        return utilisateur_createur_projet;
    }

    public void setUtilisateur_createur_projet(Utilisateur_BL utilisateur_createur_projet) {
        this.utilisateur_createur_projet = utilisateur_createur_projet;
    }

    public List<Utilisateur_BL> getUtilisateurs_projet() {
        return utilisateurs_projet;
    }

    public void setUtilisateurs_projet(List<Utilisateur_BL> utilisateurs_projet) {
        this.utilisateurs_projet = utilisateurs_projet;
    }
}
