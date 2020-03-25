package be.technobel.ucmppg.API_Projet.Service.Creation;

import be.technobel.ucmppg.BL.Models.Droit_Projet_BL;
import be.technobel.ucmppg.BL.Models.Participation_BL;
import be.technobel.ucmppg.BL.Models.Projet_BL;
import be.technobel.ucmppg.BL.Models.Utilisateur_BL;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Service_De_Creation_De_Projet implements Service_De_Creation_De_Projet_Interface {


    @Override
    public List<Participation_BL> transforme_Util_En_Parti(Utilisateur_BL u, Projet_BL p) {
        return null;
    }
}
