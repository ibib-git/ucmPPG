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
public class Role_Projet_BL {

    private String nom_Role_Projet;
    private List<Droit_Projet_BL> droits_Du_Role;


}
