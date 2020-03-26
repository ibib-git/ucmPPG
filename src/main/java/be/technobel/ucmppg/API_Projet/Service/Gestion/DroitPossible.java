package be.technobel.ucmppg.API_Projet.Service.Gestion;

public enum DroitPossible {

    GERERTACHE("Gérer les taches"),
    GERERROLE("Gérer les roles"),
    PRENDRETACHE("Prendre une tache"),
    INVITER("Inviter des collaborateur"),
    CREERTACHE("Créer des taches"),
    CREERROLE("Créer des roles");

    private String nom;

    DroitPossible(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
