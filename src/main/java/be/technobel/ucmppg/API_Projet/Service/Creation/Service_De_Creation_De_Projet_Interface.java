package be.technobel.ucmppg.API_Projet.Service.Creation;

import be.technobel.ucmppg.API_Projet.DAO.Projet_DAO;
import be.technobel.ucmppg.BL.Models.Participation_BL;
import be.technobel.ucmppg.BL.Models.Utilisateur_BL;
import java.util.List;

public interface Service_De_Creation_De_Projet_Interface {

    // transforme une liste d'utilisateur en Participant
    List<Participation_BL> Creation_de_Liste_Participation(Projet_DAO p);


    // transforme une liste de String Email en Utilisateur en cherchant dans la DB
    List<Utilisateur_BL> liste_utilisateur_par_Email_liste(List<String> email_liste);


}
