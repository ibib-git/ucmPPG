package be.technobel.ucmppg.DAL.Models;

import be.technobel.ucmppg.API_Projet.DAO.HistoriqueDAO;
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

    public  HistoriqueTacheEntity(HistoriqueDAO historique_DAO){
        this.etapeWorkflow_Tache_historique = new EtapeWorkflowEntity(historique_DAO.getEtape_worflow_Tache());
        this.tache_historique = new TacheEntity(historique_DAO.getTache_historique());
        this.utilisateur_Tache_historique = new UtilisateurEntity(historique_DAO.getUtilisateur_Tache());
    }
}
