package be.technobel.ucmppg.DAL.Models;

import be.technobel.ucmppg.dto.UserDTOLogin;
import be.technobel.ucmppg.dto.UserDTORegister;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
    @Email(message = "Format non conforme pour un email valide")
    @NotNull(message = "un utilisateur doit posséder un email valide")
    private String email;

    @Column(name ="MotDePasse_Utilisateur", nullable = false)
    @Size(min = 8,message = "Un mot de passe doit être de minimum 8 caractères")
    @NotNull(message = "un utilisateur doit posséder un mot de passe valide")
    @Pattern(regexp = "^(?=.*[\\d])(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*])[\\w!@#$%^&*]{8,}$",
            message ="Format non conforme, un mot de passe valide doit contenir au min 1 majuscule, 1 minuscule, 1 chiffre et 1 caractère special (!@#$%^&*)" )
    private String motDePasse;

    @Column(name ="Pseudo_Utilisateur", unique = true, nullable = false)
    @NotNull(message = "un utilisateur doit posséder un pseudo")
    private String pseudo_Utilisateur;

    @Column(name="Nom_Utilisateur")
    @Size(min = 2,message = "un nom doit être de minimum 2 caractères")
    private String nom_Utilisateur;

    @Column(name="Prenom_Utilisateur")
    @Size(min = 2,message = "un prénom doit être de minimum 2 caractères")
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

}
