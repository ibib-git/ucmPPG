package be.technobel.ucmppg.bl.dto;

import be.technobel.ucmppg.bl.dto.projet.taches.TacheDTO;
import be.technobel.ucmppg.bl.dto.projet.workflow.EtapeWorkflowDTO;
import be.technobel.ucmppg.bl.dto.utilisateur.UtilisateurDTO;
import be.technobel.ucmppg.dal.entities.HistoriqueTacheEntity;
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

    public HistoriqueDTO(HistoriqueTacheEntity historiqueTacheEntity){
        this.utilisateurTache = new UtilisateurDTO(historiqueTacheEntity.getUtilisateur_Tache_historique());
        this.etapeWorflowTache = new EtapeWorkflowDTO(historiqueTacheEntity.getEtapeWorkflowTacheHistorique());
        this.tacheHistorique = new TacheDTO(historiqueTacheEntity.getTacheHistorique());
    }
}
