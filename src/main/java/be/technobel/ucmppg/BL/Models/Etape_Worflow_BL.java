package be.technobel.ucmppg.BL.Models;

import be.technobel.ucmppg.DAL.Models.ConstrainteAffectationEnum;

import java.util.List;

public class Etape_Worflow_BL {

    private String nom_workflow;
    private String description_workflow;
    private boolean estPrenable;
    private List<Role_Projet_BL> role_workflow;
    private ConstrainteAffectationEnum contrainte;
    private List<Tache_BL> tache_workflow;

    public Etape_Worflow_BL(String nom_workflow, String description_workflow, boolean estPrenable, List<Role_Projet_BL> role_workflow, ConstrainteAffectationEnum contrainte, List<Tache_BL> tache_workflow) {
        this.nom_workflow = nom_workflow;
        this.description_workflow = description_workflow;
        this.estPrenable = estPrenable;
        this.role_workflow = role_workflow;
        this.contrainte = contrainte;
        this.tache_workflow = tache_workflow;
    }

    public Etape_Worflow_BL() {
    }

    public String getNom_workflow() {
        return nom_workflow;
    }

    public void setNom_workflow(String nom_workflow) {
        this.nom_workflow = nom_workflow;
    }

    public String getDescription_workflow() {
        return description_workflow;
    }

    public void setDescription_workflow(String description_workflow) {
        this.description_workflow = description_workflow;
    }

    public boolean isEstPrenable() {
        return estPrenable;
    }

    public void setEstPrenable(boolean estPrenable) {
        this.estPrenable = estPrenable;
    }

    public List<Role_Projet_BL> getRole_workflow() {
        return role_workflow;
    }

    public void setRole_workflow(List<Role_Projet_BL> role_workflow) {
        this.role_workflow = role_workflow;
    }

    public ConstrainteAffectationEnum getContrainte() {
        return contrainte;
    }

    public void setContrainte(ConstrainteAffectationEnum contrainte) {
        this.contrainte = contrainte;
    }

    public List<Tache_BL> getTache_workflow() {
        return tache_workflow;
    }

    public void setTache_workflow(List<Tache_BL> tache_workflow) {
        this.tache_workflow = tache_workflow;
    }
}
