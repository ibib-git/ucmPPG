package be.technobel.ucmppg.DAL.Models;

import be.technobel.ucmppg.API_Projet.DAO.DroitDAO;
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
    private Long id_Droit;

    @Column(name = "nom",nullable = false, unique = true)
    private String nom_Droit;

    public DroitProjetEntity(DroitDAO droit_dao) {
        this.nom_Droit = droit_dao.getNom();
    }
}
