package be.technobel.ucmppg.bl.service.creation;

import be.technobel.ucmppg.bl.service.gestion.colonneParDefautEnum;
import be.technobel.ucmppg.bl.service.gestion.DroitPossibleEnum;
import be.technobel.ucmppg.bl.dto.*;
import be.technobel.ucmppg.bl.dto.projet.ProjetCreationDTO;
import be.technobel.ucmppg.dal.entities.ConstrainteAffectationEnum;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CreationParDefautService {

    private List<DroitDTO> droitListeComplete;
    private List<RoleDTO> rolesParDefaut;
    private List<EtapeWorkflowDTO> workflowParDefaut;
    private List<ParticipationDTO> participantParDefaut;

    // Methode de creation d'un projet sans nom ni description avec 3 role et 4 colonnes
    public ProjetCreationDTO creationParDefautDeProjet(){

        // creation du projet
        ProjetCreationDTO p = new ProjetCreationDTO();

        // Droit possible dans un projet
        for (DroitPossibleEnum droit : DroitPossibleEnum.values()) {
            DroitDTO newDroit = new DroitDTO(droit.getNom());
            droitListeComplete.add(newDroit);
        }

        // Role ADMINISTRATEUR
        rolesParDefaut.add(creerRoleAdministrateurProjet());

        // Role MEMBRE
        rolesParDefaut.add(creerRoleMembreProjet());

        // Role MODERATEUR
        rolesParDefaut.add(creerModerateurProjet());

        // INITIALISATION DU PROJET avec :
        // 3 role et 4 colonne
//        p.getProjetParDefaut().setColonneDuProjet(workflowParDefaut);
        return p;
    }

    // Creation du Role Admin
    RoleDTO creerRoleAdministrateurProjet(){
        return new RoleDTO("Administrateur", droitListeComplete);
    }

    // Creation du Role Membre
    RoleDTO creerRoleMembreProjet(){
        // Liste De droit par role pour les membres
        List<DroitDTO> droitParMembre = new ArrayList<>();
        for (DroitDTO d: droitListeComplete) {
            if(d.getNom().equals("Prendre une tache")){
                droitParMembre.add(d);
            }
        }
        return new RoleDTO("Membre",droitParMembre);
    }

    // Creation du Role Modo
    private RoleDTO creerModerateurProjet(){
        List<DroitDTO> droitModerateur = new ArrayList<>();
        for (DroitDTO d: droitListeComplete) {
            switch (d.getNom()) {
                case "Gérer les roles":
                case "Gérer les taches":
                case "Prendre une tache":
                    droitModerateur.add(d);
                    break;
            }
        }
        return new RoleDTO("Modérateur",droitModerateur);
    }

    // Initialisation des Colonnes de départ
    public List<EtapeWorkflowDTO> creerEtapesParDefaut(){
        for (colonneParDefautEnum descpt: colonneParDefautEnum.values()) {
            if(descpt.getNomDescription().equals("Dernière colonne")){
                workflowParDefaut.add(creationDesColonneDeDepart(descpt,ConstrainteAffectationEnum.AUCUN,false));
            }else{
                workflowParDefaut.add(creationDesColonneDeDepart(descpt,ConstrainteAffectationEnum.MEME,true));
            }

        }
        return workflowParDefaut;
    }

    // Creation des Colonnes de départ
    private EtapeWorkflowDTO creationDesColonneDeDepart(colonneParDefautEnum descpt, ConstrainteAffectationEnum cons, boolean b){
        return new EtapeWorkflowDTO(descpt.getNomDescription(),descpt.getTexte(),b, rolesParDefaut,cons,null);
    }
}
