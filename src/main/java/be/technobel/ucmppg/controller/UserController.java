package be.technobel.ucmppg.controller;

import be.technobel.ucmppg.DAL.Models.UtilisateurEntity;
import be.technobel.ucmppg.DAL.repositories.UtilisateurRepository;
import be.technobel.ucmppg.dto.UserDTODetails;
import be.technobel.ucmppg.dto.UserDTOLogin;
import be.technobel.ucmppg.dto.UserDTORegister;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
        System.out.println(utilisateurEntity);
        //UserDTODetails userDTODetails = new UserDTODetails(utilisateurRepository.save(new UtilisateurEntity(userDTORegister)));
        utilisateurRepository.save(utilisateurEntity);
        UserDTODetails userDTODetails = new UserDTODetails();
        System.out.println(userDTODetails);
        return ResponseEntity.ok(userDTODetails);
    }

    @ApiOperation(value = "Appelé pour la connexion d'un utilisateur")
    @PostMapping("/login")
    public ResponseEntity<UserDTODetails> loginUser (@RequestBody UserDTOLogin userDTOLogin)
    {
        UserDTODetails userDTODetails = new UserDTODetails(Objects.requireNonNull(utilisateurRepository.findByEmailAndMotDePasse(userDTOLogin.getEmail(), userDTOLogin.getPassword()).orElse(null)));
        System.out.println(userDTODetails);
        return ResponseEntity.ok(userDTODetails);
    }


}
