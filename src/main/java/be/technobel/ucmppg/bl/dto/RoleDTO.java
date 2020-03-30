package be.technobel.ucmppg.bl.dto;

import be.technobel.ucmppg.dal.entities.RoleProjetEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleDTO {

    private String nom;
    private List<String> droits=new ArrayList<>();

    public RoleDTO(RoleProjetEntity roleProjetEntity) {
        this.nom=roleProjetEntity.getNomDeRole();
        roleProjetEntity.getDroitProjets().forEach(
                droitProjetEntity -> {
                    this.droits.add(droitProjetEntity.getNomDroit());
                }
        );
    }
}
