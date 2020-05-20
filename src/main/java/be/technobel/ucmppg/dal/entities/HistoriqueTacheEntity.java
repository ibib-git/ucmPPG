package be.technobel.ucmppg.dal.entities;

import be.technobel.ucmppg.bl.dto.HistoriqueDTO;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Historique_de_tache")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@ToString
public class HistoriqueTacheEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHistorique;

    @ManyToOne
    private TacheEntity tacheHistorique;

    @ManyToOne
    private EtapeWorkflowEntity etapeWorkflowTacheHistorique;

    @ManyToOne
    private UtilisateurEntity utilisateur_Tache_historique;

    public HistoriqueTacheEntity(TacheEntity tacheEntity, EtapeWorkflowEntity etapeWorkflowEntity, UtilisateurEntity utilisateurEntity){
        this.etapeWorkflowTacheHistorique = etapeWorkflowEntity;
        this.tacheHistorique = tacheEntity;
        this.utilisateur_Tache_historique = utilisateurEntity;
    }
}
