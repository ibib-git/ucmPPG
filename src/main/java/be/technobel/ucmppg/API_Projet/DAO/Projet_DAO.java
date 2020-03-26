package be.technobel.ucmppg.API_Projet.DAO;

import be.technobel.ucmppg.BL.Models.Etape_Worflow_BL;
import be.technobel.ucmppg.BL.Models.Projet_BL;
import be.technobel.ucmppg.BL.Models.Utilisateur_BL;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Projet_DAO {

    private Projet_BL projet_par_defaut;
    private List<String> email_utilisateur;
    private Utilisateur_BL createur_Projet;

}
