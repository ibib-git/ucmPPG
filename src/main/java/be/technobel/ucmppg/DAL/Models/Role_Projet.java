package be.technobel.ucmppg.DAL.Models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="Role_dans_Projet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Role_Projet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_Role;

    @Column(name = "nom_de_Role",nullable = false)
    private String nom_de_Role;

    @OneToMany
    private Set<Droit_Projet> droitProjets;
}
