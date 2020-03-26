package be.technobel.ucmppg.API_Projet.Service.Gestion;


public enum  Description_Par_Colonne_Par_Defaut {

    ZERO("Impossible à supprimer","Dernière colonne"),
    PREMIERE("colonne où les taches sont encore à faire","To Do"),
    SECONDE("colonne où les taches sont en cours","Doing"),
    TROISIEME("colonne où les taches sont sont terminées","Done");
   // QUATRIEME,
   // CINQUIEME

    private String texte;
    private String nom_description;

    Description_Par_Colonne_Par_Defaut(String texte, String nom_description) {
        this.texte = texte;
        this.nom_description = nom_description;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public String getNom_description() {
        return nom_description;
    }

    public void setNom_description(String nom_description) {
        this.nom_description = nom_description;
    }
}
