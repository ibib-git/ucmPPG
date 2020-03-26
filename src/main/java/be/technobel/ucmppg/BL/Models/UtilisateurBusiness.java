package be.technobel.ucmppg.BL.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurBusiness {

    private String pseudo_Utilisateur;
    private String nom_Utilisateur;
    private String prenom_Utilisateur;
    private String telephone_utilisateur;
    private String urlPhoto_Utilisateur;
    private String motDePasse_Utilisateur;
    private String email_Utilisateur;
    private String informationSupplementaire_Utilisateur;
    private List<ParticipationBusiness> participation_projet;
}
