package be.technobel.ucmppg.API_Projet.Service.Creation;

import be.technobel.ucmppg.API_Projet.Service.Gestion.Description_Par_Colonne_Par_Defaut;
import be.technobel.ucmppg.API_Projet.Service.Gestion.DroitPossible;
import be.technobel.ucmppg.BL.Models.*;
import be.technobel.ucmppg.DAL.Models.ConstrainteAffectationEnum;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class Service_Creation_Par_Defaut {

    private List<Droit_Projet_BL> droit_Liste_Complete;
    private List<Role_Projet_BL> roles_par_defaut;
    private List<Etape_Worflow_BL> workflow_par_defaut;
    private List<Participation_BL> participant_par_defaut;

    // Methode de creation d'un projet sans nom ni description avec 3 role et 4 colonnes
    public Projet_BL creationParDefautDeProjet(){

        // creation du projet
        Projet_BL p = new Projet_BL();

        // Droit possible dans un projet
        for (DroitPossible droit : DroitPossible.values()) {
            Droit_Projet_BL newDroit = new Droit_Projet_BL(droit.getNom());
            droit_Liste_Complete.add(newDroit);
        }

        // Role ADMINISTRATEUR
        roles_par_defaut.add(administrateur_projet());

        // Role MEMBRE
        roles_par_defaut.add(membre_projet());

        // Role MODERATEUR
        roles_par_defaut.add(moderateur_projet());

        // INITIALISATION DU PROJET avec :
        // 3 role et 4 colonne
        p.setColonne_Du_Projet(workflow_par_defaut);
        return p;
    }

    // Creation du Role Admin
    Role_Projet_BL administrateur_projet(){
        return new Role_Projet_BL("Administrateur",droit_Liste_Complete);
    }

    // Creation du Role Membre
    Role_Projet_BL membre_projet(){
        // Liste De droit par role pour les membres
        List<Droit_Projet_BL> droit_par_membre = new ArrayList<>();
        for (Droit_Projet_BL d: droit_Liste_Complete) {
            if(d.getNom_Droit_Projet().equals("Prendre une tache")){
                droit_par_membre.add(d);
            }
        }
        return new Role_Projet_BL("Membre",droit_par_membre);
    }

    // Creation du Role Modo
    private Role_Projet_BL moderateur_projet(){
        List<Droit_Projet_BL> droit_Moderateur = new ArrayList<>();
        for (Droit_Projet_BL d:droit_Liste_Complete) {
            switch (d.getNom_Droit_Projet()) {
                case "Gérer les roles":
                case "Gérer les taches":
                case "Prendre une tache":
                    droit_Moderateur.add(d);
                    break;
            }
        }
        return new Role_Projet_BL("Modérateur",droit_Moderateur);
    }

    // Initialisation des Colonnes de départ
    public List<Etape_Worflow_BL> etape_Workflow_Par_Defaut(){
        for (Description_Par_Colonne_Par_Defaut descpt: Description_Par_Colonne_Par_Defaut.values()) {
            if(descpt.getNom_description().equals("Dernière colonne")){
                workflow_par_defaut.add(creation_des_colonne(descpt,ConstrainteAffectationEnum.AUCUN,false));
            }else{
                workflow_par_defaut.add(creation_des_colonne(descpt,ConstrainteAffectationEnum.MEME,true));
            }

        }
        return workflow_par_defaut;
    }

    // Creation des Colonnes de départ
    private Etape_Worflow_BL creation_des_colonne(Description_Par_Colonne_Par_Defaut descpt,ConstrainteAffectationEnum cons,boolean b){
        return new Etape_Worflow_BL(descpt.getNom_description(),descpt.getTexte(),b,roles_par_defaut,cons,null);
    }
}
