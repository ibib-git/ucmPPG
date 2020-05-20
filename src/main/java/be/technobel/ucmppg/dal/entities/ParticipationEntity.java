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

    @Column(name ="Actif_Sur_Le_Projet", nullable = false)
    private boolean actif;

    @ManyToOne(fetch = FetchType.EAGER)
    private RoleProjetEntity roleDuParticipant;

    @ManyToOne
    private UtilisateurEntity utilisateurParticipant;

    @ManyToOne
    private ProjetEntity projetParticipation;

    @Override
    public int hashCode() {
        return super.hashCode();
    }
  

}
