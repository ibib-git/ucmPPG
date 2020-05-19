package be.technobel.ucmppg.bl.dto.utilisateur;

import be.technobel.ucmppg.dal.entities.UtilisateurEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurAuthentificationDTO {

    private Long id;
    private String mail;
    private String nom;
    private String prenom;
    private String pseudo;
    private String telephone;
    private String infoSuppl;
    private String token;

    public UtilisateurAuthentificationDTO(UtilisateurEntity utilisateurEntity) {
        this.id = utilisateurEntity.getIdUtilisateur();
        this.mail = utilisateurEntity.getEmailUtilisateur();
        this.nom = utilisateurEntity.getNomUtilisateur();
        this.prenom = utilisateurEntity.getPrenomUtilisateur();
        this.pseudo = utilisateurEntity.getPseudoUtilisateur();
        this.telephone = utilisateurEntity.getTelephoneUtilisateur();
        this.infoSuppl = utilisateurEntity.getInformationSupplementaireUtilisateur();
        this.token = null;
    }

    public UtilisateurAuthentificationDTO(UtilisateurDetailsDTO utilisateurDetailDTO) {
        this.id = utilisateurDetailDTO.getId();
        this.mail = utilisateurDetailDTO.getMail();
        this.nom = utilisateurDetailDTO.getNom();
        this.prenom = utilisateurDetailDTO.getPrenom();
        this.pseudo = utilisateurDetailDTO.getPseudo();
        this.telephone = utilisateurDetailDTO.getTelephone();
        this.infoSuppl = utilisateurDetailDTO.getInfoSuppl();
        this.token = null;
    }
}
