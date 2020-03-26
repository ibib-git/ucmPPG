package be.technobel.ucmppg.BL.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HistoriqueTacheBusiness {

    private UtilisateurBusiness utilisateur_Tache;
    private EtapeWorkflowBusiness etape_worflow_Tache;
    private TacheBusiness tache_historique;
}
