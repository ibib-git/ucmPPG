package be.technobel.ucmppg.bl.service.utilisateur;
import be.technobel.ucmppg.bl.dto.utilisateur.UtilisateurDetailsDTO;
import be.technobel.ucmppg.bl.dto.utilisateur.UtilisateurEnregistrementDTO;
import be.technobel.ucmppg.configuration.HashConfig;
import be.technobel.ucmppg.dal.entities.UtilisateurEntity;
import be.technobel.ucmppg.dal.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreationUtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    HashConfig hashConfig;

    public Boolean enregistrementUtilisateur (UtilisateurEnregistrementDTO utilisateurEnregistrementDTO)
    {
        utilisateurEnregistrementDTO.setMotDePasse (hashConfig.getPasswordEncoder().encode(utilisateurEnregistrementDTO.getMotDePasse()));
        utilisateurRepository.save(new UtilisateurEntity(utilisateurEnregistrementDTO));
        return true;
    }

}
