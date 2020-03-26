package be.technobel.ucmppg.DAL.Models;

import be.technobel.ucmppg.API_Projet.DAO.ParticipationDAO;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Participation_Projet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ParticipationEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idParticipation;

    @OneToOne
    private RoleProjetEntity roleDuParticipant;

    @OneToOne
    private UtilisateurEntity utilisateurParticipant;

    @OneToOne
    private ProjetEntity projetParticipation;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public ParticipationEntity(ParticipationDAO participation_DAO) {
        this.projetParticipation = new ProjetEntity(participation_DAO.getProjet());
        this.roleDuParticipant = new RoleProjetEntity(participation_DAO.getRole());
        this.utilisateurParticipant = new UtilisateurEntity(participation_DAO.getUtilisateur());
    }
}
