package be.technobel.ucmppg.DAL.Models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="Tableau_de_Tache")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Tache implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_Tache;

    @Column(name="Nom_de_Tache",nullable = false)
    private String nom_de_Tache;

    @Column(name="Description_de_la_Tache")
    private String description_Tache;

    @Column(name="Tache_Parent")
    private long id_Tache_Parent;

    private String taches_precedentes;

    @Column(name = "Estimation_de_temps")
    private int estimation_de_temps_Tache;

    @Column(name = "Unite_de_temps")
    private UniteDeTemps uniteDeTemps_tache;

    @OneToMany
    private Set<Historique_Tache> historiqueTaches;

    @OneToOne
    private Utilisateur utilisateur_Tache;

}
