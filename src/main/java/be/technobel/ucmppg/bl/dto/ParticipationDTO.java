package be.technobel.ucmppg.bl.dto;

import be.technobel.ucmppg.bl.dto.projet.ProjetDTO;
import be.technobel.ucmppg.bl.dto.utilisateur.UtilisateurDetailsDTO;
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

    private UtilisateurDetailsDTO utilisateur;
    private ProjetDTO projet;
    private RoleDTO role;
    private boolean actif;

    public ParticipationDTO(ParticipationEntity participationEntity) {
        this.utilisateur = new UtilisateurDetailsDTO(participationEntity.getUtilisateurParticipant());
        this.projet = new ProjetDTO(participationEntity.getProjetParticipation());
        this.role = new RoleDTO(participationEntity.getRoleDuParticipant());
        this.actif = participationEntity.isActif();
    }

}
