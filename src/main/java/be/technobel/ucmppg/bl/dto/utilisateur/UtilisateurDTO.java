package be.technobel.ucmppg.bl.dto.utilisateur;

import be.technobel.ucmppg.bl.dto.ParticipationDTO;
import be.technobel.ucmppg.bl.dto.ParticipationDetailDTO;
import be.technobel.ucmppg.dal.entities.UtilisateurEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurDTO {

    private Long id;
    private String motDePasse;
    private String mail;
    private String nom;
    private String prenom;
    private String pseudo;
    private String telephone;
    private String infoSuppl;
    private String urlPhoto;
    private List<ParticipationDTO> participations = new ArrayList<>();

    public UtilisateurDTO(UtilisateurEntity utilisateurEntity) {
        this.id = utilisateurEntity.getIdUtilisateur();
        this.motDePasse = utilisateurEntity.getPassword();
        this.mail =utilisateurEntity.getEmailUtilisateur();
        this.nom=utilisateurEntity.getNomUtilisateur();
        this.prenom=utilisateurEntity.getPrenomUtilisateur();
        this.pseudo=utilisateurEntity.getPseudoUtilisateur();
        this.telephone=utilisateurEntity.getTelephoneUtilisateur();
        this.infoSuppl=utilisateurEntity.getInformationSupplementaireUtilisateur();
        this.urlPhoto=utilisateurEntity.getUrlPhotoUtilisateur();
        this.participations = utilisateurEntity.getProjetsParticiperUtilisateur().stream().map(
                ParticipationDTO::new
        ).collect(Collectors.toList());
    }
}
