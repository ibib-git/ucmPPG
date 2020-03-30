package be.technobel.ucmppg.bl.dto;

import be.technobel.ucmppg.bl.dto.projet.ProjetDTO;
import be.technobel.ucmppg.bl.dto.utilisateur.UtilisateurDTO;
import be.technobel.ucmppg.dal.entities.ParticipationEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ParticipationDTO {

    private UtilisateurDTO utilisateur;
    private ProjetDTO projet;
    private RoleDTO role;

    public ParticipationDTO(ParticipationEntity participationEntity) {

    }
}
