package be.technobel.ucmppg.BL.Models;

import be.technobel.ucmppg.DAL.Models.UniteDeTempsEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tache_BL {

    private String nom_Tache;
    private String description_workflow;
    private List<Tache_BL> tache_enfants;
    private List<Tache_BL> tache_Parents;
    private int estimation_Temps_Tache;
    private UniteDeTempsEnum uniteDeTempsEnum;
    private List<Historique_Tache_BL> historique;
    private Utilisateur_BL utilisateur_Tache;

}
