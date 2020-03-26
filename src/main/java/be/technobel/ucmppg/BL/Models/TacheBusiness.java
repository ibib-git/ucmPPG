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
public class TacheBusiness {

    private String nom_Tache;
    private String description_workflow;
    private List<TacheBusiness> tache_enfants;
    private List<TacheBusiness> tache_Parents;
    private int estimation_Temps_Tache;
    private UniteDeTempsEnum uniteDeTempsEnum;
    private List<HistoriqueTacheBusiness> historique;
    private UtilisateurBusiness utilisateur_Tache;
}
