package be.technobel.ucmppg.bl.dto;

import be.technobel.ucmppg.bl.dto.projet.ProjetDetailDTO;
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
public class ParticipationDetailDTO {
    private UtilisateurDetailsDTO utilisateur;
    private ProjetDetailDTO projet;
    private RoleDTO role;

    public ParticipationDetailDTO(ParticipationEntity participationEntity) {
        this.utilisateur = new UtilisateurDetailsDTO(participationEntity.getUtilisateurParticipant());
        this.projet = new ProjetDetailDTO(participationEntity.getProjetParticipation());
        this.role = new RoleDTO(participationEntity.getRoleDuParticipant());
    }
}
