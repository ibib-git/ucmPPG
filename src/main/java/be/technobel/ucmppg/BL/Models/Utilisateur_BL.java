package be.technobel.ucmppg.BL.Models;

public class Utilisateur_BL {

    private String nom_utilisateur;

    public Utilisateur_BL(String nom_utilisateur) {
        this.nom_utilisateur = nom_utilisateur;
    }

    public String getNom_utilisateur() {
        return nom_utilisateur;
    }

    public void setNom_utilisateur(String nom_utilisateur) {
        this.nom_utilisateur = nom_utilisateur;
    }
}
