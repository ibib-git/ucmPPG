package be.technobel.ucmppg.bl.dto;

import be.technobel.ucmppg.dal.entities.DroitProjetEntity;
import be.technobel.ucmppg.dal.entities.RoleProjetEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleDTO {


    private long id;
    private String nom;
    private List<String> droits=new ArrayList<>();

    public RoleDTO(RoleProjetEntity roleProjetEntity) {
        this.id=roleProjetEntity.getIdRole();
        this.nom=roleProjetEntity.getNomDeRole();
        System.out.println(roleProjetEntity.getDroitProjets());

        Set<DroitProjetEntity> droits=roleProjetEntity.getDroitProjets();
        droits.stream().forEach(
                droitProjetEntity -> {
                    System.out.println(droitProjetEntity);
                    this.droits.add(
                            droitProjetEntity.getNomDroit()
                    );
                }
        );
//        DroitProjetEntity[] droits=(DroitProjetEntity[])roleProjetEntity.getDroitProjets().toArray();
//        for(int i=0;i<droits.length;i++){
//            this.droits.add(droits[i].getNomDroit());
//        }
//        this.droits = roleProjetEntity.getDroitProjets().stream()
//                .map(
//                        DroitDTO::new
//                ).collect(Collectors.toList());
    }
}
