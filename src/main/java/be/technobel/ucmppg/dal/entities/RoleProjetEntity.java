package be.technobel.ucmppg.dal.entities;

import be.technobel.ucmppg.BL.dto.DroitDTO;
import be.technobel.ucmppg.BL.dto.RoleDTO;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Role_dans_Projet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RoleProjetEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRole;

    @Column(name = "nom_de_Role",nullable = false)
    private String nomDeRole;

    @ManyToMany
    @JoinTable(name="droit_role",joinColumns =@JoinColumn(name="role_id"),inverseJoinColumns = @JoinColumn(name="droit_id"))
    private Set<DroitProjetEntity> droitProjets=new HashSet<>();

    public RoleProjetEntity(RoleDTO role_DAO) {
        this.nomDeRole = role_DAO.getNom();
        for (DroitDTO d: role_DAO.getDroitsDtoRole()) {
            droitProjets.add(new DroitProjetEntity(d));
        }
    }
}
