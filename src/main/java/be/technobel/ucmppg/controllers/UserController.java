package be.technobel.ucmppg.controllers;

import be.technobel.ucmppg.bl.dto.utilisateur.UtilisateurDetailsDTO;
import be.technobel.ucmppg.dal.entities.UtilisateurEntity;
import be.technobel.ucmppg.dal.repositories.UtilisateurRepository;
import be.technobel.ucmppg.bl.dto.UserDTOLogin;
import be.technobel.ucmppg.bl.dto.UserDTORegister;
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
    public ResponseEntity<UtilisateurDetailsDTO> registerUser (@RequestBody UserDTORegister userDTORegister)
    {
       UtilisateurEntity utilisateurEntity = new UtilisateurEntity(userDTORegister);

        return ResponseEntity.ok(new UtilisateurDetailsDTO(utilisateurRepository.save(utilisateurEntity)));
    }

    @ApiOperation(value = "Appelé pour la connexion d'un utilisateur")
    @PostMapping("/login")
    public ResponseEntity<UtilisateurDetailsDTO> loginUser (@RequestBody UserDTOLogin userDTOLogin)
    {
        UtilisateurDetailsDTO userDTODetails = new UtilisateurDetailsDTO(Objects.requireNonNull(utilisateurRepository.findByEmailAndMotDePasse(userDTOLogin.getEmail(), userDTOLogin.getPassword()).orElse(null)));
        return ResponseEntity.ok(userDTODetails);
    }


}
