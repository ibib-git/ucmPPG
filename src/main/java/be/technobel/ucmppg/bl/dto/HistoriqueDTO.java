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

    private UtilisateurDTO utilisateur;
    private EtapeWorkflowDTO etapeWorflow;
    private TacheDTO tache;

    public HistoriqueDTO(HistoriqueTacheEntity historiqueTacheEntity){

        if(historiqueTacheEntity.getUtilisateur_Tache_historique() != null){
            this.utilisateur = new UtilisateurDTO(historiqueTacheEntity.getUtilisateur_Tache_historique());
        }else{
            this.utilisateur = null;
        }
        this.etapeWorflow = new EtapeWorkflowDTO(historiqueTacheEntity.getEtapeWorkflowTacheHistorique());
        this.tache = new TacheDTO(historiqueTacheEntity.getTacheHistorique());

    }
}
