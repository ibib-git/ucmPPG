package be.technobel.ucmppg.controllers;

import be.technobel.ucmppg.Exception.ErrorServiceException;
import be.technobel.ucmppg.bl.dto.projet.ProjetCreationDTO;
import be.technobel.ucmppg.bl.dto.projet.ProjetDTO;
import be.technobel.ucmppg.bl.dto.projet.collaborateur.ProjetAjoutCollaborateurDTO;
import be.technobel.ucmppg.bl.dto.projet.collaborateur.SupprimerCollaborateurDTO;
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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(value = "API pour les opérations CRUD sur un/les projets ")
@RestController
@RequestMapping("/projet")
@CrossOrigin
public class ProjetController {


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

    @ApiOperation(value = "Appelé pour récupérer un projet bien précis")
    @GetMapping("/{id}")
    public ResponseEntity<ProjetDTO> getProjetParId(@PathVariable("id") long id) throws ErrorServiceException {	    public ResponseEntity<ProjetDTO> getProjetParId(@PathVariable("id") long id) throws ErrorServiceException {
        return ( ResponseEntity.ok(recuperationProjetService.getProjetById(id)) );
        
    @PostMapping()
    public ResponseEntity<ProjetDTO> creerProjet(@RequestBody ProjetCreationDTO projetCreationDTO){

        ResponseEntity<ProjetDTO> projet=null;

        projet= ResponseEntity.ok(creationDeProjetService.execute(projetCreationDTO.getNom(),
                projetCreationDTO.getDescription(),
                projetCreationDTO.getIdUtilisateur()));
        //TODO BASTIEN : wtf is that
        return projet;
    }

    @PostMapping("/ajoutCollaborateur")
    public ResponseEntity<Boolean> ajouterCollaborateurProjet(@RequestBody ProjetAjoutCollaborateurDTO projetAjoutCollaborateurDTO){


        return ResponseEntity.ok(
                ajouterCollaborateurAuProjetService.execute(
                        projetAjoutCollaborateurDTO.getIdProjet(),
                        projetAjoutCollaborateurDTO.getEmailUtilisateur()
                )
        );
    }

    @PostMapping("/supprimerCollaborateur")
    public ResponseEntity<Boolean> supprimerCollaborateurProjet(@RequestBody SupprimerCollaborateurDTO supprimerCollaborateurDTO){
        return ResponseEntity.ok(supprimerCollaborateurDuProjetService.execute(supprimerCollaborateurDTO));
    }


}
