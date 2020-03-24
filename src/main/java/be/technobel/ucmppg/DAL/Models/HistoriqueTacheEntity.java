package be.technobel.ucmppg.DAL.Models;

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
    private Long id_historique;

    @OneToOne
    private TacheEntity tache_historique;

    @OneToOne
    private EtapeWorkflowEntity etapeWorkflow_Tache_historique;

    @OneToOne
    private UtilisateurEntity utilisateur_Tache_historique;

}
