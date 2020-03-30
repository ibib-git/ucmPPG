package be.technobel.ucmppg.BL.Service.Gestion;


public enum colonneParDefautEnum {

    ZERO("Impossible à supprimer","Dernière colonne"),
    PREMIERE("colonne où les taches sont encore à faire","To Do"),
    SECONDE("colonne où les taches sont en cours","Doing"),
    TROISIEME("colonne où les taches sont sont terminées","Done");
   // QUATRIEME,
   // CINQUIEME

    private String texte;
    private String nomDescription;

    colonneParDefautEnum(String texte, String nom_description) {
        this.texte = texte;
        this.nomDescription = nom_description;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public String getNomDescription() {
        return nomDescription;
    }

    public void setNomDescription(String nomDescription) {
        this.nomDescription = nomDescription;
    }
}
