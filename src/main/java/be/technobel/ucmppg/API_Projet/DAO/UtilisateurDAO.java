package be.technobel.ucmppg.API_Projet.DAO;

import be.technobel.ucmppg.BL.Models.ParticipationBusiness;
import be.technobel.ucmppg.BL.Models.UtilisateurBusiness;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UtilisateurDAO {

    private String email;
    private String password;
    private String nom;
    private String prenom;
    private String pseudo;
    private String telephone;
    private String infoSuppl;
    private String urlPhoto;
    private List<ParticipationDAO> participation;

    public UtilisateurDAO(UtilisateurBusiness user) {
        this.email = user.getEmail_Utilisateur();
        this.infoSuppl = user.getInformationSupplementaire_Utilisateur();
        this.nom = user.getNom_Utilisateur();
        this.password = user.getMotDePasse_Utilisateur();
        this.prenom = user.getPrenom_Utilisateur();
        this.pseudo = user.getPseudo_Utilisateur();
        this.telephone = user.getTelephone_utilisateur();
        this.urlPhoto = user.getUrlPhoto_Utilisateur();
        for (ParticipationBusiness p: user.getParticipation_projet()){
            this.participation.add(new ParticipationDAO(p));
        }
    }
}
