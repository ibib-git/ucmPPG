package be.technobel.ucmppg.bl.dto;

import be.technobel.ucmppg.bl.dto.projet.ProjetDTO;
import be.technobel.ucmppg.bl.dto.utilisateur.UtilisateurDTO;
import be.technobel.ucmppg.dal.entities.ParticipationEntity;
import be.technobel.ucmppg.dal.entities.ProjetEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.servlet.http.Part;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ParticipationDTO {

    private UtilisateurDTO utilisateur;
    private ProjetDTO projet;
    private RoleDTO role;

    public ParticipationDTO(ParticipationEntity participationEntity) {
        this.utilisateur = new UtilisateurDTO(participationEntity.getUtilisateurParticipant());
        this.projet = new ProjetDTO(participationEntity.getProjetParticipation());
        this.role = new RoleDTO(participationEntity.getRoleDuParticipant());
    }
}
