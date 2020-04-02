package be.technobel.ucmppg.bl.service.creation;

import be.technobel.ucmppg.bl.dto.projet.ProjetDTO;
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

            ProjetEntity projetEntity=new ProjetEntity();
            projetEntity.setNomDeProjet(nom);
            projetEntity.setDescriptionDeProjet(description);
            projetEntity.setUtilisateurCreateur(utilisateurEntity);

            RoleProjetEntity roleAdmininistrateurEntity=new RoleProjetEntity();
            roleAdmininistrateurEntity.setNomDeRole("administrateur");
            Set<DroitProjetEntity> droitAdministrateurEntities=new HashSet<>();
            droitProjetRepository.findAll().forEach(
                    droitAdministrateurEntities::add
            );
            roleAdmininistrateurEntity.setDroitProjets(droitAdministrateurEntities);

            RoleProjetEntity roleModerateurEntity=new RoleProjetEntity();
            roleModerateurEntity.setNomDeRole("modérateur");
            Set<DroitProjetEntity> droitModerateurEntities=new HashSet<>();
            droitModerateurEntities.add(
                    droitProjetRepository.findDroitProjetByNomDroit("gérer les taches")
            );
            droitModerateurEntities.add(
                    droitProjetRepository.findDroitProjetByNomDroit("gérer les rôles")
            );
            droitModerateurEntities.add(
                    droitProjetRepository.findDroitProjetByNomDroit("Créer des tâches")
            );
            droitModerateurEntities.add(
                    droitProjetRepository.findDroitProjetByNomDroit("inviter des collaborateurs")
            );
            droitModerateurEntities.add(
                    droitProjetRepository.findDroitProjetByNomDroit("prendre une tâche")
            );
            roleModerateurEntity.setDroitProjets(droitModerateurEntities);

            RoleProjetEntity roleMembreEntity=new RoleProjetEntity();
            roleMembreEntity.setNomDeRole("membre");
            Set<DroitProjetEntity> droitMembreEntities=new HashSet<>();
            droitMembreEntities.add(
                    droitProjetRepository.findDroitProjetByNomDroit("prendre une tâche")
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
            etapeAFaireEntity.setNomEtapeWorkflow("à faire");
            etapeAFaireEntity.setDescriptionEtapeWorkflow("taches à faire");


            EtapeWorkflowEntity etapeEnCoursEntity=new EtapeWorkflowEntity();
            etapeEnCoursEntity.setConstrainteAffectation(ConstrainteAffectationEnum.MEME);
            etapeEnCoursEntity.setEstPrenableEtapeWorkflow(false);
            etapeEnCoursEntity.setNomEtapeWorkflow("en cours");
            etapeEnCoursEntity.setDescriptionEtapeWorkflow("taches en cours");

            EtapeWorkflowEntity etapeFaiteEntity=new EtapeWorkflowEntity();
            etapeFaiteEntity.setConstrainteAffectation(ConstrainteAffectationEnum.AUCUN);
            etapeFaiteEntity.setEstPrenableEtapeWorkflow(false);
            etapeFaiteEntity.setNomEtapeWorkflow("fini");
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
