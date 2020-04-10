package be.technobel.ucmppg.bl.dto.projet.workflow;

import be.technobel.ucmppg.bl.dto.utilisateur.UtilisateurDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdreEtapeDTO {

    private int idUtilisateur;
    private int nvOrdre;
}
