package be.technobel.ucmppg.controllers;

import be.technobel.ucmppg.Exception.ErrorServiceException;
import be.technobel.ucmppg.bl.dto.ErrorDTO;
import be.technobel.ucmppg.bl.dto.projet.ProjetDTO;
import be.technobel.ucmppg.bl.dto.projet.taches.TacheCreationTacheDTO;
import be.technobel.ucmppg.bl.dto.projet.taches.TacheCreationTacheEnfantDTO;
import be.technobel.ucmppg.bl.service.projet.ValiderTacheService;
import be.technobel.ucmppg.bl.service.tache.TacheAjouterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tache")
@CrossOrigin
@Api(value = "Pour les Controllers avec les Taches")
public class TacheController {

    @Autowired
    private TacheAjouterService tacheAjouterService;
    @Autowired
    private ValiderTacheService validerTacheService;

    @PostMapping("/{idWorkflow}/ajouterTacheParent")
    public ResponseEntity<Boolean> postTacheParent(@PathVariable("idWorkflow")Long idWorkflow, TacheCreationTacheDTO tacheCreationTacheDTO){

        return ResponseEntity.ok(tacheAjouterService.creationTacheParent(idWorkflow, tacheCreationTacheDTO));
    }
    @PostMapping("/{idWorkflow}/ajouterTacheEnfant")
    public ResponseEntity<Boolean> postTacheEnfant(@PathVariable("idWorkflow")Long idWorkflow, TacheCreationTacheEnfantDTO tacheCreationTacheEnfantDTO) {

        return ResponseEntity.ok(tacheAjouterService.creatioTacheEnfant(idWorkflow, tacheCreationTacheEnfantDTO));
    }



    @ApiOperation(value = "Appelé pour valider une tache")
    @PostMapping (value = "{idTache}/valider")
    public ResponseEntity<ProjetDTO> validerTache (@PathVariable long idTache, @RequestBody Long idUtilisateur) throws ErrorServiceException {
        //TODO TOKEN : remplacer le paramètre utilisateur avec l'identification par token

            return ResponseEntity.ok(this.validerTacheService.validerTache(idUtilisateur,idTache));
    }

    /**
     * @author Damien Fricot
     *
     * Description : Methode qui permet de catcher une exception, de créer un ErrorDTO avec les paramètre de cette dernière.
     * On va renvoyer ce ErrorDTO avec une requete HTTP code=406. Va nous permettre de gérer les cas d'erreurs de manière plus spécifiques.
     *
     * @exception ErrorServiceException Cette exception est levée dans le cas d'une erreur prévue dans le service appelé.
     * @param ex : ErrorServiceException
     * @return new ErrorDTO(String: nomDuChamps,String: messageErreur) && Code HTTP de la requete = 406
     */
    @ExceptionHandler(ErrorServiceException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ErrorDTO serviceException (ErrorServiceException ex) {
        return new ErrorDTO(ex.getProperties(),ex.getErrorMessage());
    }
}
