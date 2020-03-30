package be.technobel.ucmppg.controllers;

import be.technobel.ucmppg.dal.entities.UtilisateurEntity;
import be.technobel.ucmppg.dal.repositories.UtilisateurRepository;
import be.technobel.ucmppg.BL.dto.UserDTODetails;
import be.technobel.ucmppg.BL.dto.UserDTOLogin;
import be.technobel.ucmppg.BL.dto.UserDTORegister;
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
    public ResponseEntity<UserDTODetails> registerUser (@RequestBody UserDTORegister userDTORegister)
    {
       UtilisateurEntity utilisateurEntity = new UtilisateurEntity(userDTORegister);

        return ResponseEntity.ok(new UserDTODetails(utilisateurRepository.save(utilisateurEntity)));
    }

    @ApiOperation(value = "Appelé pour la connexion d'un utilisateur")
    @PostMapping("/login")
    public ResponseEntity<UserDTODetails> loginUser (@RequestBody UserDTOLogin userDTOLogin)
    {
        UserDTODetails userDTODetails = new UserDTODetails(Objects.requireNonNull(utilisateurRepository.findByEmailAndMotDePasse(userDTOLogin.getEmail(), userDTOLogin.getPassword()).orElse(null)));
        return ResponseEntity.ok(userDTODetails);
    }


}