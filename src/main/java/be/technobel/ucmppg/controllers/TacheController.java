package be.technobel.ucmppg.controllers;

import be.technobel.ucmppg.bl.dto.projet.taches.TacheCreationTacheDTO;
import be.technobel.ucmppg.bl.dto.projet.taches.TacheCreationTacheEnfantDTO;
import be.technobel.ucmppg.bl.service.tache.TacheAjouterService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tache")
@CrossOrigin
@Api(value = "Pour les Controllers avec les Taches")
public class TacheController {

    @Autowired
    private TacheAjouterService tacheAjouterService;

    @PostMapping("/{idWorkflow}/ajouterTacheParent")
    public ResponseEntity<Boolean> postTacheParent(@PathVariable("idWorkflow")Long idWorkflow, TacheCreationTacheDTO tacheCreationTacheDTO){

        return ResponseEntity.ok(tacheAjouterService.creationTacheParent(idWorkflow, tacheCreationTacheDTO));
    }
    @PostMapping("/{idWorkflow}/ajouterTacheEnfant")
    public ResponseEntity<Boolean> postTacheEnfant(@PathVariable("idWorkflow")Long idWorkflow, TacheCreationTacheEnfantDTO tacheCreationTacheEnfantDTO){

        return ResponseEntity.ok(tacheAjouterService.creatioTacheEnfant(idWorkflow,tacheCreationTacheEnfantDTO));
    }
}
