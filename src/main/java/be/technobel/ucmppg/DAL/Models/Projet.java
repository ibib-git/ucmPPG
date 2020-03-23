package be.technobel.ucmppg.DAL.Models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Tableau_Projet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_Projet;

    @Column(name = "nom_du_Projet", nullable = false)
    private String nom_de_Projet;

    @Column(name = "Description_du_Projet")
    private String description_de_Projet;

    @Column(name = "UtilisateurCreateur", nullable = false)
    private String Utilisateur_createur;

    @ManyToMany
    private Set<Utilisateur> membres_Du_Projet;

}
