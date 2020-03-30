package be.technobel.ucmppg.controllers;

import be.technobel.ucmppg.bl.dto.projet.ProjetDTO;
import be.technobel.ucmppg.bl.dto.projet.ProjetCreationDTO;
import be.technobel.ucmppg.bl.service.creation.CreationParDefautService;
import be.technobel.ucmppg.bl.service.creation.CreationDeProjetService;
import be.technobel.ucmppg.dal.entities.ProjetEntity;
import be.technobel.ucmppg.dal.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/projet")
@CrossOrigin
public class ProjetController {

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
    private CreationDeProjetService service_de_creation;
    @Autowired
    private CreationParDefautService creationParDefautService;

    /* méthode pour créer un projet et l'envoyer dans la DB */
    @PostMapping("/enregistrementProjet")
    public ResponseEntity<ProjetEntity> postCreationProjet(@RequestBody ProjetCreationDTO projet_DTO){

        ProjetEntity projetFinal = new ProjetEntity(new ProjetDTO());
        /*
        List<ParticipationEntity> participant = Service_Creation_De_Projet.Creation_de_Liste_Participation(projet_dao.getEmail_utilisateur());
        projetFinal.setMembresDuProjet(participant);
        projetFinal.membresDuProjet.add(new ParticipationEntity(new RoleProjetEntity(new Role_DTO(creation_par_defaut.administrateur_projet())),projetFinal.getUtilisateurCreateur,projetFinal));
         */
        return ResponseEntity.ok(projetRepository.save(projetFinal));
    }



    @PostMapping("/{idProjet}/collaborateur/{idCollaborateur}")
    public String ajouterCollaborateurProjet(
            @PathVariable("idProjet") String idProjet,
            @PathVariable("idCollaborateur") String idCollaborateur){
        return "id projet : "+idProjet+"  id collaborateur : "+idCollaborateur;
    }


}
