package be.technobel.ucmppg.bl.dto.projet.collaborateur;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProjetAjoutCollaborateurDTO {
    private String emailUtilisateur;
    private long idProjet;
}
