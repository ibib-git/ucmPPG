package be.technobel.ucmppg.bl.service.projet;

import be.technobel.ucmppg.bl.dto.projet.ProjetDTO;
import be.technobel.ucmppg.dal.entities.ProjetEntity;
import be.technobel.ucmppg.dal.repositories.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecuperationProjetService {

    @Autowired
    private ProjetRepository projetRepository;

    public ProjetDTO getProjetById (Long idProjet)
    {
        Optional<ProjetEntity> projetEntity = projetRepository.findByIdProjet(idProjet);

        return projetEntity.map(ProjetDTO::new).orElse(null);

    }
}