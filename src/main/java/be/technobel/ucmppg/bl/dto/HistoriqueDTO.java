package be.technobel.ucmppg.bl.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HistoriqueDTO {

    private UtilisateurDTO utilisateurTache;
    private EtapeWorkflowDTO etapeWorflowTache;
    private TacheDTO tacheHistorique;


}
