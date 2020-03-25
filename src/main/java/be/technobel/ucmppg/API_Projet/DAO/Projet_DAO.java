package be.technobel.ucmppg.API_Projet.DAO;

import be.technobel.ucmppg.BL.Models.Etape_Worflow_BL;
import be.technobel.ucmppg.BL.Models.Projet_BL;
import be.technobel.ucmppg.BL.Models.Utilisateur_BL;

import java.util.List;

public class Projet_DAO {

    private Projet_BL projet_par_defaut;
    private List<String> email_utilisateur;
    private Utilisateur_BL createur_Projet;

    public Projet_DAO(Projet_BL projet_par_defaut, List<String> email_utilisateur,Utilisateur_BL createur_Projet) {
        this.projet_par_defaut = projet_par_defaut;
        this.email_utilisateur = email_utilisateur;
        this.createur_Projet = createur_Projet;
    }

    public Projet_DAO() {
    }


    public Projet_BL getProjet_par_defaut() {
        return projet_par_defaut;
    }

    public void setProjet_par_defaut(Projet_BL projet_par_defaut) {
        this.projet_par_defaut = projet_par_defaut;
    }

    public List<String> getEmail_utilisateur() {
        return email_utilisateur;
    }

    public void setEmail_utilisateur(List<String> email_utilisateur) {
        this.email_utilisateur = email_utilisateur;
    }

    public Utilisateur_BL getCreateur_Projet() {
        return createur_Projet;
    }

    public void setCreateur_Projet(Utilisateur_BL createur_Projet) {
        this.createur_Projet = createur_Projet;
    }
}
