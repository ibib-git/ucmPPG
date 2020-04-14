package be.technobel.ucmppg.bl.service.projet;

import be.technobel.ucmppg.bl.service.utilisateur.RecuperationUtilisateurService;
import be.technobel.ucmppg.configuration.Constantes;
import be.technobel.ucmppg.dal.entities.*;
import be.technobel.ucmppg.dal.repositories.ProjetRepository;
import be.technobel.ucmppg.dal.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class ChangerOrdreEtapeService {

    @Autowired
    ProjetRepository projetRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    public Boolean execute(Long idUtilisateur, Long idEtape, int nvOrdre)
    {
        Optional<ProjetEntity> projetEntityOptional = projetRepository.getProjetByEtapeWorkflows(idEtape);
        Optional<UtilisateurEntity> utilisateurEntityOptional = utilisateurRepository.findById(idUtilisateur);

        //check si le projet existe et si l'utilisateur existe
        if (projetEntityOptional.isPresent() && utilisateurEntityOptional.isPresent())
            {
                ProjetEntity projetEntity = projetEntityOptional.get();
                UtilisateurEntity utilisateurEntity = utilisateurEntityOptional.get();

            Optional<RoleProjetEntity> roleUtilisateur = projetEntity.getMembresDuProjet().stream()
                    .filter(m -> m.getUtilisateurParticipant().equals(utilisateurEntity))
                    .map(ParticipationEntity::getRoleDuParticipant).findFirst();

            // check si l'utilisateur participe bien au projet et si le nouvel ordre est compris dans les bornes
            if (roleUtilisateur.isPresent() && nvOrdre <= projetEntity.getEtapeWorkflows().size() && nvOrdre >= 0)
            {
                Optional<DroitProjetEntity> droitUtilisateur = roleUtilisateur.get().getDroitProjets().stream()
                        .filter(d -> d.getNomDroit().equals(Constantes.DROIT_CHANGER_ORDRE_ETAPE))
                        .findFirst();

                Set<EtapeWorkflowEntity> etapeWorkflowEntitySet = projetEntity.getEtapeWorkflows();
                EtapeWorkflowEntity etapeInput = etapeWorkflowEntitySet.stream()
                        .filter(e -> e.getIdEtapeWorkflow().equals(idEtape)).findFirst().orElse(null);

                // check si l'etape du workflow en input existe bien dans le projet et si l'utilisateur à le droit
                if (etapeInput !=  null && droitUtilisateur.isPresent())
                {
                    //check si il veut déplacer une étape vers la droite ou vers la gauche
                    if (nvOrdre < etapeInput.getNumOrdreEtapeWorkflow())
                    {
                        // déplacer les autres étapes impactées vers la droite
                        etapeWorkflowEntitySet.stream()
                                .filter(e -> e.getNumOrdreEtapeWorkflow() >= nvOrdre && e.getNumOrdreEtapeWorkflow() < etapeInput.getNumOrdreEtapeWorkflow())
                                .forEach(e -> e.setNumOrdreEtapeWorkflow(e.getNumOrdreEtapeWorkflow()+1));
                    } else {
                        // déplacer les autres étapes impactées vers la gauche
                        etapeWorkflowEntitySet.stream()
                                .filter(e -> e.getNumOrdreEtapeWorkflow() <= nvOrdre && e.getNumOrdreEtapeWorkflow() > etapeInput.getNumOrdreEtapeWorkflow())
                                .forEach(e -> e.setNumOrdreEtapeWorkflow(e.getNumOrdreEtapeWorkflow()-1));
                    }

                    etapeWorkflowEntitySet.stream()
                            .filter(e -> e.equals(etapeInput))
                            .forEach(e -> e.setNumOrdreEtapeWorkflow(nvOrdre));

                    projetEntity.setEtapeWorkflows(etapeWorkflowEntitySet);
                    projetRepository.save(projetEntity);
                    return true;

                } else  return false;

            } return false;
        } return false;
    }
}
