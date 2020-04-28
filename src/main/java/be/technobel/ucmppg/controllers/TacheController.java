package be.technobel.ucmppg.controllers;

import be.technobel.ucmppg.Exception.ErrorServiceException;
import be.technobel.ucmppg.bl.dto.ErrorDTO;
import be.technobel.ucmppg.bl.dto.projet.ProjetDTO;
import be.technobel.ucmppg.bl.service.projet.AssignerTacheService;
import be.technobel.ucmppg.bl.service.projet.ValiderTacheService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "API pour les opérations sur une tache ")
@RestController
@CrossOrigin
@RequestMapping(value = "/tache")
public class TacheController {

    @Autowired
    ValiderTacheService validerTacheService;
    @Autowired
    AssignerTacheService assignerTacheService;

    @ApiOperation(value = "Appelé pour valider une tache")
    @PostMapping (value = "{idTache}/valider")
    public ResponseEntity<ProjetDTO> validerTache (@PathVariable long idTache, @RequestBody Long idUtilisateur) throws ErrorServiceException {
        //TODO TOKEN : remplacer le paramètre utilisateur avec l'identification par token

            return ResponseEntity.ok(this.validerTacheService.validerTache(idUtilisateur,idTache));
    }

    @ApiOperation(value = "Appelé pour assigner une tache à un utilisateur")
    @PostMapping (value = "{idTache}/assigner/{idUtilisateurAssignateur}")
    public ResponseEntity<ProjetDTO> assignerTache (@PathVariable("idTache") long idTache,@PathVariable("idUtilisateurAssignateur") long idUtilisateurAssignateur, @RequestBody Long idUtilisateurAssigne) throws ErrorServiceException {
        //TODO TOKEN : remplacer le paramètre utilisateur assignateur avec l'identification par token

        return ResponseEntity.ok(this.assignerTacheService.assignation(idUtilisateurAssigne,idUtilisateurAssignateur,idTache));
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
    public ErrorDTO serviceException (ErrorServiceException ex)
    {
        return new ErrorDTO(ex.getProperties(),ex.getErrorMessage());
    }
}
