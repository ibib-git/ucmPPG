package be.technobel.ucmppg.BL.Service.Creation;

import be.technobel.ucmppg.dal.entities.ParticipationEntity;

import java.util.List;

public interface CreationDeProjetInterface {

    // transforme une liste d'utilisateur en Participant
    List<ParticipationEntity> creationDeListeParticipation(List<String> emails);

}
