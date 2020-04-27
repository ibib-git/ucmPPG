package be.technobel.ucmppg.bl.service.tache;

import be.technobel.ucmppg.bl.dto.projet.taches.TacheCreationTacheDTO;
import be.technobel.ucmppg.bl.dto.projet.taches.TacheCreationTacheEnfantDTO;
import be.technobel.ucmppg.dal.entities.EtapeWorkflowEntity;
import be.technobel.ucmppg.dal.entities.ProjetEntity;
import be.technobel.ucmppg.dal.repositories.EtapeWorkflowRepository;
import be.technobel.ucmppg.dal.repositories.ProjetRepository;
import be.technobel.ucmppg.dal.repositories.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class TacheAjouterService {

    @Autowired
    private ProjetRepository projetRepository;
    @Autowired
    private EtapeWorkflowRepository etapeWorkflowRepository;
    @Autowired
    private TacheRepository tacheRepository;

    public boolean creationTacheParent(Long idWorkflow, TacheCreationTacheDTO tacheCreationTacheDTO){

        Optional<EtapeWorkflowEntity> optionalEtapeWorkflowRepository = etapeWorkflowRepository.findById(idWorkflow);

        if(optionalEtapeWorkflowRepository.isPresent()){

            EtapeWorkflowEntity etape = optionalEtapeWorkflowRepository.get();

            return true;

        }

        return false;
    }

    public boolean creatioTacheEnfant(Long idWorkflow, TacheCreationTacheEnfantDTO tacheCreationTacheEnfantDTO){

        Optional<EtapeWorkflowEntity> optionalEtapeWorkflowEntity = etapeWorkflowRepository.findById(idWorkflow);

        if(optionalEtapeWorkflowEntity.isPresent()){

            EtapeWorkflowEntity etape = optionalEtapeWorkflowEntity.get();
            return true;
        }
        return false;
    }
}
