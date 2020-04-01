package be.technobel.ucmppg.bl.dto.utilisateur;

import be.technobel.ucmppg.dal.entities.UtilisateurEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurDetailsDTO {

    private String mail;
    private String nom;
    private String prenom;
    private String pseudo;
    private String telephone;
    private String infoSuppl;
    private String urlPhoto;

    public UtilisateurDetailsDTO(UtilisateurEntity utilisateurEntity) {
        this.mail = utilisateurEntity.getEmailUtilisateur();
        this.nom = utilisateurEntity.getNomUtilisateur();
        this.prenom = utilisateurEntity.getPrenomUtilisateur();
        this.pseudo = utilisateurEntity.getPseudoUtilisateur();
        this.telephone = utilisateurEntity.getTelephoneUtilisateur();
        this.infoSuppl = utilisateurEntity.getInformationSupplementaireUtilisateur();
        this.urlPhoto = utilisateurEntity.getUrlPhotoUtilisateur();
    }
}
