package be.technobel.ucmppg.API_Projet.Service.Creation;

import be.technobel.ucmppg.API_Projet.DAO.Projet_DAO;
import be.technobel.ucmppg.BL.Models.Participation_BL;
import be.technobel.ucmppg.BL.Models.Utilisateur_BL;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class Service_De_Creation_De_Projet implements Service_De_Creation_De_Projet_Interface {

    private Service_Creation_Par_Defaut creation;

    // Creation de la liste de Participation pour le projet
    @Override
    public List<Participation_BL> Creation_de_Liste_Participation(Projet_DAO p) {
        p.getProjet_par_defaut().getUtilisateurs_projet().add(new Participation_BL(creation.administrateur_projet(),p.getCreateur_Projet(),p.getProjet_par_defaut()));
        List<Utilisateur_BL> utilisateurs = liste_utilisateur_par_Email_liste(p.getEmail_utilisateur());
        for (Utilisateur_BL u: utilisateurs) {
            if(!u.getEmail_Utilisateur().equals(p.getCreateur_Projet().getEmail_Utilisateur())){
                p.getProjet_par_defaut().getUtilisateurs_projet().add(new Participation_BL(creation.membre_projet(),u,p.getProjet_par_defaut()));
            }
        }
        return p.getProjet_par_defaut().getUtilisateurs_projet();
    }

    // BESOIN DU REPERTORY POUR RECUP LISTE D'UTILISATEUR
    @Override
    public List<Utilisateur_BL> liste_utilisateur_par_Email_liste(List<String> email_liste) {
        return null;
    }
}
