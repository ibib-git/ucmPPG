package be.technobel.ucmppg.bl.service.droits;

import be.technobel.ucmppg.dal.entities.*;
import be.technobel.ucmppg.dal.repositories.DroitProjetRepository;
import be.technobel.ucmppg.dal.repositories.ProjetRepository;
import be.technobel.ucmppg.dal.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenVerificationDroitService {

    private final UtilisateurRepository utilisateurRepository;
    private final DroitProjetRepository droitProjetRepository;
    private final ProjetRepository projetRepository;

    public TokenVerificationDroitService(UtilisateurRepository utilisateurRepository, DroitProjetRepository droitProjetRepository, ProjetRepository projetRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.droitProjetRepository = droitProjetRepository;
        this.projetRepository = projetRepository;
    }

    //TODO TOKEN : service a modifier avec le check du token
    public boolean verificationDroitUtilisateurService (long idUtilisateur, String droit, long idProjet)
    {
        Optional<UtilisateurEntity> optionalUtilisateurEntity = utilisateurRepository.findById(idUtilisateur);
        Optional<ProjetEntity> optionalProjetEntity = projetRepository.findByIdProjet(idProjet);
        DroitProjetEntity droitProjet = droitProjetRepository.findDroitProjetByNomDroit(droit);

        // check si l utilisateur existe et si le projet existe
        if (optionalUtilisateurEntity.isPresent() && optionalProjetEntity.isPresent())
        {

            UtilisateurEntity utilisateurEntity = optionalUtilisateurEntity.get();
            ProjetEntity projetEntity = optionalProjetEntity.get();

            Optional<RoleProjetEntity> optionalRoleUtilisateur = projetEntity.getMembresDuProjet().stream()
                    .filter(m -> m.getUtilisateurParticipant().equals(utilisateurEntity) && m.isActif())
                    .map(ParticipationEntity::getRoleDuParticipant).findFirst();

            // check si l'utilisateur à le droit
            return optionalRoleUtilisateur.map(roleProjetEntity -> roleProjetEntity.getDroitProjets().contains(droitProjet)).orElse(false);

        } return false ; //utilisateur ou projet n existe pas

    }
}
