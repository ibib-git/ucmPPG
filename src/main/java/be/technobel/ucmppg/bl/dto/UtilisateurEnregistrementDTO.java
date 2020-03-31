package be.technobel.ucmppg.bl.dto;

import be.technobel.ucmppg.dal.entities.UtilisateurEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurEnregistrementDTO {

    private String email;
    private String password;
    private String nom;
    private String prenom;
    private String pseudo;
    private String telephone;
    private String infoSuppl;
    private String urlPhoto;

    public UtilisateurEnregistrementDTO(UtilisateurEntity utilisateurEntity) {
        this.email=utilisateurEntity.getEmailUtilisateur();
        this.password=utilisateurEntity.getMotDePasseUtilisateur();
        this.nom=utilisateurEntity.getNomUtilisateur();
        this.prenom=utilisateurEntity.getPrenomUtilisateur();
        this.pseudo=utilisateurEntity.getPseudoUtilisateur();
        this.telephone=utilisateurEntity.getTelephoneUtilisateur();
        this.infoSuppl=utilisateurEntity.getInformationSupplementaireUtilisateur();
        this.urlPhoto=utilisateurEntity.getUrlPhotoUtilisateur();
    }
}
