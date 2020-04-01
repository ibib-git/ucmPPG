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

    private String nom;
    private String description;
    private boolean estPrenable;
    private List<RoleDTO> roleAffectables =new ArrayList<>();
    private ConstrainteAffectationEnum contrainte;
    private List<TacheDTO> taches =new ArrayList<>();

    public EtapeWorkflowDTO(EtapeWorkflowEntity etapeWorkflowEntity) {
        this.nom =etapeWorkflowEntity.getNomEtapeWorkflow();
        this.description =etapeWorkflowEntity.getNomEtapeWorkflow();
        this.estPrenable=etapeWorkflowEntity.isEstPrenableEtapeWorkflow();
        etapeWorkflowEntity.getRolesAutorisation().forEach(
                roleProjetEntity -> {
                    this.roleAffectables.add(new RoleDTO(roleProjetEntity));
                }
        );

        this.contrainte=etapeWorkflowEntity.getConstrainteAffectation();
        etapeWorkflowEntity.getTaches().forEach(
                tacheEntity -> {
                    this.taches.add(new TacheDTO(tacheEntity));
                }
        );
    }
}
