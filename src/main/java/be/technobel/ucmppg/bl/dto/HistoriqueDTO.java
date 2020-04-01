package be.technobel.ucmppg.bl.dto;

import be.technobel.ucmppg.bl.dto.projet.taches.TacheDTO;
import be.technobel.ucmppg.bl.dto.projet.workflow.EtapeWorkflowDTO;
import be.technobel.ucmppg.bl.dto.utilisateur.UtilisateurDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HistoriqueDTO {

    private UtilisateurDTO utilisateur;
    private EtapeWorkflowDTO etapeWorflow;
    private TacheDTO tache;


}
