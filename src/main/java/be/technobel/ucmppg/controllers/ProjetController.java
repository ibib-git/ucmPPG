package be.technobel.ucmppg.controllers;

import be.technobel.ucmppg.bl.dto.projet.ProjetDTO;
import be.technobel.ucmppg.bl.dto.projet.ProjetCreationDTO;
import be.technobel.ucmppg.bl.dto.projet.collaborateur.AjoutCollaborateurDTO;
import be.technobel.ucmppg.bl.service.creation.CreationDeProjetService;
import be.technobel.ucmppg.bl.service.projet.AjouterCollaborateurAuProjetService;
import be.technobel.ucmppg.dal.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    private CreationDeProjetService creationDeProjetService;
    @Autowired
    private AjouterCollaborateurAuProjetService ajouterCollaborateurAuProjetService;
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

    @GetMapping("/{id}")
    public ProjetDTO getProjetParId(@PathVariable("id") long id){
        //todo : grosse ligne bien dégueu mais tant que ca plante pas its ok
        return new ProjetDTO(projetRepository.findById(id).get());
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


}
