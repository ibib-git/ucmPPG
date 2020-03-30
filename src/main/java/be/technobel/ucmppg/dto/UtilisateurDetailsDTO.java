package be.technobel.ucmppg.dto;

import be.technobel.ucmppg.DAL.Models.UtilisateurEntityFromPast;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurDetailsDTO {

    private Long id;
    private String email;
    private String password;
    private String nom;
    private String prenom;
    private String pseudo;
    private String telephone;
    private String infoSuppl;
    private String urlPhoto;

    public UtilisateurDetailsDTO(UtilisateurEntityFromPast utilisateurEntityFromPast) {
        this.id = utilisateurEntityFromPast.getId_Utilisateur();
        this.email = utilisateurEntityFromPast.getEmail();
        this.password = utilisateurEntityFromPast.getMotDePasse();
        this.nom = utilisateurEntityFromPast.getNom_Utilisateur();
        this.prenom = utilisateurEntityFromPast.getPrenom_Utilisateur();
        this.pseudo = utilisateurEntityFromPast.getPseudo_Utilisateur();
        this.telephone = utilisateurEntityFromPast.getTelephone_Utilisateur();
        this.infoSuppl = utilisateurEntityFromPast.getInformation_supplementaire();
        this.urlPhoto = utilisateurEntityFromPast.getUrlPhoto_Utilisateur();
    }
}
