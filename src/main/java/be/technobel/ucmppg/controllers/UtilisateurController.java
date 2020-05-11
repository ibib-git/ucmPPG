package be.technobel.ucmppg.controllers;

import be.technobel.ucmppg.Exception.ErrorServiceException;
import be.technobel.ucmppg.bl.dto.ErrorDTO;
import be.technobel.ucmppg.bl.dto.ParticipationDetailDTO;
import be.technobel.ucmppg.bl.dto.utilisateur.UtilisateurConnexionDTO;
import be.technobel.ucmppg.bl.dto.utilisateur.UtilisateurDTO;
import be.technobel.ucmppg.bl.dto.utilisateur.UtilisateurDetailsDTO;
import be.technobel.ucmppg.bl.dto.utilisateur.UtilisateurEnregistrementDTO;
import be.technobel.ucmppg.bl.service.utilisateur.CreationUtilisateurService;
import be.technobel.ucmppg.bl.service.utilisateur.RecuperationUtilisateurService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Api(value = "API pour les opérations CRUD sur les utilisateurs")
@RestController
@CrossOrigin
@RequestMapping("/utilisateur")
public class UtilisateurController {

    @Autowired
    private RecuperationUtilisateurService recuperationUtilisateurService;
    @Autowired
    private CreationUtilisateurService creationUtilisateurService;


    @ApiOperation(value = "Appelé pour l'enregistrement d'un nouvel utilisateur" )
    @PostMapping("/enregistrement")
    public ResponseEntity<UtilisateurDetailsDTO> enregistrementUtilisateur(@RequestBody UtilisateurEnregistrementDTO utilisateurEnregistrementDTO)

    {
        UtilisateurDetailsDTO utilisateurDetailsDTO = creationUtilisateurService.enregistrementUtilisateur(utilisateurEnregistrementDTO);
        return ResponseEntity.ok(utilisateurDetailsDTO);
    }

    /**
     * @author Damien Fricot
     *
     * Description : Methode qui permet de catcher une exception, de créer un ErrorDTO avec les paramètre de cette dernière.
     * On va renvoyer ce ErrorDTO avec une requete HTTP code=406. Va nous permettre de gérer les cas d'erreurs de manière plus spécifiques.
     *
     * @exception ConstraintViolationException Cette exception est levée dans le cas ou les contraintes (javax.validation) imposées aux entity ne sont pas respectées.
     * @param ex : ConstraintViolationException
     * @return errors: liste de ErrorDTO(String: nomDuChamps,String: messageErreur) && Code HTTP de la requete = 406
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public List<ErrorDTO> validationException (ConstraintViolationException ex)
    {
        List<ErrorDTO> errors = ex.getConstraintViolations().stream()
                .map(e -> new ErrorDTO(e.getPropertyPath().toString(),e.getMessageTemplate()))
                .collect(Collectors.toList());
        return errors;
    }

    /**
     *  @author Damien Fricot
     *
     * Description : Methode qui permet de catcher une exception, de créer un ErrorDTO avec les paramètre de cette dernière.
     * On va renvoyer ce ErrorDTO avec une requete HTTP code=406. Va nous permettre de gérer les cas d'erreurs de manière plus spécifique.
     * Dans une DataIntegrityViolationException il faut se référer au code SQL de l'erreur.
     *
     *@exception DataIntegrityViolationException Cette exception est levée par hibernate dans le cas ou les contraintes de la db sql ne sont pas respectées.
     * @param ex : DataIntegrityViolationException
     * @return errors: liste de ErrorDTO(String: nomDuChamps,String: messageErreur) && Code HTTP de la requete = 406
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ErrorDTO integrityException (DataIntegrityViolationException ex)
    {
        // Le code SQL -803 fait référence à la contrainte d'unicité des champs
       return (Objects.requireNonNull(ex.getRootCause()).getMessage().contains("SQLCODE=-803") ? new ErrorDTO("Email/Pseudo","L'email et/ou le pseudo existe deja") : new ErrorDTO("Serveur","Erreur serveur"));
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

    @ApiOperation(value = "Appelé pour la connexion d'un utilisateur")
    @PostMapping("/connexion")
    public ResponseEntity<UtilisateurDetailsDTO> connexionUtilisateur(@RequestBody UtilisateurConnexionDTO utilisateurConnexionDTO)
    {
        UtilisateurDetailsDTO utilisateurDetailsDTO = recuperationUtilisateurService.connexionUtilisateur(utilisateurConnexionDTO.getMail(),utilisateurConnexionDTO.getMotDePasse());
        return (utilisateurDetailsDTO != null ? ResponseEntity.ok(utilisateurDetailsDTO) : new ResponseEntity("mail ou mot de passe incorrect", HttpStatus.NOT_FOUND));
    }


    @ApiOperation(value = "pour récupérer un utilisateur")
    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDTO> getUtilisateurParId(@PathVariable("id") long id) throws ErrorServiceException {

        UtilisateurDTO utilisateurDTO = recuperationUtilisateurService.recupererUtilisateur(id);
        System.out.println("Taille de la participation : "+ utilisateurDTO.getParticipations().size());

        return (ResponseEntity.ok(utilisateurDTO));
    }

}
