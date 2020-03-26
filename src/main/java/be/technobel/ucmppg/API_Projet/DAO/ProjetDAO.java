package be.technobel.ucmppg.API_Projet.DAO;

import be.technobel.ucmppg.BL.Models.EtapeWorkflowBusiness;
import be.technobel.ucmppg.BL.Models.ParticipationBusiness;
import be.technobel.ucmppg.BL.Models.ProjetBusiness;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProjetDAO {

    private String nom_projet;
    private String description_projet;
    private UtilisateurDAO utilisateur_createur_projet;
    private List<ParticipationDAO> utilisateurs_projet;
    private List<EtapeWorkflowDAO> colonne_Du_Projet;

    public ProjetDAO(ProjetBusiness projet_business) {
        this.nom_projet = projet_business.getNom_projet();
        this.description_projet = projet_business.getDescription_projet();
        this.utilisateur_createur_projet = new UtilisateurDAO(projet_business.getUtilisateur_createur_projet());
        for (ParticipationBusiness p: projet_business.getUtilisateurs_projet()) {
            this.utilisateurs_projet.add(new ParticipationDAO(p));
        }
        for (EtapeWorkflowBusiness e: projet_business.getColonne_Du_Projet()) {
            this.colonne_Du_Projet.add(new EtapeWorkflowDAO(e));
        }
    }
}
