package be.technobel.ucmppg.API_Projet.Service.Creation;

import be.technobel.ucmppg.DAL.Models.ParticipationEntity;

import java.util.List;

public interface Service_De_Creation_De_Projet_Interface {

    // transforme une liste d'utilisateur en Participant
    List<ParticipationEntity> Creation_de_Liste_Participation(List<String> emails);

}
