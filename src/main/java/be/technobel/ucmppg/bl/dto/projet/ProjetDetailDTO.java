package be.technobel.ucmppg.bl.dto.projet;

import be.technobel.ucmppg.bl.dto.RoleDTO;
import be.technobel.ucmppg.bl.dto.participations.MembreProjetDTO;
import be.technobel.ucmppg.bl.dto.projet.workflow.EtapeWorkflowDTO;
import be.technobel.ucmppg.bl.dto.utilisateur.UtilisateurDetailsDTO;
import be.technobel.ucmppg.dal.entities.ProjetEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProjetDetailDTO {

    private long id;
    private String nomProjet;
    private String descriptionProjet;
    private UtilisateurDetailsDTO utilisateurCreateurProjet;

    public ProjetDetailDTO(ProjetEntity projetEntity) {
        this.id = projetEntity.getIdProjet();
        this.nomProjet = projetEntity.getNomDeProjet();
        this.descriptionProjet = projetEntity.getDescriptionDeProjet();
        this.utilisateurCreateurProjet = new UtilisateurDetailsDTO(projetEntity.getUtilisateurCreateur());
    }
}
