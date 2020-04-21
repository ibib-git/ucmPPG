package be.technobel.ucmppg.bl.dto.projet.collaborateur;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SupprimerCollaborateurDTO {
    private String mail;
    private long idProjet;
}
