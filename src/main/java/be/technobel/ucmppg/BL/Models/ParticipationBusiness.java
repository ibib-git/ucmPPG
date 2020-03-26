package be.technobel.ucmppg.BL.Models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParticipationBusiness {

    private RoleBusiness role_projet;
    private UtilisateurBusiness utilisateur;
    private ProjetBusiness projet;
}
