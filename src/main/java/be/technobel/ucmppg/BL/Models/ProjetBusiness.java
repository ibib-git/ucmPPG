package be.technobel.ucmppg.BL.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjetBusiness {

    private String nom_projet;
    private String description_projet;
    private UtilisateurBusiness utilisateur_createur_projet;
    private List<ParticipationBusiness> utilisateurs_projet;
    private List<EtapeWorkflowBusiness> colonne_Du_Projet;
}
