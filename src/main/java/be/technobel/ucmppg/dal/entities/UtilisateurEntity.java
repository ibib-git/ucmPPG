package be.technobel.ucmppg.dal.entities;


import be.technobel.ucmppg.BL.dto.UtilisateurDTO;
import be.technobel.ucmppg.BL.dto.UserDTORegister;
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
    private Long idUtilisateur;

    @Column(name = "Email_Utilisateur", unique = true , nullable = false)
    private String email;

    @Column(name ="MotDePasse_Utilisateur", nullable = false)
    private String motDePasse;

    @Column(name ="Pseudo_Utilisateur", unique = true, nullable = false)
    private String pseudoUtilisateur;

    @Column(name="Nom_Utilisateur")
    private String nomUtilisateur;

    @Column(name="Prenom_Utilisateur")
    private String prenomUtilisateur;

    @Column(name = "Numéro_Utilisateur")
    private String telephoneUtilisateur;

    @Column(name="Localisaton_Photo")
    private String urlPhotoUtilisateur;

    @Column(name="Information")
    private String informationSupplementaire;

    @OneToMany
    private Set<ParticipationEntity> projetsParticiper;
  
    public UtilisateurEntity(UtilisateurDTO user) {
        this.email = user.getEmail();
        this.informationSupplementaire = user.getInfoSuppl();
        this.nomUtilisateur = user.getNom();
        this.prenomUtilisateur = user.getPrenom();
        this.motDePasse = user.getPassword();
        this.pseudoUtilisateur = user.getPseudo();
        this.urlPhotoUtilisateur = user.getUrlPhoto();
        this.telephoneUtilisateur = user.getTelephone();
    }
  
    public UtilisateurEntity(UserDTORegister user) {
        this.email = user.getEmail();
        this.motDePasse= user.getPassword();
        this.pseudoUtilisateur = user.getPseudo();
        this.nomUtilisateur = user.getNom();
        this.prenomUtilisateur = user.getPrenom();
        this.telephoneUtilisateur = user.getTelephone();
        this.informationSupplementaire = user.getInfoSuppl();
        this.urlPhotoUtilisateur = user.getUrlPhoto();
    }

   /* public UtilisateurEntity(UserDTOLogin user) {
        this.email = user.getEmail();
        this.motDePasse = user.getPassword();
    }*/
}
