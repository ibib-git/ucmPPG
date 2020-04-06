package be.technobel.ucmppg.bl.service.projet;

import be.technobel.ucmppg.bl.dto.projet.collaborateur.ChangerRoleCollaborateurDTO;
import be.technobel.ucmppg.dal.entities.ParticipationEntity;
import be.technobel.ucmppg.dal.entities.RoleProjetEntity;
import be.technobel.ucmppg.dal.entities.UtilisateurEntity;
import be.technobel.ucmppg.dal.repositories.ParticipationRepository;
import be.technobel.ucmppg.dal.repositories.RoleProjetRepository;
import be.technobel.ucmppg.dal.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.Optional;
@Service
public class ChangerRoleCollaborateurService {

    @Autowired
    private RoleProjetRepository roleProjetRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private ParticipationRepository participationRepository;

    public boolean execute(ChangerRoleCollaborateurDTO changerRoleCollaborateurDTO){


        Optional<RoleProjetEntity> roleProjetEntityOptional= roleProjetRepository.getRoleSurProjetByNomDeRole(
                changerRoleCollaborateurDTO.getIdProjet(),
                changerRoleCollaborateurDTO.getNomRole()
        );

        Optional<UtilisateurEntity> utilisateurEntityOptional=utilisateurRepository.findById(
                changerRoleCollaborateurDTO.getIdUtilisateur()
        );

        if(roleProjetEntityOptional.isPresent() && utilisateurEntityOptional.isPresent()){
            RoleProjetEntity roleProjetEntity=roleProjetEntityOptional.get();
            UtilisateurEntity utilisateurEntity=utilisateurEntityOptional.get();

            Optional<ParticipationEntity> participationEntityOptional=utilisateurEntity.getProjetsParticiperUtilisateur()
                    .stream()
                    .filter(
                            participationEntity -> {
                                return participationEntity.getProjetParticipation().getIdProjet() == changerRoleCollaborateurDTO.getIdProjet();
                            }
                    ).findFirst();

            if(participationEntityOptional.isPresent()){
                ParticipationEntity participationEntity=participationEntityOptional.get();
                participationEntity.setRoleDuParticipant(roleProjetEntity);

                participationRepository.save(participationEntity);

                return true;

            }

        }

        return false;
    }
}
