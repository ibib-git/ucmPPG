package be.technobel.ucmppg.dal.entities;

import be.technobel.ucmppg.BL.dto.EtapeWorkflowDTO;
import be.technobel.ucmppg.BL.dto.RoleDTO;
import be.technobel.ucmppg.BL.dto.TacheDTO;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Colonne_du_workflow")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class EtapeWorkflowEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEtapeWorkflow;

    @Column(name="nom_de_la_Colonne",nullable = false)
    private String nomEtapeWorkflow;

    @Column(name="Description_de_la_Colonne")
    private String descriptionEtapeWorkflow;

    @Column(name="Prenable",nullable = false)
    private boolean estPrenableEtapeWorkflow;

    @ManyToMany
    @JoinTable(name="autorisation_colonne",joinColumns =@JoinColumn(name="colonne_id"),inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<RoleProjetEntity> rolesAutorisation=new HashSet<>();

    @Column(name="Constrainte")
    private ConstrainteAffectationEnum constrainteAffectation;

    @OneToMany
    private Set<TacheEntity> taches=new HashSet<>();

    public EtapeWorkflowEntity(EtapeWorkflowDTO etape) {
        this.nomEtapeWorkflow = etape.getNomWorkflow();
        this.descriptionEtapeWorkflow = etape.getDescriptionWorkflow();
        this.estPrenableEtapeWorkflow = etape.isEstPrenable();
        this.constrainteAffectation = etape.getContrainte();
        for (TacheDTO t: etape.getTacheWorkflow()) {
            this.taches.add(new TacheEntity(t));
        }
        for (RoleDTO r:etape.getRoleWorkflow()) {
            this.rolesAutorisation.add(new RoleProjetEntity(r));
        }
    }
}
