package be.technobel.ucmppg.bl.dto.projet;

import be.technobel.ucmppg.bl.dto.EtapeWorkflowDTO;
import be.technobel.ucmppg.bl.dto.ParticipationDTO;
import be.technobel.ucmppg.bl.dto.UtilisateurDTO;
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
