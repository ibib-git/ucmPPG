package be.technobel.ucmppg.BL.Models;

import java.util.List;

public class Role_Projet_BL {

    private String nom_Role_Projet;
    private List<Droit_Projet_BL> droits_Du_Role;

    public Role_Projet_BL(String nom_Role_Projet, List<Droit_Projet_BL> droits_Du_Role) {
        this.nom_Role_Projet = nom_Role_Projet;
        this.droits_Du_Role = droits_Du_Role;
    }

    public String getNom_Role_Projet() {
        return nom_Role_Projet;
    }

    public void setNom_Role_Projet(String nom_Role_Projet) {
        this.nom_Role_Projet = nom_Role_Projet;
    }

    public List<Droit_Projet_BL> getDroits_Du_Role() {
        return droits_Du_Role;
    }

    public void setDroits_Du_Role(List<Droit_Projet_BL> droits_Du_Role) {
        this.droits_Du_Role = droits_Du_Role;
    }
}
