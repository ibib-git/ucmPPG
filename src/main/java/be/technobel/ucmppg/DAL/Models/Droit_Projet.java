package be.technobel.ucmppg.DAL.Models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="Droit_Autorisation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Droit_Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_Droit;

    @Column(name = "nom",nullable = false, unique = true)
    private String nom_de_droit;

}
