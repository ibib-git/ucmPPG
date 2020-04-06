package be.technobel.ucmppg.bl.dto.projet.collaborateur;

import lombok.Data;

@Data
public class ChangerRoleCollaborateurDTO {
    private long idUtilisateur;
    private long idProjet;
    private String nomRole;
}
