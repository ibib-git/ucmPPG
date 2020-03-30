package be.technobel.ucmppg.controllers;

import be.technobel.ucmppg.dal.entities.UtilisateurEntity;
import be.technobel.ucmppg.dal.repositories.UtilisateurRepository;
import be.technobel.ucmppg.BL.dto.UtilisateurDetailsDTO;
import be.technobel.ucmppg.BL.dto.UtilisateurConnexionDTO;
import be.technobel.ucmppg.BL.dto.UtilisateurEnregistrementDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Api(value = "API pour les opérations CRUD sur les utilisateurs")
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    private final UtilisateurRepository utilisateurRepository;

    public UserController(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @ApiOperation(value = "Appelé pour l'enregistrement d'un nouvel utilisateur")
    @PostMapping("/register")
    public ResponseEntity<UtilisateurDetailsDTO> registerUser (@RequestBody UtilisateurEnregistrementDTO utilisateurEnregistrementDTO)
    {
       UtilisateurEntity utilisateurEntity = new UtilisateurEntity(utilisateurEnregistrementDTO);

        return ResponseEntity.ok(new UtilisateurDetailsDTO(utilisateurRepository.save(utilisateurEntity)));
    }

    @ApiOperation(value = "Appelé pour la connexion d'un utilisateur")
    @PostMapping("/login")
    public ResponseEntity<UtilisateurDetailsDTO> loginUser (@RequestBody UtilisateurConnexionDTO utilisateurConnexionDTO)
    {
        UtilisateurDetailsDTO utilisateurDetailsDTO = new UtilisateurDetailsDTO(Objects.requireNonNull(utilisateurRepository.findByEmailUtilisateurAndMotDePasseUtilisateur(utilisateurConnexionDTO.getEmail(), utilisateurConnexionDTO.getPassword()).orElse(null)));
        return ResponseEntity.ok(utilisateurDetailsDTO);
    }


}
