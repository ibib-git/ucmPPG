package be.technobel.ucmppg.bl.dto;

import be.technobel.ucmppg.dal.entities.DroitProjetEntity;
import be.technobel.ucmppg.dal.entities.RoleProjetEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleDTO {

    private String nom;
    private List<DroitDTO> droits=new ArrayList<>();

    public RoleDTO(RoleProjetEntity roleProjetEntity) {
        this.nom=roleProjetEntity.getNomDeRole();
        this.droits = roleProjetEntity.getDroitProjets().stream().map(DroitDTO::new).collect(Collectors.toList());
    }
}
