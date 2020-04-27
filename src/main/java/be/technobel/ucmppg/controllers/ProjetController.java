package be.technobel.ucmppg.controllers;

import be.technobel.ucmppg.bl.dto.projet.ProjetDTO;
import be.technobel.ucmppg.bl.dto.projet.ProjetCreationDTO;
import be.technobel.ucmppg.bl.dto.projet.collaborateur.AjoutCollaborateurDTO;
import be.technobel.ucmppg.bl.dto.projet.collaborateur.SupprimerCollaborateurDTO;
import be.technobel.ucmppg.bl.dto.utilisateur.UtilisateurDetailsDTO;
import be.technobel.ucmppg.bl.service.creation.CreationDeProjetService;
import be.technobel.ucmppg.bl.service.projet.RecuperationProjetService;
import be.technobel.ucmppg.bl.service.projet.AjouterCollaborateurAuProjetService;
import be.technobel.ucmppg.bl.service.projet.SupprimerCollaborateurDuProjetService;
import be.technobel.ucmppg.dal.entities.ProjetEntity;
import be.technobel.ucmppg.dal.repositories.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(value = "API pour les opérations CRUD sur un/les projets ")
@RestController
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
    private RecuperationProjetService recuperationProjetService;
    @Autowired
    private CreationDeProjetService creationDeProjetService;
    @Autowired
    private AjouterCollaborateurAuProjetService ajouterCollaborateurAuProjetService;
    @Autowired
    private SupprimerCollaborateurDuProjetService supprimerCollaborateurDuProjetService;
//    @Autowired
//    private CreationParDefautService creationParDefautService;


    //todo : supprimer lorsque cette méthode n'est plus nécessaire
    /**
     * GET ALL - a supprimer, pour le test uniquement
     */
    @GetMapping("")
    public List<ProjetDTO> getTousLesProjets(){
        List<ProjetDTO> projetDTOS=new ArrayList<>();
        projetRepository.findAll().forEach(
                projetEntity -> {
                    projetDTOS.add(new ProjetDTO(projetEntity));
                }
        );
        return projetDTOS;
    }
    @ApiOperation(value = "Appelé pour récupérer un projet bien précis")
    @GetMapping("/{id}")
    public ResponseEntity<ProjetDTO> getProjetParId(@PathVariable("id") long id){

        ProjetDTO projetDTO = recuperationProjetService.getProjetById(id);

        return (projetDTO != null ? ResponseEntity.ok(projetDTO) : new ResponseEntity("Pas de projet existant", HttpStatus.NOT_FOUND) );
    }

    @PostMapping()
    public ResponseEntity<ProjetDTO> creerProjet(@RequestBody ProjetCreationDTO projetCreationDTO){

        ResponseEntity<ProjetDTO> projet=null;

        projet= ResponseEntity.ok(creationDeProjetService.execute(projetCreationDTO.getNom(),
                projetCreationDTO.getDescription(),
                projetCreationDTO.getIdUtilisateur()));

        return projet;
    }

    @PostMapping("/ajoutCollaborateur")
    public ResponseEntity<Boolean> ajouterCollaborateurProjet(@RequestBody AjoutCollaborateurDTO ajoutCollaborateurDTO){


        return ResponseEntity.ok(
                ajouterCollaborateurAuProjetService.execute(
                        ajoutCollaborateurDTO.getIdProjet(),
                        ajoutCollaborateurDTO.getEmailUtilisateur()
                )
        );
    }

    @PostMapping("/supprimerCollaborateur")
    public ResponseEntity<Boolean> supprimerCollaborateurProjet(@RequestBody SupprimerCollaborateurDTO supprimerCollaborateurDTO){
        System.out.println("Qu'est-ce qu'on dit ");
        return ResponseEntity.ok(supprimerCollaborateurDuProjetService.execute(supprimerCollaborateurDTO));
    }


}
