package be.technobel.ucmppg.BL.Models;

import javax.servlet.http.Part;
import java.util.List;

public class Utilisateur_BL {

    private String pseudo_Utilisateur;
    private String nom_Utilisateur;
    private String prenom_Utilisateur;
    private String urlPhoto_Utilisateur;
    private String motDePasse_Utilisateur;
    private String email_Utilisateur;
    private String informationSupplementaire_Utilisateur;
    private List<Participation_BL> participation_projet;

    public Utilisateur_BL(String pseudo_Utilisateur, String nom_Utilisateur, String prenom_Utilisateur, String urlPhoto_Utilisateur, String motDePasse_Utilisateur, String email_Utilisateur, String informationSupplementaire_Utilisateur, List<Participation_BL> participation_projet) {
        this.pseudo_Utilisateur = pseudo_Utilisateur;
        this.nom_Utilisateur = nom_Utilisateur;
        this.prenom_Utilisateur = prenom_Utilisateur;
        this.urlPhoto_Utilisateur = urlPhoto_Utilisateur;
        this.motDePasse_Utilisateur = motDePasse_Utilisateur;
        this.email_Utilisateur = email_Utilisateur;
        this.informationSupplementaire_Utilisateur = informationSupplementaire_Utilisateur;
        this.participation_projet = participation_projet;
    }

    public Utilisateur_BL() {
    }

    public String getPseudo_Utilisateur() {
        return pseudo_Utilisateur;
    }

    public void setPseudo_Utilisateur(String pseudo_Utilisateur) {
        this.pseudo_Utilisateur = pseudo_Utilisateur;
    }

    public String getNom_Utilisateur() {
        return nom_Utilisateur;
    }

    public void setNom_Utilisateur(String nom_Utilisateur) {
        this.nom_Utilisateur = nom_Utilisateur;
    }

    public String getPrenom_Utilisateur() {
        return prenom_Utilisateur;
    }

    public void setPrenom_Utilisateur(String prenom_Utilisateur) {
        this.prenom_Utilisateur = prenom_Utilisateur;
    }

    public String getUrlPhoto_Utilisateur() {
        return urlPhoto_Utilisateur;
    }

    public void setUrlPhoto_Utilisateur(String urlPhoto_Utilisateur) {
        this.urlPhoto_Utilisateur = urlPhoto_Utilisateur;
    }

    public String getMotDePasse_Utilisateur() {
        return motDePasse_Utilisateur;
    }

    public void setMotDePasse_Utilisateur(String motDePasse_Utilisateur) {
        this.motDePasse_Utilisateur = motDePasse_Utilisateur;
    }

    public String getEmail_Utilisateur() {
        return email_Utilisateur;
    }

    public void setEmail_Utilisateur(String email_Utilisateur) {
        this.email_Utilisateur = email_Utilisateur;
    }

    public String getInformationSupplementaire_Utilisateur() {
        return informationSupplementaire_Utilisateur;
    }

    public void setInformationSupplementaire_Utilisateur(String informationSupplementaire_Utilisateur) {
        this.informationSupplementaire_Utilisateur = informationSupplementaire_Utilisateur;
    }

    public List<Participation_BL> getParticipation_projet() {
        return participation_projet;
    }

    public void setParticipation_projet(List<Participation_BL> participation_projet) {
        this.participation_projet = participation_projet;
    }
}
