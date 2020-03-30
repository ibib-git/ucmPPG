package be.technobel.ucmppg.bl.dto;

import be.technobel.ucmppg.dal.entities.ConstrainteAffectationEnum;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EtapeWorkflowDTO {

    private String nomWorkflow;
    private String descriptionWorkflow;
    private boolean estPrenable;
    private List<RoleDTO> roleWorkflow;
    private ConstrainteAffectationEnum contrainte;
    private List<TacheDTO> tacheWorkflow;


}
