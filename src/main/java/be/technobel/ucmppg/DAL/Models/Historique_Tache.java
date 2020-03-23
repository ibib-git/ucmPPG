package be.technobel.ucmppg.DAL.Models;

import lombok.*;

import javax.persistence.*;

@Entity
//@Table(name = "Historique_de_tache")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Historique_Tache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_historique;

    @OneToOne
    private Tache tache_historique;

    @OneToOne
    private Etape_workflow Etape_workflow_Tache_historique;

    @OneToOne
    private Utilisateur utilisateur_Tache_historique;

}
