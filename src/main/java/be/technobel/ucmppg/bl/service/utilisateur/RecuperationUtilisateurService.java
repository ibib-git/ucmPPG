package be.technobel.ucmppg.bl.service.utilisateur;

import be.technobel.ucmppg.Exception.ErrorServiceException;
import be.technobel.ucmppg.bl.dto.utilisateur.UtilisateurDTO;
import be.technobel.ucmppg.bl.dto.utilisateur.UtilisateurDetailsDTO;
import be.technobel.ucmppg.dal.entities.UtilisateurEntity;
import be.technobel.ucmppg.dal.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class RecuperationUtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public UtilisateurDetailsDTO connexionUtilisateur (String mail, String motDePasse)
    {
        Optional<UtilisateurEntity> utilisateurEntity = utilisateurRepository.findByEmailUtilisateurAndMotDePasseUtilisateur(mail,motDePasse);

        return utilisateurEntity.map(UtilisateurDetailsDTO::new).orElse(null);
    }

    public UtilisateurDTO recupererUtilisateur(long id) throws ErrorServiceException {

        Optional<UtilisateurEntity> optionalUtilisateurEntity = utilisateurRepository.findById(id);

        if (optionalUtilisateurEntity.isPresent())
        {

            return new UtilisateurDTO(optionalUtilisateurEntity.get());
        } else throw new ErrorServiceException("Utilisateur","Erreur de chargement de l utilisateur");

    }
}
