package be.technobel.ucmppg.dal.entities;

import be.technobel.ucmppg.bl.dto.DroitDTO;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Droit_Autorisation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class DroitProjetEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDroit;

    @Column(name = "nom",nullable = false, unique = true)
    private String nomDroit;

    public DroitProjetEntity(DroitDTO droitDTO){
        this.nomDroit = droitDTO.getNom();
    }
}
