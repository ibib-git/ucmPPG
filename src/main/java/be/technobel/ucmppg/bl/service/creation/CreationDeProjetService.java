package be.technobel.ucmppg.bl.service.creation;

import be.technobel.ucmppg.bl.dto.projet.ProjetDTO;
import be.technobel.ucmppg.configuration.Constantes;
import be.technobel.ucmppg.dal.entities.*;
import be.technobel.ucmppg.dal.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CreationDeProjetService implements CreationDeProjetInterface {

    @Autowired
    private RoleProjetRepository roleProjetRepository;
    @Autowired
    private EtapeWorkflowRepository etapeWorkflowRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private ProjetRepository projetRepository;
    @Autowired
    private ParticipationRepository participationRepository;
    @Autowired
    private DroitProjetRepository droitProjetRepository;
    @Override
    public ProjetDTO execute(String nom, String description, long idCreateur) {
        Optional<UtilisateurEntity> utilisateurOptionalEntity=utilisateurRepository.findById(idCreateur);

        ProjetDTO projetDTO=null;
        if(utilisateurOptionalEntity.isPresent()){
            UtilisateurEntity utilisateurEntity=utilisateurOptionalEntity.get();
            utilisateurEntity.setProjetsParticiperUtilisateur(new HashSet<>());//TODO Bastien : que se passe t il si il a deja des participations dans d'autres projets ?
            ProjetEntity projetEntity=new ProjetEntity();
            projetEntity.setNomDeProjet(nom);
            projetEntity.setDescriptionDeProjet(description);
            projetEntity.setUtilisateurCreateur(utilisateurEntity);
            RoleProjetEntity roleAdmininistrateurEntity=new RoleProjetEntity();
            roleAdmininistrateurEntity.setNomDeRole(Constantes.ROLE_ADMINISTRATEUR);
            Set<DroitProjetEntity> droitAdministrateurEntities=new HashSet<>();
            droitProjetRepository.findAll().forEach(
                    droitAdministrateurEntities::add
            );
            roleAdmininistrateurEntity.setDroitProjets(droitAdministrateurEntities);
            RoleProjetEntity roleModerateurEntity=new RoleProjetEntity();
            roleModerateurEntity.setNomDeRole(Constantes.ROLE_MODERATEUR);
            Set<DroitProjetEntity> droitModerateurEntities=new HashSet<>();
            droitModerateurEntities.add(
                    droitProjetRepository.findDroitProjetByNomDroit(Constantes.DROIT_GERER_TACHES)
            );
            droitModerateurEntities.add(
                    droitProjetRepository.findDroitProjetByNomDroit(Constantes.DROIT_GERER_ROLES)
            );
            droitModerateurEntities.add(
                    droitProjetRepository.findDroitProjetByNomDroit(Constantes.DROIT_CREER_TACHES)
            );
            droitModerateurEntities.add(
                    droitProjetRepository.findDroitProjetByNomDroit(Constantes.DROIT_INVITER_COLLABORATEURS)
            );
            droitModerateurEntities.add(
                    droitProjetRepository.findDroitProjetByNomDroit(Constantes.DROIT_PRENDRE_TACHE)
            );
            droitModerateurEntities.add(
                    droitProjetRepository.findDroitProjetByNomDroit(Constantes.DROIT_VALIDER_TACHE)
            );
            roleModerateurEntity.setDroitProjets(droitModerateurEntities);
            RoleProjetEntity roleMembreEntity=new RoleProjetEntity();
            roleMembreEntity.setNomDeRole(Constantes.ROLE_MEMBRE);
            Set<DroitProjetEntity> droitMembreEntities=new HashSet<>();
            droitMembreEntities.add(
                    droitProjetRepository.findDroitProjetByNomDroit(Constantes.DROIT_PRENDRE_TACHE)
            );
            roleMembreEntity.setDroitProjets(droitMembreEntities);
            Set<RoleProjetEntity> roleProjetEntities=new HashSet<>();
            roleProjetEntities.add(roleAdmininistrateurEntity);
            roleProjetEntities.add(roleModerateurEntity);
            roleProjetEntities.add(roleMembreEntity);

            projetEntity.setRolesProjet(roleProjetEntities);

            Set<EtapeWorkflowEntity> etapeWorkflowEntities=new HashSet<>();
            EtapeWorkflowEntity etapeAFaireEntity=new EtapeWorkflowEntity();
            etapeAFaireEntity.setConstrainteAffectation(ConstrainteAffectationEnum.AUCUN);
            etapeAFaireEntity.setEstPrenableEtapeWorkflow(true);
            etapeAFaireEntity.setNomEtapeWorkflow(Constantes.ETAPE_AFAIRE_DEFAUT);
            etapeAFaireEntity.setNumOrdreEtapeWorkflow(1);
            etapeAFaireEntity.setDescriptionEtapeWorkflow("taches à faire");
            EtapeWorkflowEntity etapeEnCoursEntity=new EtapeWorkflowEntity();
            etapeEnCoursEntity.setConstrainteAffectation(ConstrainteAffectationEnum.MEME);
            etapeEnCoursEntity.setEstPrenableEtapeWorkflow(false);
            etapeEnCoursEntity.setNumOrdreEtapeWorkflow(2);
            etapeEnCoursEntity.setNomEtapeWorkflow(Constantes.ETAPE_ENCOURS_DEFAUT);
            etapeEnCoursEntity.setDescriptionEtapeWorkflow("taches en cours");
            EtapeWorkflowEntity etapeFaiteEntity=new EtapeWorkflowEntity();
            etapeFaiteEntity.setConstrainteAffectation(ConstrainteAffectationEnum.AUCUN);
            etapeFaiteEntity.setEstPrenableEtapeWorkflow(false);
            etapeFaiteEntity.setNumOrdreEtapeWorkflow(3);
            etapeFaiteEntity.setNomEtapeWorkflow(Constantes.ETAPE_FINI_DEFAUT);
            etapeFaiteEntity.setDescriptionEtapeWorkflow("taches finies");
            etapeWorkflowEntities.add(etapeAFaireEntity);
            etapeWorkflowEntities.add(etapeEnCoursEntity);
            etapeWorkflowEntities.add(etapeFaiteEntity);

            projetEntity.setEtapeWorkflows(etapeWorkflowEntities);
            ParticipationEntity participationEntity = new ParticipationEntity();
            participationEntity.setUtilisateurParticipant(utilisateurEntity);
            participationEntity.setRoleDuParticipant(roleAdmininistrateurEntity);
            participationEntity.setProjetParticipation(projetEntity);
            roleProjetRepository.save(roleAdmininistrateurEntity);
            roleProjetRepository.save(roleModerateurEntity);
            roleProjetRepository.save(roleMembreEntity);
            etapeWorkflowRepository.save(etapeAFaireEntity);
            etapeWorkflowRepository.save(etapeEnCoursEntity);
            etapeWorkflowRepository.save(etapeFaiteEntity);
            projetRepository.save(projetEntity);
            participationRepository.save(participationEntity);
            projetEntity.getMembresDuProjet().add(participationEntity);
            utilisateurEntity.getProjetsParticiperUtilisateur().add(participationEntity);
            projetRepository.save(projetEntity);
            utilisateurRepository.save(utilisateurEntity);

            projetDTO=new ProjetDTO(projetEntity);
        }
        return projetDTO;
    }

}
