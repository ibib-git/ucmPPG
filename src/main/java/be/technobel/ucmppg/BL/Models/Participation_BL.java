package be.technobel.ucmppg.BL.Models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Participation_BL {

    private Role_Projet_BL role_projet;
    private Utilisateur_BL utilisateur;
    private Projet_BL projet;

}
