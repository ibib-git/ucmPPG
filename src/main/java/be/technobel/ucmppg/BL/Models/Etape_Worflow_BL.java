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
public class Etape_Worflow_BL {

    private String nom_workflow;
    private String description_workflow;
    private boolean estPrenable;
    private List<Role_Projet_BL> role_workflow;
    private ConstrainteAffectationEnum contrainte;
    private List<Tache_BL> tache_workflow;

}
