package be.technobel.ucmppg.DAL.Models;

import be.technobel.ucmppg.API_Projet.DAO.EtapeWorkflowDAO;
import be.technobel.ucmppg.API_Projet.DAO.ParticipationDAO;
import be.technobel.ucmppg.API_Projet.DAO.ProjetDAO;
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

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public ProjetEntity(ProjetDAO projet_DAO) {
        this.nomDeProjet = projet_DAO.getNom_projet();
        this.descriptionDeProjet = projet_DAO.getDescription_projet();
        this.utilisateurCreateur = new UtilisateurEntity(projet_DAO.getUtilisateur_createur_projet());
        for (ParticipationDAO p: projet_DAO.getUtilisateurs_projet()) {
            this.membresDuProjet.add(new ParticipationEntity(p));
        }
        for (EtapeWorkflowDAO e: projet_DAO.getColonne_Du_Projet()) {
            this.etapeWorkflows.add(new EtapeWorkflowEntity(e));
        }
    }
}
