package be.technobel.ucmppg.bl.dto.projet.collaborateur;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class SupprimerCollaborateurDTO {
    private String mail;
    private long idProjet;
}
