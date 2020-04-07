package be.technobel.ucmppg.bl.dto;

import be.technobel.ucmppg.dal.entities.DroitProjetEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DroitDTO {

    private String nom;


    public DroitDTO(DroitProjetEntity droitProjetEntity) {
        this.nom = droitProjetEntity.getNomDroit();
    }
}
