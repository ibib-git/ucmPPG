package be.technobel.ucmppg.API_Projet.Service.Gestion;

import be.technobel.ucmppg.BL.Models.*;
import be.technobel.ucmppg.DAL.Models.ConstrainteAffectationEnum;

import java.util.ArrayList;
import java.util.List;

public class Component_Creation {

    private List<Droit_Projet_BL> droit_Liste_Complete;
    private List<Role_Projet_BL> roles_par_defaut;
    private List<Etape_Worflow_BL> workflow_par_defaut;
    private List<Participation_BL> participant_par_defaut;

    // Methode de creation d'un projet sans nom ni description avec 3 role et 3 colonnes
    public Projet_BL creationParDefautDeProjet(Utilisateur_BL createur){

        // creation du projet
        Projet_BL p = new Projet_BL();

        //TODO Les droits Revoir
        // Installation des droits
        for (DroitPossible droit : DroitPossible.values()) {
            Droit_Projet_BL newDroit = new Droit_Projet_BL(droit.getNom());
            droit_Liste_Complete.add(newDroit);
        }
        // Role ADMINISTATEUR
        Role_Projet_BL admin = new Role_Projet_BL("Administrateur",droit_Liste_Complete);
        roles_par_defaut.add(admin);

        // Liste De droit par role
        List<Droit_Projet_BL> droit_par_role = new ArrayList<>();
        for (Droit_Projet_BL d: droit_Liste_Complete) {
            if(d.getNom_Droit_Projet().equals("Prendre une tache")){
                droit_par_role.add(d);
            }
        }

        // Role MEMBRE
        roles_par_defaut.add(new Role_Projet_BL("Membre",droit_par_role));

        for (Droit_Projet_BL d:droit_Liste_Complete) {
            if(d.getNom_Droit_Projet().equals("Gérer les roles")){
                droit_par_role.add(d);
            }else if(d.getNom_Droit_Projet().equals("Gérer les taches")){
                droit_par_role.add(d);
            }
        }
        // Role MODERATEUR
        roles_par_defaut.add(new Role_Projet_BL("Modérateur",droit_par_role));
        participant_par_defaut.add(new Participation_BL(admin,createur,p));

        // DESCRIPTION des 3 colonnes
        String description_colonne_toDo = "colonne où les taches sont encore à faire";
        String description_colonne_Doing = "colonne où les taches sont en cours";
        String description_colonne_Done = "colonne où les taches sont sont terminées";

        // CREATION des 3 colonne
        workflow_par_defaut.add(new Etape_Worflow_BL("To Do",description_colonne_toDo,true,roles_par_defaut, ConstrainteAffectationEnum.MEME,null));
        workflow_par_defaut.add(new Etape_Worflow_BL("Doing",description_colonne_Doing,true,roles_par_defaut, ConstrainteAffectationEnum.MEME,null));
        workflow_par_defaut.add(new Etape_Worflow_BL("Done",description_colonne_Done,true,roles_par_defaut, ConstrainteAffectationEnum.MEME,null));

        // INITIALISATION DU PROJET avec :
        // participant 1 le createur
        // 3 role et 3 colonne
        p.setColonne_Du_Projet(workflow_par_defaut);
        p.setUtilisateur_createur_projet(createur);
        p.setUtilisateurs_projet(participant_par_defaut);
        return p;
    }
}
