package be.technobel.ucmppg.DAL.Models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Tableau_Projet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProjetEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProjet;

    @Column(name = "nom_du_Projet", nullable = false)
    private String nomDeProjet;

    @Column(name = "Description_du_Projet")
    private String descriptionDeProjet;

    @OneToOne
    private UtilisateurEntity utilisateurCreateur;

    @OneToMany
    private Set<ParticipationEntity> membresDuProjet =new HashSet<>();

    @OneToMany
    private Set<EtapeWorkflowEntity> etapeWorkflows=new HashSet<>();

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
