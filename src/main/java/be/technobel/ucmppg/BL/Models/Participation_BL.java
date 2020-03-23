package be.technobel.ucmppg.BL.Models;

public class Participation_BL {

    private Role_Projet_BL role_projet;
    private Utilisateur_BL utilisateur;
    private Projet_BL projet;

    public Participation_BL(Role_Projet_BL role_projet, Utilisateur_BL utilisateur, Projet_BL projet) {
        this.role_projet = role_projet;
        this.utilisateur = utilisateur;
        this.projet = projet;
    }

    public Participation_BL() {
    }

    public Role_Projet_BL getRole_projet() {
        return role_projet;
    }

    public void setRole_projet(Role_Projet_BL role_projet) {
        this.role_projet = role_projet;
    }

    public Utilisateur_BL getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur_BL utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Projet_BL getProjet() {
        return projet;
    }

    public void setProjet(Projet_BL projet) {
        this.projet = projet;
    }
}
