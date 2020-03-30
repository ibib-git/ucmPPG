package be.technobel.ucmppg.controller;

import be.technobel.ucmppg.DAL.Models.UtilisateurEntityFromPast;
import be.technobel.ucmppg.DAL.repositories.UtilisateurRepository;
import be.technobel.ucmppg.dto.UtilisateurDetailsDTO;
import be.technobel.ucmppg.dto.UtilisateurConnexionDTO;
import be.technobel.ucmppg.dto.UtilisateurEnregistrementDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Api(value = "API pour les opérations CRUD sur les utilisateurs")
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UtilisateurController {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurController(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @ApiOperation(value = "Appelé pour l'enregistrement d'un nouvel utilisateur")
    @PostMapping("/register")
    public ResponseEntity<UtilisateurDetailsDTO> enregistrementUtilisateur(@RequestBody UtilisateurEnregistrementDTO utilisateurEnregistrementDTO)
    {
       UtilisateurEntityFromPast utilisateurEntityFromPast = new UtilisateurEntityFromPast(utilisateurEnregistrementDTO);

        return ResponseEntity.ok(new UtilisateurDetailsDTO(utilisateurRepository.save(utilisateurEntityFromPast)));
    }

    @ApiOperation(value = "Appelé pour la connexion d'un utilisateur")
    @PostMapping("/login")
    public ResponseEntity<UtilisateurDetailsDTO> connexionUtilisateur(@RequestBody UtilisateurConnexionDTO utilisateurConnexionDTO)
    {
        UtilisateurDetailsDTO utilisateurDetailsDTO = new UtilisateurDetailsDTO(Objects.requireNonNull(utilisateurRepository.findByEmailAndMotDePasse(utilisateurConnexionDTO.getEmail(), utilisateurConnexionDTO.getPassword()).orElse(null)));
        return ResponseEntity.ok(utilisateurDetailsDTO);
    }


}
