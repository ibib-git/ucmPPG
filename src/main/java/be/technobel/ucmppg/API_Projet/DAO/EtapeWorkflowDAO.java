package be.technobel.ucmppg.API_Projet.DAO;

import be.technobel.ucmppg.BL.Models.EtapeWorkflowBusiness;
import be.technobel.ucmppg.BL.Models.RoleBusiness;
import be.technobel.ucmppg.BL.Models.TacheBusiness;
import be.technobel.ucmppg.DAL.Models.ConstrainteAffectationEnum;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EtapeWorkflowDAO {

    private String nom_workflow;
    private String description_workflow;
    private boolean estPrenable;
    private List<RoleDAO> role_workflow;
    private ConstrainteAffectationEnum contrainte;
    private List<TacheDAO> tache_workflow;

    public EtapeWorkflowDAO(EtapeWorkflowBusiness etape_workflowBusiness) {
        this.nom_workflow = etape_workflowBusiness.getNom_workflow();
        this.description_workflow = etape_workflowBusiness.getDescription_workflow();
        this.estPrenable = etape_workflowBusiness.isEstPrenable();
        this.contrainte = etape_workflowBusiness.getContrainte();
        for (RoleBusiness r : etape_workflowBusiness.getRole_workflow()){
            this.role_workflow.add(new RoleDAO(r));
        }
        for (TacheBusiness t: etape_workflowBusiness.getTache_workflow()) {
            this.tache_workflow.add(new TacheDAO(t));
        }
    }
}
