package be.technobel.ucmppg.controller;

import be.technobel.ucmppg.dto.UserDTORegister;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Api(value = "API pour les opérations CRUD sur les utilisateurs")
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @ApiOperation(value = "Appelé pour l'enregistrement d'un nouvel utilisateur")
    @PostMapping("/register")
    public ResponseEntity<String> registerUser (@RequestBody UserDTORegister userDTORegister)
    {
        //TODO Damien : DAL.save

        return ResponseEntity.ok("200");
    }


}
