package be.technobel.ucmppg.dal.entities;

import be.technobel.ucmppg.bl.dto.ParticipationDTO;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Participation_Projet")
@Getter
@Setter
@ToString (exclude = "utilisateurParticipant")
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idParticipation;

    @Column(name ="Actif_Sur_Le_Projet", nullable = false)
    private boolean isActif;

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
  

}
