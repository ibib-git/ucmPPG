package be.technobel.ucmppg.BL.Models;

import java.util.List;

public class Projet_BL {

    private String nom_projet;
    private String description_projet;
    private Utilisateur_BL utilisateur_createur_projet;
    private List<Participation_BL> utilisateurs_projet;
    private List<Etape_Worflow_BL> colonne_Du_Projet;

    public Projet_BL(String nom_projet, String description_projet, Utilisateur_BL utilisateur_createur_projet, List<Participation_BL> utilisateurs_projet, List<Etape_Worflow_BL> colonne_Du_Projet) {
        this.nom_projet = nom_projet;
        this.description_projet = description_projet;
        this.utilisateur_createur_projet = utilisateur_createur_projet;
        this.utilisateurs_projet = utilisateurs_projet;
        this.colonne_Du_Projet = colonne_Du_Projet;
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

    public List<Participation_BL> getUtilisateurs_projet() {
        return utilisateurs_projet;
    }

    public void setUtilisateurs_projet(List<Participation_BL> utilisateurs_projet) {
        this.utilisateurs_projet = utilisateurs_projet;
    }

    public List<Etape_Worflow_BL> getColonne_Du_Projet() {
        return colonne_Du_Projet;
    }

    public void setColonne_Du_Projet(List<Etape_Worflow_BL> colonne_Du_Projet) {
        this.colonne_Du_Projet = colonne_Du_Projet;
    }
}
