package be.technobel.ucmppg.API_Projet.DAO;

import be.technobel.ucmppg.BL.Models.HistoriqueTacheBusiness;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HistoriqueDAO {

    private UtilisateurDAO utilisateur_Tache;
    private EtapeWorkflowDAO etape_worflow_Tache;
    private TacheDAO tache_historique;

    public HistoriqueDAO(HistoriqueTacheBusiness historique) {
        this.utilisateur_Tache = new UtilisateurDAO(historique.getUtilisateur_Tache());
        this.etape_worflow_Tache = new EtapeWorkflowDAO(historique.getEtape_worflow_Tache());
        this.tache_historique = new TacheDAO(historique.getTache_historique());
    }
}
