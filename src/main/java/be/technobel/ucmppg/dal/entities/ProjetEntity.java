package be.technobel.ucmppg.dal.entities;

import be.technobel.ucmppg.bl.dto.projet.workflow.EtapeWorkflowDTO;
import be.technobel.ucmppg.bl.dto.ParticipationDTO;
import be.technobel.ucmppg.bl.dto.projet.ProjetDTO;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Tableau_Projet")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProjetEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long idProjet;

    @Column(name = "nom_du_Projet", nullable = false)
    private String nomDeProjet;

    @Column(name = "Description_du_Projet")
    private String descriptionDeProjet;

    @ManyToOne
    private UtilisateurEntity utilisateurCreateur;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<ParticipationEntity> membresDuProjet =new HashSet<>();

    @OneToMany (fetch = FetchType.EAGER)
    private Set<EtapeWorkflowEntity> etapeWorkflows=new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER)
    private Set<RoleProjetEntity> rolesProjet=new HashSet<>();

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
