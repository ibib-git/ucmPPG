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
@EqualsAndHashCode
@ToString
public class HistoriqueTacheEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHistorique;

    @OneToOne
    private TacheEntity tacheHistorique;

    @OneToOne
    private EtapeWorkflowEntity etapeWorkflowTacheHistorique;

    @OneToOne
    private UtilisateurEntity utilisateur_Tache_historique;

}
