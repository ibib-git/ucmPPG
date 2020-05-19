package be.technobel.ucmppg.controllers;

import be.technobel.ucmppg.Exception.ErrorServiceException;
import be.technobel.ucmppg.bl.dto.ErrorDTO;
import be.technobel.ucmppg.bl.dto.projet.ProjetDTO;
import be.technobel.ucmppg.bl.dto.utilisateur.UtilisateurAuthentificationDTO;
import be.technobel.ucmppg.bl.dto.utilisateur.UtilisateurDetailsDTO;
import be.technobel.ucmppg.bl.service.projet.AssignerTacheService;
import be.technobel.ucmppg.bl.service.projet.ValiderTacheService;
import be.technobel.ucmppg.configuration.JwtTokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Api(value = "API pour les opérations sur une tache ")
@RestController
@CrossOrigin
@RequestMapping(value = "/tache")
public class TacheController {

    @Autowired
    ValiderTacheService validerTacheService;
    @Autowired
    AssignerTacheService assignerTacheService;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @ApiOperation(value = "Appelé pour valider une tache")
    @GetMapping (value = "{idTache}/valider")
    public ResponseEntity<ProjetDTO> validerTache (@PathVariable long idTache, @RequestHeader ("Authorization") String token) throws ErrorServiceException {
            return ResponseEntity.ok(this.validerTacheService.validerTache(jwtTokenProvider.getIdUtilisateur(token),idTache));
    }

    @ApiOperation(value = "Appelé pour assigner une tache à un utilisateur")
    @PostMapping (value = "{idTache}/assigner")
    public ResponseEntity<ProjetDTO> assignerTache (@PathVariable("idTache") long idTache,@RequestHeader ("Authorization") String token, @RequestBody Long idUtilisateurAssigne) throws ErrorServiceException {
        return ResponseEntity.ok(this.assignerTacheService.assignation(idUtilisateurAssigne,jwtTokenProvider.getIdUtilisateur(token),idTache));
    }

    @ApiOperation(value = "Appelé pour retirer une assignation d'utilisateur à une tache")
    @GetMapping (value = "{idTache}/congedier")
    public ResponseEntity<ProjetDTO> congedierUtilisateurTache (@PathVariable("idTache") long idTache, @RequestHeader ("Authorization") String token) throws ErrorServiceException {
        return ResponseEntity.ok(this.assignerTacheService.congedierUtilisateur(idTache,jwtTokenProvider.getIdUtilisateur(token)));
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
