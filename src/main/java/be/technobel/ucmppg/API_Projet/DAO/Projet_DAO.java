package be.technobel.ucmppg.API_Projet.DAO;

import be.technobel.ucmppg.BL.Models.Etape_Worflow_BL;
import be.technobel.ucmppg.BL.Models.Projet_BL;

import java.util.List;

public class Projet_DAO {

    private String nom_projet_DAO;
    private String description_projet_DAO;
    private List<Etape_Worflow_BL> etape_projet_DAO;
    private Projet_BL projet_par_defaut;
    private List<String> email_utilisateur;

    public Projet_DAO(String nom_projet_DAO, String description_projet_DAO, List<Etape_Worflow_BL> etape_projet_DAO, Projet_BL projet_par_defaut, List<String> email_utilisateur) {
        this.nom_projet_DAO = nom_projet_DAO;
        this.description_projet_DAO = description_projet_DAO;
        this.etape_projet_DAO = etape_projet_DAO;
        this.projet_par_defaut = projet_par_defaut;
        this.email_utilisateur = email_utilisateur;
    }

    public String getNom_projet_DAO() {
        return nom_projet_DAO;
    }

    public void setNom_projet_DAO(String nom_projet_DAO) {
        this.nom_projet_DAO = nom_projet_DAO;
    }

    public String getDescription_projet_DAO() {
        return description_projet_DAO;
    }

    public void setDescription_projet_DAO(String description_projet_DAO) {
        this.description_projet_DAO = description_projet_DAO;
    }

    public List<Etape_Worflow_BL> getEtape_projet_DAO() {
        return etape_projet_DAO;
    }

    public void setEtape_projet_DAO(List<Etape_Worflow_BL> etape_projet_DAO) {
        this.etape_projet_DAO = etape_projet_DAO;
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
}
