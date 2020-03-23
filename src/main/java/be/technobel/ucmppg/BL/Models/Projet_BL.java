package be.technobel.ucmppg.BL.Models;

import java.util.List;

public class BussinesProjet {

    private String nom_projet;
    private String description_projet;
    private BussinesUtilisateur utilisateur_createur_projet;
    private List<BussinesUtilisateur> utilisateurs_projet;

    public BussinesProjet(String nom_projet, String description_projet, BussinesUtilisateur utilisateur_createur_projet) {
        this.nom_projet = nom_projet;
        this.description_projet = description_projet;
        this.utilisateur_createur_projet = utilisateur_createur_projet;
        this.utilisateurs_projet = utilisateurs_projet;
    }

    public BussinesProjet() {
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

    public BussinesUtilisateur getUtilisateur_createur_projet() {
        return utilisateur_createur_projet;
    }

    public void setUtilisateur_createur_projet(BussinesUtilisateur utilisateur_createur_projet) {
        this.utilisateur_createur_projet = utilisateur_createur_projet;
    }

    public List<BussinesUtilisateur> getUtilisateurs_projet() {
        return utilisateurs_projet;
    }

    public void setUtilisateurs_projet(List<BussinesUtilisateur> utilisateurs_projet) {
        this.utilisateurs_projet = utilisateurs_projet;
    }
}
