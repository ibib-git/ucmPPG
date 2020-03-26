package be.technobel.ucmppg.DAL.Models;

import be.technobel.ucmppg.API_Projet.DAO.HistoriqueDAO;
import be.technobel.ucmppg.API_Projet.DAO.TacheDAO;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Tableau_de_Tache")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class TacheEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Tache;

    @Column(name="Nom_de_Tache",nullable = false)
    private String nom_Tache;

    @Column(name="Description_de_la_Tache")
    private String description_Tache;

    @OneToMany
    private Set<TacheEntity> tachesEnfants=new HashSet<>();

    @ManyToMany
    @JoinTable(name="taches_precedentes",joinColumns = @JoinColumn(name="tache_suivante"),inverseJoinColumns = @JoinColumn(name="tache_precedente"))
    private Set<TacheEntity> tachesPrecedentes=new HashSet<>();

    @Column(name = "Estimation_de_temps")
    private int estimationDeTemps_Tache;

    @Column(name = "Unite_de_temps")
    private UniteDeTempsEnum uniteDeTemps_tache;

    @OneToMany
    private Set<HistoriqueTacheEntity> historiqueTaches;

    @OneToOne
    private UtilisateurEntity utilisateur_Tache;

    public TacheEntity(TacheDAO tache) {
        this.description_Tache = tache.getDescription_workflow();
        this.nom_Tache = tache.getNom_Tache();
        this.estimationDeTemps_Tache = tache.getEstimation_Temps_Tache();
        this.uniteDeTemps_tache = tache.getUniteDeTempsEnum();
        this.utilisateur_Tache = new UtilisateurEntity(tache.getUtilisateur_Tache());
        for (TacheDAO t: tache.getTache_enfants()) {
            this.tachesEnfants.add(new TacheEntity(t));
        }
        for (TacheDAO t: tache.getTache_Parents()) {
            this.tachesPrecedentes.add(new TacheEntity(t));
        }
        for (HistoriqueDAO h: tache.getHistorique()) {
            this.historiqueTaches.add(new HistoriqueTacheEntity(h));
        }
    }
}
