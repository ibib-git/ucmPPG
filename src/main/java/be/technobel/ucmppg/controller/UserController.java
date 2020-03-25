package be.technobel.ucmppg.controller;

import be.technobel.ucmppg.DAL.Models.UtilisateurEntity;
import be.technobel.ucmppg.DAL.repositories.UtilisateurRepository;
import be.technobel.ucmppg.dto.UserDTODetails;
import be.technobel.ucmppg.dto.UserDTORegister;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
       UserDTODetails userDTODetails = new UserDTODetails(utilisateurRepository.save(new UtilisateurEntity(userDTORegister)));
        System.out.println(userDTODetails);
        return ResponseEntity.ok(userDTODetails);
    }


}
