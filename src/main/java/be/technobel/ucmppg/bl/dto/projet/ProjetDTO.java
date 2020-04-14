package be.technobel.ucmppg.bl.dto.projet;

import be.technobel.ucmppg.bl.dto.RoleDTO;
import be.technobel.ucmppg.bl.dto.projet.workflow.EtapeWorkflowDTO;
import be.technobel.ucmppg.bl.dto.participations.MembreProjetDTO;
import be.technobel.ucmppg.bl.dto.utilisateur.UtilisateurDetailsDTO;
import be.technobel.ucmppg.dal.entities.ProjetEntity;
import be.technobel.ucmppg.dal.entities.RoleProjetEntity;
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
public class ProjetDTO {

    private long id;
    private String nom;
    private String description;
    private UtilisateurDetailsDTO createurUtilisateur;
    private List<MembreProjetDTO> utilisateurMembres = new ArrayList<>();
    private List<EtapeWorkflowDTO> etapeWorkflows = new ArrayList<>();
    private List<RoleDTO> roles = new ArrayList<>();

    public ProjetDTO(ProjetEntity projetEntity) {
        this.nom=projetEntity.getNomDeProjet();
        this.description=projetEntity.getDescriptionDeProjet();
        this.createurUtilisateur=new UtilisateurDetailsDTO(projetEntity.getUtilisateurCreateur());
        projetEntity.getMembresDuProjet().forEach(
                participationEntity -> {
                    this.utilisateurMembres.add(new MembreProjetDTO(participationEntity));
                }
        );
        this.etapeWorkflows =projetEntity.getEtapeWorkflows().stream()
                .map(
                        EtapeWorkflowDTO::new
                ).collect(Collectors.toList());

        this.roles = projetEntity.getRolesProjet().stream()
                .map(
                        RoleDTO::new
                ).collect(Collectors.toList());
    }

}
