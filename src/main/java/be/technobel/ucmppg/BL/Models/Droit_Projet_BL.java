package be.technobel.ucmppg.BL.Models;

public class Droit_Projet_BL {

    private String nom_Droit_Projet;

    public Droit_Projet_BL(String nom_Droit_Projet) {
        this.nom_Droit_Projet = nom_Droit_Projet;
    }

    public Droit_Projet_BL() {
    }

    public String getNom_Droit_Projet() {
        return nom_Droit_Projet;
    }

    public void setNom_Droit_Projet(String nom_Droit_Projet) {
        this.nom_Droit_Projet = nom_Droit_Projet;
    }
}