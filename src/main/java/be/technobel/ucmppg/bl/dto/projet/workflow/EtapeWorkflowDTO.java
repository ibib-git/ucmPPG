package be.technobel.ucmppg.bl.dto.projet.workflow;

import be.technobel.ucmppg.bl.dto.RoleDTO;
import be.technobel.ucmppg.bl.dto.projet.taches.TacheDTO;
import be.technobel.ucmppg.dal.entities.ConstrainteAffectationEnum;
import be.technobel.ucmppg.dal.entities.EtapeWorkflowEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EtapeWorkflowDTO {

    private String nomWorkflow;
    private String descriptionWorkflow;
    private boolean estPrenable;
    private List<RoleDTO> rolesAffectableWorkflow=new ArrayList<>();
    private ConstrainteAffectationEnum contrainte;
    private List<TacheDTO> tacheWorkflow=new ArrayList<>();

    public EtapeWorkflowDTO(EtapeWorkflowEntity etapeWorkflowEntity) {
        this.nomWorkflow=etapeWorkflowEntity.getNomEtapeWorkflow();
        this.descriptionWorkflow=etapeWorkflowEntity.getNomEtapeWorkflow();
        this.estPrenable=etapeWorkflowEntity.isEstPrenableEtapeWorkflow();
        etapeWorkflowEntity.getRolesAutorisation().forEach(
                roleProjetEntity -> {
                    this.rolesAffectableWorkflow.add(new RoleDTO(roleProjetEntity));
                }
        );

        this.contrainte=etapeWorkflowEntity.getConstrainteAffectation();
        etapeWorkflowEntity.getTaches().forEach(
                tacheEntity -> {
                    this.tacheWorkflow.add(new TacheDTO(tacheEntity));
                }
        );
    }
}
