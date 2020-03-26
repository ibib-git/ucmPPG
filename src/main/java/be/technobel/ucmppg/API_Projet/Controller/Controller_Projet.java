package be.technobel.ucmppg.API_Projet.Controller;

import be.technobel.ucmppg.API_Projet.DTO.ProjetDTO;
import be.technobel.ucmppg.API_Projet.DAO.ProjetDAO;
import be.technobel.ucmppg.API_Projet.Service.Creation.Service_Creation_Par_Defaut;
import be.technobel.ucmppg.API_Projet.Service.Creation.Service_De_Creation_De_Projet;
import be.technobel.ucmppg.DAL.Models.ProjetEntity;
import be.technobel.ucmppg.DAL.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/projet")
@CrossOrigin
public class Controller_Projet {

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private DroitProjetRepository droitProjetRepository;
    @Autowired
    private RoleProjetRepository roleProjetRepository;
    @Autowired
    private ParticipationRepository participationRepository;
    @Autowired
    private EtapeWorkflowRepository etapeWorkflowRepository;
    @Autowired
    private ProjetRepository projetRepository;
    @Autowired
    private Service_De_Creation_De_Projet service_de_creation;
    @Autowired
    private Service_Creation_Par_Defaut creation_par_defaut;

    /* méthode pour créer un projet et l'envoyer dans la DB */
    @PostMapping("/enregistrementProjet")
    public ResponseEntity<ProjetEntity> postCreationProjet(@RequestBody ProjetDTO projet_DTO){

        ProjetEntity projetFinal = new ProjetEntity(new ProjetDAO(projet_DTO.getProjet_par_defaut()));
        /*
        List<ParticipationEntity> participant = Service_Creation_De_Projet.Creation_de_Liste_Participation(projet_dao.getEmail_utilisateur());
        projetFinal.setMembresDuProjet(participant);
        projetFinal.membresDuProjet.add(new ParticipationEntity(new RoleProjetEntity(new Role_DTO(creation_par_defaut.administrateur_projet())),projetFinal.getUtilisateurCreateur,projetFinal));
         */
        return ResponseEntity.ok(projetRepository.save(projetFinal));
    }

    /* Méthode qui est envoyée en front pour créer un projet par défaut sans nom et description mais 4 colonne ( 3 logique et 1 de base )
    ainsi que 3 Role dedans */
    @GetMapping("/nouveauProjet")
    public ProjetDTO getProjetParDefaut() {
        // Projet DAO Creer
        ProjetDTO projet_DTO_par_defaut = new ProjetDTO();

        // List de Membre L'admin y est mis par defaut
        List<String> email_par_defaut = new ArrayList<>();

        // Initialisation du Projet_DAO
        projet_DTO_par_defaut.setCreateur_Projet(null);
        // l'email de celui-ci
        projet_DTO_par_defaut.setEmail_utilisateur(email_par_defaut);
        // Le Projet par defaut
        projet_DTO_par_defaut.setProjet_par_defaut(creation_par_defaut.creationParDefautDeProjet());

        return projet_DTO_par_defaut;
    }


}
