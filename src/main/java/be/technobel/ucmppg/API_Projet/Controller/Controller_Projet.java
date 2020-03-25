package be.technobel.ucmppg.API_Projet.Controller;

import be.technobel.ucmppg.API_Projet.DAO.Projet_DAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/projet")
@CrossOrigin
public class Controller_Projet {

    public Projet_DAO postCreationProjet(@RequestBody Projet_DAO projet_DAO){

        return projet_DAO;
    }


}
