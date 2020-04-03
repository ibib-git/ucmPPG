package be.technobel.ucmppg.dal.entities;



import be.technobel.ucmppg.bl.dto.utilisateur.UtilisateurDTO;
import be.technobel.ucmppg.bl.dto.utilisateur.UtilisateurDetailsDTO;
import be.technobel.ucmppg.bl.dto.utilisateur.UtilisateurEnregistrementDTO;

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Tableau_Utilisateur")
@Getter
@Setter
@AllArgsConstructor
@ToString
public class UtilisateurEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_utilisateur",unique = true)
    private Long idUtilisateur;

    @Column(name = "Email_Utilisateur", unique = true , nullable = false)
    @Email(message = "Format non conforme pour un email valide")
    @NotNull(message = "un utilisateur doit posséder un email valide")
    private String emailUtilisateur;

    @Column(name ="Mot_De_Passe_Utilisateur", nullable = false)
    @Size(min = 8,message = "Un mot de passe doit être de minimum 8 caractères")
    @NotNull(message = "un utilisateur doit posséder un mot de passe valide")
    @Pattern(regexp = "^(?=.*[\\d])(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*])[\\w!@#$%^&*]{8,}$",
            message ="Format non conforme, un mot de passe valide doit contenir au min 1 majuscule, 1 minuscule, 1 chiffre et 1 caractère special (!@#$%^&*)" )
    private String motDePasseUtilisateur;

    @Column(name ="Pseudo_Utilisateur",unique = true, nullable = false)
    @NotNull(message = "un utilisateur doit posséder un pseudo")
    private String pseudoUtilisateur;

    @Column(name="Nom_Utilisateur")
    @Size(min = 2,message = "un nom doit être de minimum 2 caractères")
    private String nomUtilisateur;

    @Column(name="Prenom_Utilisateur")
    @Size(min = 2,message = "un prénom doit être de minimum 2 caractères")
    private String prenomUtilisateur;

    @Column(name = "Numéro_Utilisateur")
    private String telephoneUtilisateur;

    @Column(name="Localisaton_Photo")
    private String urlPhotoUtilisateur;

    @Column(name="Information")
    private String informationSupplementaireUtilisateur;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<ParticipationEntity> projetsParticiperUtilisateur;

    public UtilisateurEntity() {
        this.projetsParticiperUtilisateur=new HashSet<>();
    }

    public UtilisateurEntity(UtilisateurDetailsDTO utilisateur) {
        this.emailUtilisateur = utilisateur.getMail();
        this.informationSupplementaireUtilisateur = utilisateur.getInfoSuppl();
        this.nomUtilisateur = utilisateur.getNom();
        this.prenomUtilisateur = utilisateur.getPrenom();
        this.pseudoUtilisateur = utilisateur.getPseudo();
        this.urlPhotoUtilisateur = utilisateur.getUrlPhoto();
        this.telephoneUtilisateur = utilisateur.getTelephone();
        this.projetsParticiperUtilisateur = new HashSet<>();
    }
  
    public UtilisateurEntity(UtilisateurEnregistrementDTO utilisateur) {
        this.emailUtilisateur = utilisateur.getMail();
        this.motDePasseUtilisateur = utilisateur.getMotDePasse();
        this.pseudoUtilisateur = utilisateur.getPseudo();
        this.nomUtilisateur = utilisateur.getNom();
        this.prenomUtilisateur = utilisateur.getPrenom();
        this.telephoneUtilisateur = utilisateur.getTelephone();
        this.informationSupplementaireUtilisateur = utilisateur.getInfoSuppl();
        this.urlPhotoUtilisateur = utilisateur.getUrlPhoto();
        this.projetsParticiperUtilisateur = new HashSet<>();
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof UtilisateurEntity))return false;
        return ((UtilisateurEntity)o).getIdUtilisateur().equals(this.getIdUtilisateur());
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUtilisateur);
    }
}
