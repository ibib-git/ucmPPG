package be.technobel.ucmppg.DAL.Models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Colonne_du_workflow")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Etape_workflow implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_Etape_workflow;

    @Column(name="nom_de_la_Colonne",nullable = false)
    private String nom_Etape_workflow;

    @Column(name="Description_de_la_Colonne")
    private String description_Etape_workflow;

    @Column(name="Prenable",nullable = false)
    private boolean estPrenable_Etape_workflow;

    @OneToMany
    private Set<Role_Projet> roles_autorisation;

    @Column(name="Constrainte")
    private ConstrainteDAffectation constrainteDAffectation;

}
