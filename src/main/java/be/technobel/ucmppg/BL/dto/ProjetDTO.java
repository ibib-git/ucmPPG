package be.technobel.ucmppg.BL.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProjetDTO {

    private String nomProjet;
    private String descriptionProjet;
    private UtilisateurDTO utilisateurCreateurProjet;
    private List<ParticipationDTO> utilisateursProjet;
    private List<EtapeWorkflowDTO> colonneDuProjet;


}
