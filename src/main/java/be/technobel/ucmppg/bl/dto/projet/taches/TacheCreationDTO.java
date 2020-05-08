package be.technobel.ucmppg.bl.dto.projet.taches;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TacheCreationTacheDTO {

    private String nom;
    private String description;
    private String priorite;
    private int estimation;
    private String unite;

}
