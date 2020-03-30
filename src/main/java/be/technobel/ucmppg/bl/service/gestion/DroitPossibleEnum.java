package be.technobel.ucmppg.bl.service.gestion;

public enum DroitPossibleEnum {

    GERERTACHE("Gérer les taches"),
    GERERROLE("Gérer les roles"),
    PRENDRETACHE("Prendre une tache"),
    INVITER("Inviter des collaborateur"),
    CREERTACHE("Créer des taches"),
    CREERROLE("Créer des roles");

    private String nom;

    DroitPossibleEnum(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
