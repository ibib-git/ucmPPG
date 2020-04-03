package be.technobel.ucmppg.dal.entities;

import be.technobel.ucmppg.bl.dto.ParticipationDTO;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Participation_Projet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idParticipation;

    @OneToOne(fetch = FetchType.EAGER)
    private RoleProjetEntity roleDuParticipant;

    @OneToOne
    private UtilisateurEntity utilisateurParticipant;

    @OneToOne
    private ProjetEntity projetParticipation;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

//    public ParticipationEntity(ParticipationDTO participation_DAO) {
//        this.projetParticipation = new ProjetEntity(participation_DAO.getProjet());
//        this.roleDuParticipant = new RoleProjetEntity(participation_DAO.getRole());
//        this.utilisateurParticipant = new UtilisateurEntity(participation_DAO.getUtilisateur());
//    }
}
