package be.technobel.ucmppg.API_Projet.Service.Creation;

import be.technobel.ucmppg.DAL.Models.ParticipationEntity;
import be.technobel.ucmppg.DAL.repositories.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Service_De_Creation_De_Projet implements Service_De_Creation_De_Projet_Interface {

    private Service_Creation_Par_Defaut creation;

    private UtilisateurRepository utilisateurRepository;

    // Creation de la liste de Participation pour le projet
    @Override
    public List<ParticipationEntity> Creation_de_Liste_Participation(List<String> email) {

        // methode pour recuperer les participant grace à un email => relier par le mail d'un utilisateur
        return null;
    }

}
