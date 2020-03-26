package be.technobel.ucmppg.BL.Models;

import be.technobel.ucmppg.DAL.Models.ConstrainteAffectationEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EtapeWorkflowBusiness {

    private String nom_workflow;
    private String description_workflow;
    private boolean estPrenable;
    private List<RoleBusiness> role_workflow;
    private ConstrainteAffectationEnum contrainte;
    private List<TacheBusiness> tache_workflow;
}
