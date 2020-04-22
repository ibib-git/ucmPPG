package be.technobel.ucmppg.dal.entities;

import be.technobel.ucmppg.bl.dto.HistoriqueDTO;
import be.technobel.ucmppg.bl.dto.projet.taches.TacheDTO;
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
    private Long idTache;

    @Column(name="Nom_de_Tache",nullable = false)
    private String nomTache;

    @Column(name="Description_de_la_Tache")
    private String descriptionTache;

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

}
