package be.technobel.ucmppg.bl.dto.participations;

import be.technobel.ucmppg.bl.dto.RoleDTO;
import be.technobel.ucmppg.bl.dto.utilisateur.UtilisateurDTO;
import be.technobel.ucmppg.bl.dto.utilisateur.UtilisateurDetailsDTO;
import be.technobel.ucmppg.dal.entities.ParticipationEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MembreProjetDTO {

    private UtilisateurDetailsDTO utilisateur;
    private RoleDTO role;
    private boolean isActif;

    public MembreProjetDTO(ParticipationEntity participationEntity) {
        this.utilisateur=new UtilisateurDetailsDTO(participationEntity.getUtilisateurParticipant());
        this.role=new RoleDTO(participationEntity.getRoleDuParticipant());
        this.isActif = participationEntity.isActif();
    }
}
