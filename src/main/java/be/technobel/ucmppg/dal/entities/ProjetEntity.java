package be.technobel.ucmppg.dal.entities;

import be.technobel.ucmppg.bl.dto.EtapeWorkflowDTO;
import be.technobel.ucmppg.bl.dto.ParticipationDTO;
import be.technobel.ucmppg.bl.dto.projet.ProjetDTO;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Tableau_Projet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProjetEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProjet;

    @Column(name = "nom_du_Projet", nullable = false)
    private String nomDeProjet;

    @Column(name = "Description_du_Projet")
    private String descriptionDeProjet;

    @OneToOne
    private UtilisateurEntity utilisateurCreateur;

    @OneToMany
    private Set<ParticipationEntity> membresDuProjet =new HashSet<>();

    @OneToMany
    private Set<EtapeWorkflowEntity> etapeWorkflows=new HashSet<>();

    @OneToMany
    private Set<RoleProjetEntity> rolesProjet=new HashSet<>();

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public ProjetEntity(ProjetDTO projet_DAO) {
        this.nomDeProjet = projet_DAO.getNomProjet();
        this.descriptionDeProjet = projet_DAO.getDescriptionProjet();
        this.utilisateurCreateur = new UtilisateurEntity(projet_DAO.getUtilisateurCreateurProjet());
        for (ParticipationDTO p: projet_DAO.getUtilisateursProjet()) {
            this.membresDuProjet.add(new ParticipationEntity(p));
        }
        for (EtapeWorkflowDTO e: projet_DAO.getColonneDuProjet()) {
            this.etapeWorkflows.add(new EtapeWorkflowEntity(e));
        }
    }
}
