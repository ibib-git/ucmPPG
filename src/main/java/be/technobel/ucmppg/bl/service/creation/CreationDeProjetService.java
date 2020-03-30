package be.technobel.ucmppg.BL.Service.Creation;

import be.technobel.ucmppg.dal.entities.ParticipationEntity;
import be.technobel.ucmppg.dal.repositories.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreationDeProjetService implements CreationDeProjetInterface {

    private CreationParDefautService creation;

    private UtilisateurRepository utilisateurRepository;

    // Creation de la liste de Participation pour le projet
    @Override
    public List<ParticipationEntity> creationDeListeParticipation(List<String> email) {

        // methode pour recuperer les participant grace à un email => relier par le mail d'un utilisateur
        return null;
    }

}
