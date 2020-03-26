package be.technobel.ucmppg.API_Projet.DTO;

import be.technobel.ucmppg.BL.Models.Tache_BL;
import be.technobel.ucmppg.DAL.Models.UniteDeTempsEnum;

import java.util.List;

public class Tache_DAO {
    private String nom_Tache;
    private String description_workflow;
    private List<Tache_DAO> tache_enfants;
    private List<Tache_DAO> tache_Parents;
    private int estimation_Temps_Tache;
    private UniteDeTempsEnum uniteDeTempsEnum;
    private List<Historique_DTO> historique;
    private Utilisateur_DTO utilisateur_Tache;

    public Tache_DAO(Tache_BL tache) {
        this.nom_Tache = tache.getNom_Tache();
        this.description_workflow = tache.getDescription_workflow();
        for (Tache_BL a:tache.getTache_enfants()) {
            this.tache_enfants.add(new Tache_DAO(a));
        }
        for (Tache_BL a:tache.getTache_Parents()) {
            this.tache_Parents.add(new Tache_DAO(a));
        }
        this.estimation_Temps_Tache = tache.getEstimation_Temps_Tache();
        this.uniteDeTempsEnum = tache.getUniteDeTempsEnum();
        ths
    }

    public String getNom_Tache() {
        return nom_Tache;
    }

    public String getDescription_workflow() {
        return description_workflow;
    }

    public List<Tache_DAO> getTache_enfants() {
        return tache_enfants;
    }

    public List<Tache_DAO> getTache_Parents() {
        return tache_Parents;
    }

    public int getEstimation_Temps_Tache() {
        return estimation_Temps_Tache;
    }

    public UniteDeTempsEnum getUniteDeTempsEnum() {
        return uniteDeTempsEnum;
    }

    public List<Historique_DTO> getHistorique() {
        return historique;
    }

    public Utilisateur_DTO getUtilisateur_Tache() {
        return utilisateur_Tache;
    }
}
