package be.technobel.ucmppg.BL.Models;

import be.technobel.ucmppg.DAL.Models.UniteDeTempsEnum;

import java.util.List;

public class Tache_BL {

    private String nom_Tache;
    private String description_workflow;
    private List<Tache_BL> tache_enfants;
    private List<Tache_BL> tache_Parents;
    private int estimation_Temps_Tache;
    private UniteDeTempsEnum uniteDeTempsEnum;

    public Tache_BL() {
    }

    public Tache_BL(String nom_Tache, String description_workflow, List<Tache_BL> tache_enfants, List<Tache_BL> tache_Parents, int estimation_Temps_Tache, UniteDeTempsEnum uniteDeTempsEnum) {
        this.nom_Tache = nom_Tache;
        this.description_workflow = description_workflow;
        this.tache_enfants = tache_enfants;
        this.tache_Parents = tache_Parents;
        this.estimation_Temps_Tache = estimation_Temps_Tache;
        this.uniteDeTempsEnum = uniteDeTempsEnum;
    }

    public String getNom_Tache() {
        return nom_Tache;
    }

    public void setNom_Tache(String nom_Tache) {
        this.nom_Tache = nom_Tache;
    }

    public String getDescription_workflow() {
        return description_workflow;
    }

    public void setDescription_workflow(String description_workflow) {
        this.description_workflow = description_workflow;
    }

    public List<Tache_BL> getTache_enfants() {
        return tache_enfants;
    }

    public void setTache_enfants(List<Tache_BL> tache_enfants) {
        this.tache_enfants = tache_enfants;
    }

    public List<Tache_BL> getTache_Parents() {
        return tache_Parents;
    }

    public void setTache_Parents(List<Tache_BL> tache_Parents) {
        this.tache_Parents = tache_Parents;
    }

    public int getEstimation_Temps_Tache() {
        return estimation_Temps_Tache;
    }

    public void setEstimation_Temps_Tache(int estimation_Temps_Tache) {
        this.estimation_Temps_Tache = estimation_Temps_Tache;
    }

    public UniteDeTempsEnum getUniteDeTempsEnum() {
        return uniteDeTempsEnum;
    }

    public void setUniteDeTempsEnum(UniteDeTempsEnum uniteDeTempsEnum) {
        this.uniteDeTempsEnum = uniteDeTempsEnum;
    }
}
