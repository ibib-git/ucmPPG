package be.technobel.ucmppg.BL.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleBusiness {

    private String nom_Role_Projet;
    private List<DroitBusiness> droits_Du_Role;
}
