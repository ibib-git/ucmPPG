package be.technobel.ucmppg.DAL.Models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Participation_Projet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Participation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_Participation;

    @OneToOne
    private Role_Projet roles_du_participant;

    @OneToOne
    private Utilisateur utilisateur_participant;

    @OneToOne
    private Projet projet_participation;

}
