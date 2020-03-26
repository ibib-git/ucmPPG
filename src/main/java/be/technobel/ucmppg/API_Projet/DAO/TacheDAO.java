package be.technobel.ucmppg.API_Projet.DAO;

import be.technobel.ucmppg.BL.Models.HistoriqueTacheBusiness;
import be.technobel.ucmppg.BL.Models.TacheBusiness;
import be.technobel.ucmppg.DAL.Models.UniteDeTempsEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TacheDAO {
    private String nom_Tache;
    private String description_workflow;
    private List<TacheDAO> tache_enfants;
    private List<TacheDAO> tache_Parents;
    private int estimation_Temps_Tache; //TODO Faut faire attention Tache int
    private UniteDeTempsEnum uniteDeTempsEnum;
    private List<HistoriqueDAO> historique;
    private UtilisateurDAO utilisateur_Tache;

    public TacheDAO(TacheBusiness tache) {
        this.nom_Tache = tache.getNom_Tache();
        this.description_workflow = tache.getDescription_workflow();
        for (TacheBusiness a:tache.getTache_enfants()) {
            this.tache_enfants.add(new TacheDAO(a));
        }
        for (TacheBusiness a:tache.getTache_Parents()) {
            this.tache_Parents.add(new TacheDAO(a));
        }
        this.estimation_Temps_Tache = tache.getEstimation_Temps_Tache();
        this.uniteDeTempsEnum = tache.getUniteDeTempsEnum();
        this.utilisateur_Tache = new UtilisateurDAO(tache.getUtilisateur_Tache());
        for (HistoriqueTacheBusiness h: tache.getHistorique()) {
            this.historique.add(new HistoriqueDAO(h));
        }
    }
}
