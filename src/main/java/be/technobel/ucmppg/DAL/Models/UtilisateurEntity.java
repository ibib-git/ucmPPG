package be.technobel.ucmppg.DAL.Models;

import be.technobel.ucmppg.dto.UserDTOLogin;
import be.technobel.ucmppg.dto.UserDTORegister;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Tableau_Utilisateur")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UtilisateurEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_utilisateur")
    private Long id_Utilisateur;

    @Column(name = "Email_Utilisateur", unique = true , nullable = false)
    private String email;

    @Column(name ="MotDePasse_Utilisateur", nullable = false)
    private String motDePasse;

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

    @Column(name="Information")
    private String information_supplementaire;

    @OneToMany
    private Set<ParticipationEntity> projets_participer;

    public UtilisateurEntity(UserDTORegister user) {
        this.email = user.getEmail();
        this.motDePasse= user.getPassword();
        this.pseudo_Utilisateur = user.getPseudo();
        this.nom_Utilisateur = user.getNom();
        this.prenom_Utilisateur = user.getPrenom();
        this.telephone_Utilisateur = user.getTelephone();
        this.information_supplementaire = user.getInfoSuppl();
        this.urlPhoto_Utilisateur = user.getUrlPhoto();
    }

    public UtilisateurEntity(UserDTOLogin user) {
        this.email = user.getEmail();
        this.motDePasse = user.getPassword();
    }
}
