package be.technobel.ucmppg.dto;

import be.technobel.ucmppg.DAL.Models.UtilisateurEntity;
import be.technobel.ucmppg.DAL.repositories.UtilisateurRepository;
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
        this.id = utilisateurEntity.getId_Utilisateur();
        this.email = utilisateurEntity.getEmail();
        this.password = utilisateurEntity.getMotDePasse();
        this.nom = utilisateurEntity.getNom_Utilisateur();
        this.prenom = utilisateurEntity.getPrenom_Utilisateur();
        this.pseudo = utilisateurEntity.getPseudo_Utilisateur();
        this.telephone = utilisateurEntity.getTelephone_Utilisateur();
        this.infoSuppl = utilisateurEntity.getInformation_supplementaire();
        this.urlPhoto = utilisateurEntity.getUrlPhoto_Utilisateur();
    }
}
