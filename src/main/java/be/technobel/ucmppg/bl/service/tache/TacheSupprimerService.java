package be.technobel.ucmppg.bl.service.tache;

import be.technobel.ucmppg.dal.repositories.EtapeWorkflowRepository;
import be.technobel.ucmppg.dal.repositories.ProjetRepository;
import be.technobel.ucmppg.dal.repositories.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TacheSupprimerService {

    @Autowired
    private EtapeWorkflowRepository etapeWorkflowRepository;
    @Autowired
    private ProjetRepository projetRepository;
    @Autowired
    private TacheRepository tacheRepository;

    public boolean execute( Long idtache, boolean choix, Long idWorkflow, Long idProjet){

        return false;
    }
}
