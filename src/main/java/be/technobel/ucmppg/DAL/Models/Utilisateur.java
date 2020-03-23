package be.technobel.ucmppg.DAL.Models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
//@Table(name = "Tableau_Utilisateur")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_utilisateur")
    private long id_Utilisateur;

    @Column(name = "Mail_Utilisateur", unique = true , nullable = false)
    private String email_Utilisateur;

    @Column(name ="MDP_Utilisateur", nullable = false)
    private String motDePasse_Utilisateur;

    @Column(name ="Pseudo_Utilisateur", unique = true, nullable = false)
    private String pseudo_Utilisateur;

    @Column(name="Nom_Utilisateur")
    private String nom_Utilisateur;

    @Column(name="Prenom_Utilisateur")
    private String prenom_Utilisateur;

    @Column(name = "Numéro_Utilisateur")
    private String telephone_Utilisateur;

    @Column(name="Localisaton_Photo")
    private String urlPhoto_Utilisateur;

    @ManyToMany
    private Set<Projet> projets_participer;

}
