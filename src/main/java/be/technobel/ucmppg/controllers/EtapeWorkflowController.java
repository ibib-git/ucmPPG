package be.technobel.ucmppg.controllers;

import be.technobel.ucmppg.Exception.ErrorServiceException;
import be.technobel.ucmppg.bl.dto.ErrorDTO;
import be.technobel.ucmppg.bl.dto.projet.ProjetDTO;
import be.technobel.ucmppg.bl.dto.projet.workflow.OrdreEtapeDTO;
import be.technobel.ucmppg.bl.service.projet.ChangerOrdreEtapeService;
import be.technobel.ucmppg.bl.service.projet.ValiderTacheService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "API pour les opérations CRUD sur les étapes du workflow")
@RestController
@CrossOrigin
@RequestMapping("/etape")
public class EtapeWorkflowController {

    @Autowired
    ChangerOrdreEtapeService changerOrdreEtapeService;

    @ApiOperation(value = "Appelé pour changer l'ordre des étapes du workflow")
    @PatchMapping("{idEtape}/ordre")
    public ResponseEntity<ProjetDTO> changerOrdreEtape (@PathVariable long idEtape, @RequestBody OrdreEtapeDTO ordreEtapeDTO) throws ErrorServiceException {
        return ResponseEntity.ok(this.changerOrdreEtapeService.execute(ordreEtapeDTO.getIdUtilisateur(),idEtape,ordreEtapeDTO.getNvOrdre()));
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
