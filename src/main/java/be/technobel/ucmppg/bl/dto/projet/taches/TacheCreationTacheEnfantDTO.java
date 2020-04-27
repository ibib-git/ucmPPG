package be.technobel.ucmppg.bl.dto.projet.taches;

import be.technobel.ucmppg.dal.entities.Priorite;
import be.technobel.ucmppg.dal.entities.UniteDeTempsEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TacheCreationTacheEnfantDTO {

    private String nom;
    private String description;
    private Priorite priorite;
    private int estimation;
    private UniteDeTempsEnum unite;
    private Long idTacheParent;
}
