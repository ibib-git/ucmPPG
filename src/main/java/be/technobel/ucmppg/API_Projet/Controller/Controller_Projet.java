package be.technobel.ucmppg.API_Projet.Controller;

import be.technobel.ucmppg.API_Projet.DAO.Projet_DAO;
import be.technobel.ucmppg.API_Projet.Service.Creation.Service_Creation_Par_Defaut;
import be.technobel.ucmppg.API_Projet.Service.Creation.Service_De_Creation_De_Projet;
import be.technobel.ucmppg.BL.Models.Projet_BL;
import be.technobel.ucmppg.BL.Models.Utilisateur_BL;
import be.technobel.ucmppg.DAL.Models.ProjetEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/projet")
@CrossOrigin
public class Controller_Projet {

    private Service_De_Creation_De_Projet service_de_creation;
    private Service_Creation_Par_Defaut creation_par_defaut;

    /* méthode pour créer un projet et l'envoyer dans la DB */
    @PostMapping("/enregistrementProjet")
    public Projet_DAO postCreationProjet(@RequestBody Projet_DAO projet_DAO){
        // Crée un projet
        Projet_BL newProjet = new Projet_BL();
        // Initialise sont créateur
        newProjet.setUtilisateur_createur_projet(projet_DAO.getCreateur_Projet());
        // Donne la liste d'utilisateur avec le droit de venir sur le projet
        newProjet.setUtilisateurs_projet(service_de_creation.Creation_de_Liste_Participation(projet_DAO));
        // Ajoute les colonnes au projet
        newProjet.setColonne_Du_Projet(projet_DAO.getProjet_par_defaut().getColonne_Du_Projet());
        // initialise le nom du projet
        newProjet.setNom_projet(projet_DAO.getProjet_par_defaut().getNom_projet());
        // initialise la description du projet
        newProjet.setDescription_projet(projet_DAO.getProjet_par_defaut().getDescription_projet());

        //TODO Save The Projet



        return projet_DAO;
    }

    /* Méthode qui est envoyée en front pour créer un projet par défaut sans nom et description mais 4 colonne ( 3 logique et 1 de base )
    ainsi que 3 Role dedans */
    @GetMapping("/nouveauProjet")
    public Projet_DAO getProjetParDefaut() {
        // Projet DAO Creer
        Projet_DAO projet_dao_par_defaut = new Projet_DAO();

        // List de Membre L'admin y est mis par defaut
        List<String> email_par_defaut = new ArrayList<>();

        // Initialisation du Projet_DAO
        projet_dao_par_defaut.setCreateur_Projet(null);
        // l'email de celui-ci
        projet_dao_par_defaut.setEmail_utilisateur(email_par_defaut);
        // Le Projet par defaut
        projet_dao_par_defaut.setProjet_par_defaut(creation_par_defaut.creationParDefautDeProjet());

        return projet_dao_par_defaut;
    }


}
