package be.technobel.ucmppg.bl.service.tache;

import be.technobel.ucmppg.dal.entities.EtapeWorkflowEntity;
import be.technobel.ucmppg.dal.entities.HistoriqueTacheEntity;
import be.technobel.ucmppg.dal.entities.TacheEntity;
import be.technobel.ucmppg.dal.repositories.EtapeWorkflowRepository;
import be.technobel.ucmppg.dal.repositories.HistoriqueTacheRepository;
import be.technobel.ucmppg.dal.repositories.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class TacheSupprimerService {

    @Autowired
    private EtapeWorkflowRepository etapeWorkflowRepository;
    @Autowired
    private TacheRepository tacheRepository;
    @Autowired
    private HistoriqueTacheRepository historiqueTacheRepository;

    private List<TacheEntity> tacheEntitiesEnfant = new ArrayList<>();


    public boolean execute(Long idtache, boolean choix) {
        Optional<TacheEntity> optionalTacheEntity = tacheRepository.findById(idtache);
        if (optionalTacheEntity.isPresent()) {
            TacheEntity tacheEntity = optionalTacheEntity.get();
            Optional<EtapeWorkflowEntity> optionalEtapeWorkflowEntity = etapeWorkflowRepository.findByTacheEntity(tacheEntity.getIdTache());
            if (optionalEtapeWorkflowEntity.isPresent()) {
                EtapeWorkflowEntity etapeWorkflowEntity = optionalEtapeWorkflowEntity.get();
                etapeWorkflowEntity.getTaches().remove(tacheEntity);
                etapeWorkflowRepository.suppressionDeTacheDansLienEtapeWorkflow(tacheEntity.getIdTache());
                etapeWorkflowRepository.save(etapeWorkflowEntity);
                if (!choix) {
                    Optional<List<TacheEntity>> optionalTacheEntities = tacheRepository.getAllTacheEnfant(idtache);
                    if (optionalTacheEntities.isPresent()) {
                        tacheEntitiesEnfant = optionalTacheEntities.get();
                        for (TacheEntity t : tacheEntitiesEnfant) {
                            etapeWorkflowEntity.getTaches().add(t);
                            tacheEntity.getTachesEnfants().remove(t);
                        }
                    }
                    historiqueTacheRepository.suppressionDesHistoriquesDeLaTache(tacheEntity.getIdTache());
                    tacheEntity.setUtilisateur_Tache(null);
                    tacheRepository.suppressionLienAvecTacheSuivante(tacheEntity.getIdTache());
                    tacheRepository.suppressionLienAvecTachePrecedente(tacheEntity.getIdTache());
                    tacheRepository.delete(tacheEntity);
                    System.out.println("putain");
                    return true;
                }else {
                    Optional<List<TacheEntity>> optionalTacheEntities = tacheRepository.getAllTacheEnfant(idtache);
                    if (optionalTacheEntities.isPresent()) {
                        for (TacheEntity t : tacheEntitiesEnfant) {
                            historiqueTacheRepository.suppressionDesHistoriquesDeLaTache(t.getIdTache());
                            t.setUtilisateur_Tache(null);
                            tacheRepository.suppressionLienAvecTacheSuivante(t.getIdTache());
                            tacheRepository.suppressionLienAvecTachePrecedente(t.getIdTache());
                            tacheRepository.delete(t);
                        }
                        historiqueTacheRepository.suppressionDesHistoriquesDeLaTache(tacheEntity.getIdTache());
                        tacheEntity.setUtilisateur_Tache(null);
                        tacheRepository.suppressionLienAvecTacheSuivante(tacheEntity.getIdTache());
                        tacheRepository.suppressionLienAvecTachePrecedente(tacheEntity.getIdTache());
                        tacheRepository.delete(tacheEntity);
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }
}