package be.technobel.ucmppg.bl.dto;

import be.technobel.ucmppg.dal.entities.UtilisateurEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTODetails {

    private Long id;
    private String email;
    private String password;
    private String nom;
    private String prenom;
    private String pseudo;
    private String telephone;
    private String infoSuppl;
    private String urlPhoto;

    public UserDTODetails(UtilisateurEntity utilisateurEntity) {
        this.id = utilisateurEntity.getIdUtilisateur();
        this.email = utilisateurEntity.getEmail();
        this.password = utilisateurEntity.getMotDePasse();
        this.nom = utilisateurEntity.getNomUtilisateur();
        this.prenom = utilisateurEntity.getPrenomUtilisateur();
        this.pseudo = utilisateurEntity.getPseudoUtilisateur();
        this.telephone = utilisateurEntity.getTelephoneUtilisateur();
        this.infoSuppl = utilisateurEntity.getInformationSupplementaire();
        this.urlPhoto = utilisateurEntity.getUrlPhotoUtilisateur();
    }
}
