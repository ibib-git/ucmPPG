package be.technobel.ucmppg.bl.service.projet;

import be.technobel.ucmppg.Exception.ErrorServiceException;
import be.technobel.ucmppg.bl.dto.projet.ProjetDTO;
import be.technobel.ucmppg.bl.service.droits.TokenVerificationDroitService;
import be.technobel.ucmppg.bl.service.utilisateur.RecuperationUtilisateurService;
import be.technobel.ucmppg.configuration.Constantes;
import be.technobel.ucmppg.dal.entities.*;
import be.technobel.ucmppg.dal.repositories.DroitProjetRepository;
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

    private final ProjetRepository projetRepository;
    private final TokenVerificationDroitService tokenVerificationDroitService;

    public ChangerOrdreEtapeService(ProjetRepository projetRepository, TokenVerificationDroitService tokenVerificationDroitService) {
        this.projetRepository = projetRepository;
        this.tokenVerificationDroitService = tokenVerificationDroitService;
    }


    public ProjetDTO execute(Long idUtilisateur, Long idEtape, int nvOrdre) throws ErrorServiceException {
        Optional<ProjetEntity> projetEntityOptional = this.projetRepository.getProjetByEtapeWorkflows(idEtape);

        //check si le projet existe
        if (projetEntityOptional.isPresent())
            {
                ProjetEntity projetEntity = projetEntityOptional.get();
                boolean aLeDroit = tokenVerificationDroitService.verificationDroitUtilisateurService(idUtilisateur,Constantes.DROIT_CHANGER_ORDRE_ETAPE,projetEntity.getIdProjet());

            // check si le nouvel ordre est compris dans les bornes et si l utilisateur a le droit
            if (nvOrdre <= projetEntity.getEtapeWorkflows().size() && nvOrdre >= 0 && aLeDroit)
            {
                Set<EtapeWorkflowEntity> etapeWorkflowEntitySet = projetEntity.getEtapeWorkflows();
                EtapeWorkflowEntity etapeInput = etapeWorkflowEntitySet.stream()
                        .filter(e -> e.getIdEtapeWorkflow().equals(idEtape)).findFirst().orElse(null);

                // check si l'etape du workflow en input existe bien dans le projet
                if (etapeInput !=  null)
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
                    //TODO DAMIEN : a remplacer (Guillaume advice)
                    etapeWorkflowEntitySet.stream()
                            .filter(e -> e.equals(etapeInput))
                            .forEach(e -> e.setNumOrdreEtapeWorkflow(nvOrdre));

                    projetEntity.setEtapeWorkflows(etapeWorkflowEntitySet);
                    this.projetRepository.save(projetEntity);
                    return new ProjetDTO(this.projetRepository.findById(projetEntity.getIdProjet()).get());

                } else  throw  new ErrorServiceException("Changer Ordre Etape workflow","La colonne n existe pas");

            } throw  new ErrorServiceException("Changer Ordre Etape workflow","L'utilisateur n'a pas le droit");

            } throw  new ErrorServiceException("Changer Ordre Etape workflow","Le projet n'existe pas ");

    }

}
