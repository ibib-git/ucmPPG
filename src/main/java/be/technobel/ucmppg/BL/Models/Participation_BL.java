package be.technobel.ucmppg.BL.Models;

public class BussinesParticipation {

    private String role_projet;
    private BussinesUtilisateur utilisateur;
    private BussinesProjet projet;

    public BussinesParticipation(String role_projet, BussinesUtilisateur utilisateur, BussinesProjet projet) {
        this.role_projet = role_projet;
        this.utilisateur = utilisateur;
        this.projet = projet;
    }

    public BussinesParticipation() {
    }

    public String getRole_projet() {
        return role_projet;
    }

    public void setRole_projet(String role_projet) {
        this.role_projet = role_projet;
    }

    public BussinesUtilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(BussinesUtilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public BussinesProjet getProjet() {
        return projet;
    }

    public void setProjet(BussinesProjet projet) {
        this.projet = projet;
    }
}
