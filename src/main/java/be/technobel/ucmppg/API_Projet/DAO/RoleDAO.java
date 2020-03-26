package be.technobel.ucmppg.API_Projet.DAO;

import be.technobel.ucmppg.BL.Models.DroitBusiness;
import be.technobel.ucmppg.BL.Models.RoleBusiness;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleDAO {

    private String nom;
    private List<DroitDAO> droits_dao_Role;

    public RoleDAO(RoleBusiness role) {
        this.nom = role.getNom_Role_Projet();
        for (DroitBusiness d: role.getDroits_Du_Role()) {
            droits_dao_Role.add(new DroitDAO(d));
        }
    }
}
