package be.technobel.ucmppg.bl.service.tache;

import be.technobel.ucmppg.bl.dto.projet.taches.TacheDTO;
import be.technobel.ucmppg.dal.entities.TacheEntity;
import be.technobel.ucmppg.dal.repositories.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TacheRecuperationService {

    @Autowired
    private TacheRepository tacheRepository;


    public TacheDTO execute(Long idTache){

        Optional<TacheEntity> optionalTacheEntity = tacheRepository.findById(idTache);

        if(optionalTacheEntity.isPresent()){
            TacheEntity tacheEntity = optionalTacheEntity.get();
            return new TacheDTO(tacheEntity);
        }
        // TODO: Placer une errreur sur le controleur
        return null;
    }

}
