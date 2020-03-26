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

    private List<DroitBusiness> droit_Liste_Complete;
    private List<RoleBusiness> roles_par_defaut;
    private List<EtapeWorkflowBusiness> workflow_par_defaut;
    private List<ParticipationBusiness> participant_par_defaut;

    // Methode de creation d'un projet sans nom ni description avec 3 role et 4 colonnes
    public ProjetBusiness creationParDefautDeProjet(){

        // creation du projet
        ProjetBusiness p = new ProjetBusiness();

        // Droit possible dans un projet
        for (DroitPossible droit : DroitPossible.values()) {
            DroitBusiness newDroit = new DroitBusiness(droit.getNom());
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
    RoleBusiness administrateur_projet(){
        return new RoleBusiness("Administrateur",droit_Liste_Complete);
    }

    // Creation du Role Membre
    RoleBusiness membre_projet(){
        // Liste De droit par role pour les membres
        List<DroitBusiness> droit_par_membre = new ArrayList<>();
        for (DroitBusiness d: droit_Liste_Complete) {
            if(d.getNom_Droit_Projet().equals("Prendre une tache")){
                droit_par_membre.add(d);
            }
        }
        return new RoleBusiness("Membre",droit_par_membre);
    }

    // Creation du Role Modo
    private RoleBusiness moderateur_projet(){
        List<DroitBusiness> droit_Moderateur = new ArrayList<>();
        for (DroitBusiness d:droit_Liste_Complete) {
            switch (d.getNom_Droit_Projet()) {
                case "Gérer les roles":
                case "Gérer les taches":
                case "Prendre une tache":
                    droit_Moderateur.add(d);
                    break;
            }
        }
        return new RoleBusiness("Modérateur",droit_Moderateur);
    }

    // Initialisation des Colonnes de départ
    public List<EtapeWorkflowBusiness> etape_Workflow_Par_Defaut(){
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
    private EtapeWorkflowBusiness creation_des_colonne(Description_Par_Colonne_Par_Defaut descpt, ConstrainteAffectationEnum cons, boolean b){
        return new EtapeWorkflowBusiness(descpt.getNom_description(),descpt.getTexte(),b,roles_par_defaut,cons,null);
    }
}
