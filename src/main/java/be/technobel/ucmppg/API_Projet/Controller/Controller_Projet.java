package be.technobel.ucmppg.API_Projet.Controller;

import be.technobel.ucmppg.API_Projet.DAO.Projet_DAO;
import be.technobel.ucmppg.API_Projet.Service.Creation.Service_Creation_Par_Defaut;
import be.technobel.ucmppg.API_Projet.Service.Creation.Service_De_Creation_De_Projet;
import be.technobel.ucmppg.BL.Models.Projet_BL;
import be.technobel.ucmppg.BL.Models.Utilisateur_BL;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/projet")
@CrossOrigin
public class Controller_Projet {

    private Service_De_Creation_De_Projet service_de_creation;
    private Service_Creation_Par_Defaut creation_par_defaut;

    @PostMapping("/enregistrementProjet")
    public Projet_DAO postCreationProjet(@RequestBody Projet_DAO projet_DAO){

        Projet_BL newProjet = new Projet_BL();
        newProjet.setUtilisateur_createur_projet(projet_DAO.getCreateur_Projet());
        newProjet.setUtilisateurs_projet(service_de_creation.Creation_de_Liste_Participation(projet_DAO));
        newProjet.setColonne_Du_Projet(projet_DAO.getProjet_par_defaut().getColonne_Du_Projet());
        newProjet.setNom_projet(projet_DAO.getProjet_par_defaut().getNom_projet());
        newProjet.setDescription_projet(projet_DAO.getProjet_par_defaut().getDescription_projet());

        //TODO Save The Projet

        return projet_DAO;
    }

    /* Méthode qui est envoyée en front pour créer un projet par défaut sans nom et description mais 4 colonne ( 3 logique et 1 de base )
    ainsi que 3 Role dedans */
    @GetMapping("/nouveauProjet")
    public Projet_DAO getProjetParDefaut(Utilisateur_BL u) {
        // Projet DAO Creer
        Projet_DAO projet_dao_par_defaut = new Projet_DAO();

        // List de Membre L'admin y est mis par defaut
        List<String> email_par_defaut = new ArrayList<>();
        email_par_defaut.add(u.getEmail_Utilisateur());

        // Initialisation du Projet_DAO
        // Le createur
        projet_dao_par_defaut.setCreateur_Projet(u);
        // l'email de celui-ci
        projet_dao_par_defaut.setEmail_utilisateur(email_par_defaut);
        // Le Projet par defaut
        projet_dao_par_defaut.setProjet_par_defaut(creation_par_defaut.creationParDefautDeProjet(u));

        return projet_dao_par_defaut;
    }


}
