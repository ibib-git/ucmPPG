package be.technobel.ucmppg.BL.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Projet_BL {

    private String nom_projet;
    private String description_projet;
    private Utilisateur_BL utilisateur_createur_projet;
    private List<Participation_BL> utilisateurs_projet;
    private List<Etape_Worflow_BL> colonne_Du_Projet;

}
