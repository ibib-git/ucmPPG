package be.technobel.ucmppg.API_Projet.Service.Interface;

import be.technobel.ucmppg.BL.Models.Participation_BL;
import be.technobel.ucmppg.BL.Models.Projet_BL;
import be.technobel.ucmppg.BL.Models.Utilisateur_BL;
import java.util.List;

public interface Service_De_Creation_De_Projet_Interface {

    List<Participation_BL> transforme_Util_En_Parti(Utilisateur_BL u, Projet_BL p);

}
