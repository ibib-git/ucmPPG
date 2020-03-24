package be.technobel.ucmppg.API_Projet.Service.Gestion;

import be.technobel.ucmppg.BL.Models.Droit_Projet_BL;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Service_Projet_Par_defaut {

    @Autowired
    private List<DroitPossible> droitPossibles;
}
