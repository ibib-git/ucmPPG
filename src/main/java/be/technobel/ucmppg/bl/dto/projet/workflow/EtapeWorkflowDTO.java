package be.technobel.ucmppg.bl.dto.projet.workflow;

import be.technobel.ucmppg.bl.dto.RoleDTO;
import be.technobel.ucmppg.bl.dto.projet.taches.TacheDTO;
import be.technobel.ucmppg.dal.entities.ConstrainteAffectationEnum;
import be.technobel.ucmppg.dal.entities.EtapeWorkflowEntity;
import be.technobel.ucmppg.dal.entities.RoleProjetEntity;
import be.technobel.ucmppg.dal.entities.TacheEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EtapeWorkflowDTO {
    private long id;
    private String nom;
    private String description;
    private boolean estPrenable;
    private Integer numOrdre;
    private List<RoleDTO> roleAffectables = new ArrayList<>();
    private ConstrainteAffectationEnum contrainte;
    private List<TacheDTO> taches = new ArrayList<>();

    public EtapeWorkflowDTO(EtapeWorkflowEntity etapeWorkflowEntity) {
        this.id = etapeWorkflowEntity.getIdEtapeWorkflow();
        this.nom = etapeWorkflowEntity.getNomEtapeWorkflow();
        this.description = etapeWorkflowEntity.getDescriptionEtapeWorkflow();
        this.numOrdre = etapeWorkflowEntity.getNumOrdreEtapeWorkflow();
        this.estPrenable = etapeWorkflowEntity.isEstPrenableEtapeWorkflow();
        this.contrainte = etapeWorkflowEntity.getConstrainteAffectation();
        etapeWorkflowEntity.getRolesAutorisation().forEach(roleProjetEntity -> this.roleAffectables.add(new RoleDTO(roleProjetEntity)));
        etapeWorkflowEntity.getTaches().forEach(tacheEntity -> this.taches.add(new TacheDTO(tacheEntity)));
    }
}
