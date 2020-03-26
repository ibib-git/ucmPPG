package be.technobel.ucmppg.API_Projet.DAO;

import be.technobel.ucmppg.BL.Models.ParticipationBusiness;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ParticipationDAO {

    private UtilisateurDAO utilisateur;
    private ProjetDAO projet;
    private RoleDAO role;

    public ParticipationDAO(ParticipationBusiness p) {
        this.utilisateur = new UtilisateurDAO(p.getUtilisateur());
        this.projet = new ProjetDAO(p.getProjet());
        this.role = new RoleDAO(p.getRole_projet());
    }
}
