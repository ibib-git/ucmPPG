package be.technobel.ucmppg.bl.dto;

import be.technobel.ucmppg.dal.entities.UniteDeTempsEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TacheDTO {
    private String nomTache;
    private String descriptionWorkflow;
    private List<TacheDTO> tacheEnfants;
    private List<TacheDTO> tacheParents;
    private Integer estimationTempsTache; //TODO Faut faire attention Tache int
    private UniteDeTempsEnum uniteDeTempsEnum;
    private List<HistoriqueDTO> historique;
    private UtilisateurDTO utilisateurTache;

}
